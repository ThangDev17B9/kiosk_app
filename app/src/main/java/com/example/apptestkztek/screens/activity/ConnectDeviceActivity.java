package com.example.apptestkztek.screens.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.example.apptestkztek.BaseActivity;
import com.example.apptestkztek.MainActivity;
import com.example.apptestkztek.client.UdpClientManager;
import com.example.apptestkztek.databinding.ActivityConnectDeviceBinding;

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
        binding.btnBackToOptionActivity.setOnClickListener(t-> BackToOptionActivity());
        binding.edtIpAddressConnectDevice.setText("192.168.20.159");
        binding.edtPortConnectDevice.setText("100");
    }

    private void BackToOptionActivity() {
        startActivity(new Intent(this, OptionModeActivity.class));
        finish();
    }

    private void ConnectDevice() {
        String modeBDK = binding.actvBDKConnectDevice.getText().toString();
        String modeMethod = binding.actvMethodConnectDevice.getText().toString();
        String ipAddress = binding.edtIpAddressConnectDevice.getText().toString();
        String port = binding.edtPortConnectDevice.getText().toString();
        if (modeBDK.isEmpty() || modeMethod.isEmpty() || ipAddress.isEmpty() || port.isEmpty()) {
            Toast.makeText(this, "Bạn chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread(() -> {
            try {
                UdpClientManager.connect(ipAddress, Integer.parseInt(port));
                runOnUiThread(() -> Toast.makeText(this, "Kết nối thành công", Toast.LENGTH_SHORT).show());
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            } catch (Exception e) {
                Log.e("ConnectDevice: ", "fail", e);
                runOnUiThread(() -> Toast.makeText(this, "Kết nối không thành công", Toast.LENGTH_SHORT).show());
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