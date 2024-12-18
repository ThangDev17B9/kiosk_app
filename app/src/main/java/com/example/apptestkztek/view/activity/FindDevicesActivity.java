package com.example.apptestkztek.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.client.UdpClient;
import com.example.apptestkztek.domain.api.Constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FindDevicesActivity extends BaseActivity {
    public Button btnBackToConnectDevice, btnFindDevices;
    public EditText edtStart, edtEnd, edtPort;
    public TextView tvIp;
    public String baseIp = "192.168.20.";
    public ProgressBar progressBar;
    public Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_devices);
        btnBackToConnectDevice = findViewById(R.id.btnBackToConnectDevice);
        btnFindDevices = findViewById(R.id.btnFindDevices);
        edtStart = findViewById(R.id.edtIpStart);
        edtEnd = findViewById(R.id.edtIpEnd);
        edtPort = findViewById(R.id.edtPort);
        progressBar = findViewById(R.id.progressBar);
        tvIp = findViewById(R.id.tvIp);
        btnBackToConnectDevice.setOnClickListener(v -> BackToConnectDeviceActivity());
        btnFindDevices.setOnClickListener(v -> FindDevicesByIp());
    }

    @SuppressLint("SetTextI18n")
    private void FindDevicesByIp() {
        thread = new Thread(() -> {

            String start = edtStart.getText().toString();
            String end = edtEnd.getText().toString();
            String port = edtPort.getText().toString();
            if (start.isEmpty() || end.isEmpty() || port.isEmpty()) {
                runOnUiThread(() -> Toast.makeText(this, "Chưa nhập đủ thông tin", Toast.LENGTH_SHORT).show());
            } else if (Integer.parseInt(end) < Integer.parseInt(start)) {
                runOnUiThread(() -> Toast.makeText(this, "ip kết thúc phải lớn hơn ip bắt đầu", Toast.LENGTH_SHORT).show());
            } else {
                int startIp = Integer.parseInt(start);
                int endIp = Integer.parseInt(end);
                int udpPort = Integer.parseInt(port);
                runOnUiThread(() ->
                {
                    progressBar.setVisibility(View.VISIBLE);
                    btnFindDevices.setVisibility(View.GONE);
                    btnBackToConnectDevice.setVisibility(View.GONE);
                });
                // Sử dụng ExecutorService để quản lý các luồng
                ExecutorService executor = Executors.newFixedThreadPool(20);
                for (int i = startIp; i <= endIp; i++) {
                    final int currentIp = i;
                    executor.execute(() -> {
                        String ip = baseIp + currentIp;
                        try {
                            UdpClient udpClient = new UdpClient();
                            String res = udpClient.scanSingleDevice(ip, udpPort, Constant.autoDetect);
                            if (!res.equals("Error: TimeoutException")) {
                                runOnUiThread(() -> {
                                    tvIp.setText("Thiết bị khả dụng : " + ip);
                                    tvIp.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.GONE);
                                    btnFindDevices.setVisibility(View.VISIBLE);
                                    btnBackToConnectDevice.setVisibility(View.VISIBLE);
                                    executor.shutdownNow();
                                });

                            }
                        } catch (Exception e) {
                            Log.e("Error: " + ip, e.getMessage());
                        }
                    });
                }

                // Đóng ExecutorService sau khi hoàn tất
                executor.shutdown();
                try {
                    if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                        executor.shutdownNow();
                    }
                } catch (InterruptedException e) {
                    executor.shutdownNow();
                }
            }
            runOnUiThread(() -> {
                progressBar.setVisibility(View.GONE);
                btnFindDevices.setVisibility(View.VISIBLE);
                btnBackToConnectDevice.setVisibility(View.VISIBLE);
            });

        });
        thread.start();
    }

    private void BackToConnectDeviceActivity() {
        startActivity(new Intent(this, ConnectDeviceActivity.class));
        finish();
    }
}