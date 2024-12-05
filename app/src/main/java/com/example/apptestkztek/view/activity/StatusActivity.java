package com.example.apptestkztek.view.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.apptestkztek.R;
import com.example.apptestkztek.view.adapter.CheckboxStatusAdapter;
import com.example.apptestkztek.domain.api.Constant;
import com.example.apptestkztek.controller.client.UdpClient;
import com.example.apptestkztek.controller.client.UdpClientManager;
import com.example.apptestkztek.model.Cabinet;

import java.util.ArrayList;
import java.util.List;

public class StatusActivity extends BaseActivity {
    private CheckboxStatusAdapter adapter;
    private List<Cabinet> cabinetList;
    private EditText edtFindCabinetToNumber;
    private TextView tvState;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView ivBackToMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        edtFindCabinetToNumber = findViewById(R.id.edtFindCabinetToNumber);
        Button btnFindCabinet = findViewById(R.id.btnSubmitFindCabinet);
        tvState = findViewById(R.id.tvState);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewStatusActivity);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayoutStatusActivity);
        ivBackToMainActivity = findViewById(R.id.ivBackToMainActivity);
        ivBackToMainActivity.setOnClickListener(v->IntentMainActivity(this));
        // Khởi tạo danh sách ban đầu
        cabinetList = new ArrayList<>();
        adapter = new CheckboxStatusAdapter(cabinetList);
        recyclerView.setAdapter(adapter);
        fetchDataFromUdp();
        swipeRefreshLayout.setOnRefreshListener(this::fetchDataFromUdp);
        btnFindCabinet.setOnClickListener(v -> FindCabinetStatus());
    }

    @SuppressLint("SetTextI18n")
    private void FindCabinetStatus() {
        new Thread(() -> {
            String cabinet = edtFindCabinetToNumber.getText().toString();
            String dataRequire;
            if (!cabinet.isEmpty()) {
                if(cabinet.length()  < 2){
                    dataRequire = "0" + cabinet;
                }else{
                    dataRequire = cabinet;
                }
                UdpClientManager.getInstance().require(Constant.getInputState + dataRequire);
                String dataInputState = UdpClient.response();
                for (int i = 0; i < 10; i++){
                    UdpClientManager.getInstance().require(Constant.getInputState + dataRequire);
                    if(dataInputState.length() == 37){
                        break;
                    }
                }
                Log.e("UDP Response", dataInputState);
                if (dataInputState.equals(Constant.getInputState + dataRequire + "/State=0")) {
                    runOnUiThread(() -> tvState.setText("Tủ " + cabinet + " đang mở"));
                    return;
                }
                if (dataInputState.equals(Constant.getInputState + dataRequire + "/State=1")) {
                    runOnUiThread(() -> tvState.setText("Tủ " + cabinet + " đang đóng"));
                }
            } else {
                runOnUiThread(() -> showToast("Chưa nhập tủ cần kiểm tra trạng thái"));
            }
        }).start();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void fetchDataFromUdp() {
        new Thread(() -> {
            try {
                runOnUiThread(()->swipeRefreshLayout.setRefreshing(true));
                UdpClientManager.getInstance().require(Constant.getAllInputState);
                UdpClientManager.getInstance();
                String dataInputState = UdpClient.response();
                Log.d("UDP Response", "Data received: " + dataInputState);
                // Nếu dữ liệu trả về không bị rỗng thì lấy chuỗi sau dấu =
                if (!dataInputState.isEmpty()) {
                    int index = dataInputState.indexOf("=");

                    if (index != -1 && index + 1 < dataInputState.length()) {
                        String state = dataInputState.substring(index + 1);

                        // Cập nhật danh sách cabinet dựa trên trạng thái nhận được
                        List<Cabinet> updatedList = parseCabinetState(state);

                        // Cập nhật giao diện trên UI Thread
                        runOnUiThread(() -> {
                            cabinetList.clear();
                            cabinetList.addAll(updatedList);
                            adapter.notifyDataSetChanged(); // Thông báo adapter cập nhật
                        });
                    } else {
                        showToast("Dữ liệu không hợp lệ!");
                    }
                } else {
                    showToast("Không nhận được phản hồi từ thiết bị!");
                }
            } catch (Exception e) {
                Log.e("UDP Error", "Error: " + e.getMessage());
                showToast("Lỗi kết nối");
            }finally {
                runOnUiThread(()->swipeRefreshLayout.setRefreshing(false));
            }
        }).start();
    }

    private List<Cabinet> parseCabinetState(String state) {
        List<Cabinet> list = new ArrayList<>();
        for (int i = 0; i < 36; i++) {
            boolean isOpen = state.charAt(i) == '0'; // x`Nếu là '0', tủ mở

            list.add(new Cabinet("Tủ đồ " + (i + 1), isOpen));
        }
        return list;
    }


    private void showToast(String message) {
        runOnUiThread(() -> Toast.makeText(this, message, Toast.LENGTH_SHORT).show());
    }
}
