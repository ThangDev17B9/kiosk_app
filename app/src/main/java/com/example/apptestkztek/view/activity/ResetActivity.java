package com.example.apptestkztek.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptestkztek.MainActivity;
import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.client.UdpClientManager;
import com.example.apptestkztek.domain.api.Constant;

public class ResetActivity extends AppCompatActivity {
    public LinearLayout lnResetDevice, lnResetDefault;
    public ImageView ivBackToMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);
        lnResetDevice = findViewById(R.id.lnResetDevice);
        lnResetDefault = findViewById(R.id.lnResetDefault);
        lnResetDevice.setOnClickListener(v -> DialogResetDevice());
        lnResetDefault.setOnClickListener(v -> DialogResetDefault());
        ivBackToMain = findViewById(R.id.ivBackToMainActivity);
        ivBackToMain.setOnClickListener(v->BackToMainActivity());
    }

    private void BackToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    private void DialogResetDefault() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo xác nhận");
        builder.setMessage("Bạn có chắc muốn reset default ?");
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("Đồng ý", (dialog, which) -> new Thread(() -> {
            UdpClientManager.getInstance().require(Constant.resetDefault);
            String dataRes = UdpClientManager.getInstance().response();
            Log.e("DialogResetDefault: ", dataRes);
            switch (dataRes) {
                case "Error: TimeoutException":
                    runOnUiThread(() ->
                            Toast.makeText(this, "Không nhận được phản hồi từ thiết bị", Toast.LENGTH_SHORT).show());
                    break;
                case Constant.resetDefault + "Reseting":
                    runOnUiThread(() ->
                            Toast.makeText(this, "BĐK đang được reset", Toast.LENGTH_SHORT).show());
                case Constant.resetDevice + "OK":
                    runOnUiThread(() -> {
                        Toast.makeText(this, "Reset Default thành công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(this, ConnectDeviceActivity.class));
                    });
                    break;
            }
        }).start());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void DialogResetDevice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo xác nhận");
        builder.setMessage("Bạn có chắc muốn reset device ?");
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());
        builder.setPositiveButton("Đồng ý", (dialog, which) -> new Thread(() -> {
            String dataReq = UdpClientManager.getInstance().require(Constant.resetDevice);
            Log.e("DialogResetDevice: ", dataReq);
            String dataRes = UdpClientManager.getInstance().response();
            Log.e("DialogResetDevice: ", dataRes);
            if (dataRes.equals("Error: TimeoutException")) {
                runOnUiThread(() ->
                        Toast.makeText(this, "Không nhận được phản hồi từ thiết bị", Toast.LENGTH_SHORT).show());
            }if (dataRes.equals("ResetDevicet?/OK")) {
                runOnUiThread(() -> {
                    Toast.makeText(this, "Reset device thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this, ConnectDeviceActivity.class));
                });
            }
        }).start());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}