package com.example.apptestkztek.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.client.UdpClientManager;
import com.example.apptestkztek.domain.api.Constant;

public class OpenCabinetActivity extends AppCompatActivity {
    public ImageView ivBackToStatusActivity;
    public Button btnOpenCabinet1, btnOpenCabinet2, btnOpenCabinet3, btnOpenCabinet4,
            btnOpenCabinet5, btnOpenCabinet6, btnOpenCabinet7, btnOpenCabinetAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_cabinet);
        ivBackToStatusActivity = findViewById(R.id.ivBackToStatusActivity);
        btnOpenCabinet1 = findViewById(R.id.btnOpenCabinet1);
        btnOpenCabinet2 = findViewById(R.id.btnOpenCabinet2);
        btnOpenCabinet3 = findViewById(R.id.btnOpenCabinet3);
        btnOpenCabinet4 = findViewById(R.id.btnOpenCabinet4);
        btnOpenCabinet5 = findViewById(R.id.btnOpenCabinet5);
        btnOpenCabinet6 = findViewById(R.id.btnOpenCabinet6);
        btnOpenCabinet7 = findViewById(R.id.btnOpenCabinet7);
        btnOpenCabinet1.setOnClickListener(v-> OpenCabinet1());
        btnOpenCabinet2.setOnClickListener(v-> OpenCabinet2());
        btnOpenCabinet3.setOnClickListener(v-> OpenCabinet3());
        btnOpenCabinet4.setOnClickListener(v-> OpenCabinet4());
        btnOpenCabinet5.setOnClickListener(v-> OpenCabinet5());
        btnOpenCabinet6.setOnClickListener(v-> OpenCabinet6());
        btnOpenCabinet7.setOnClickListener(v-> OpenCabinet7());
    }
    // Tủ 6
    private void OpenCabinet6() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet1);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=01/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 1 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 1 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // Tủ 3
    private void OpenCabinet3() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet2);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=02/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 2 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 2 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // Tủ 1
    private void OpenCabinet1() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet3);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=03/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 3 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 3 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // Tủ 5
    private void OpenCabinet5() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet4);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=04/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 4 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 4 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // Tủ 4
    private void OpenCabinet4() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet5);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=05/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 5 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 5 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // Tủ 2
    private void OpenCabinet2() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet6);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=06/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 6 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 6 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
    // Tủ 7
    private void OpenCabinet7() {
        new Thread(()-> {
            UdpClientManager.getInstance().require(Constant.openCabinet7);
            String dataRes = UdpClientManager.getInstance().response();
            if(dataRes.equals("Error: TimeoutException")){
                runOnUiThread(()->
                        Toast.makeText(this, "không có phản hồi", Toast.LENGTH_SHORT).show());
            }else if(dataRes.equals("SetRelay?/Relay=07/OK")){
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 7 thành công", Toast.LENGTH_SHORT).show());
            }else {
                runOnUiThread(()->
                        Toast.makeText(this, "Mở tủ 7 thất bại", Toast.LENGTH_SHORT).show());
            }
        }).start();
    }
}