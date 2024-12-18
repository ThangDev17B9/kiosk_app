package com.example.apptestkztek.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.apptestkztek.MainActivity;
import com.example.apptestkztek.controller.client.UdpClientManager;
import com.example.apptestkztek.databinding.ActivityConnectDeviceBinding;
import com.example.apptestkztek.domain.api.Constant;

public class ConnectDeviceActivity extends BaseActivity {
    public ActivityConnectDeviceBinding binding;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectDeviceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.actvBDKConnectDevice.setOnClickListener(v -> ListItemBDKConnectDevice());
        binding.actvMethodConnectDevice.setOnClickListener(v -> ListItemMethod());
        binding.btnConnectDevice.setOnClickListener(v -> ConnectDevice());
        binding.btnBackToOptionActivity.setOnClickListener(v -> BackToOptionActivity());
        binding.btnNextToFindDevicesActivity.setOnClickListener(v -> NextToFindDevicesActivity());
        binding.progressBar.setVisibility(View.GONE);
        binding.edtIpAddressConnectDevice.setText("192.168.20.");
        binding.edtPortConnectDevice.setText("100");
    }

    private void NextToFindDevicesActivity() {
        startActivity(new Intent(this, FindDevicesActivity.class));
        finish();
    }

    private void BackToOptionActivity() {
        startActivity(new Intent(this, OptionModeActivity.class));
        finish();
    }

    private void ConnectDevice() {
        binding.progressBar.setVisibility(View.VISIBLE);
        String modeBDK = binding.actvBDKConnectDevice.getText().toString();
        String modeMethod = binding.actvMethodConnectDevice.getText().toString();
        String ipAddress = binding.edtIpAddressConnectDevice.getText().toString();
        String port = binding.edtPortConnectDevice.getText().toString();
        if (modeBDK.isEmpty() || modeMethod.isEmpty() || ipAddress.isEmpty() || port.isEmpty()) {
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(() -> {
            try {
                UdpClientManager.connect(ipAddress, Integer.parseInt(port));
                UdpClientManager.getInstance().require(Constant.autoDetect);
                String dataRes = UdpClientManager.getInstance().response();
                Log.e("ConnectDevice: ", dataRes);
                if(dataRes.equals("Error: TimeoutException")){
                    runOnUiThread(()->{
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Không có phản hồi", Toast.LENGTH_SHORT).show();
                    });
                }else{
                    runOnUiThread(() ->{
                        binding.progressBar.setVisibility(View.GONE);
                        Toast.makeText(this, "Kết nối thành công", Toast.LENGTH_SHORT).show();
                    });
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            } catch (Exception e) {
                Log.e("ConnectDevice: ", "fail", e);
                runOnUiThread(() ->{
                    binding.progressBar.setVisibility(View.GONE);
                    Toast.makeText(this, "Kết nối không thành công", Toast.LENGTH_SHORT).show();
                });
            }
        }).start();
    }
    private void ListItemMethod() {
        String[] items = {"UDP"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, items);
        binding.actvMethodConnectDevice.setAdapter(adapter);
        binding.actvMethodConnectDevice.showDropDown();
    }
    private void ListItemBDKConnectDevice() {
        String[] items = {"KZ_LOCK.NET"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_dropdown_item_1line, items);
        binding.actvBDKConnectDevice.setAdapter(adapter);
        binding.actvBDKConnectDevice.showDropDown();
    }
}