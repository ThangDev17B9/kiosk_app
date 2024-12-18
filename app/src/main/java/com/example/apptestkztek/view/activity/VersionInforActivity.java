package com.example.apptestkztek.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptestkztek.R;
import com.example.apptestkztek.domain.api.Constant;
import com.example.apptestkztek.controller.client.UdpClientManager;

public class VersionInforActivity extends BaseActivity {
    TextView tvVersionInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_infor);
        tvVersionInfor = findViewById(R.id.tvVersionInfor);
        ImageView ivBackToMainActivity = findViewById(R.id.ivBackToMainActivity);
        ivBackToMainActivity.setOnClickListener(v->IntentMainActivity(this));
        ShowInforVersionDeveice();
    }

    private void ShowInforVersionDeveice() {
        new Thread(()->{
            TextView tvVersionInfor = findViewById(R.id.tvVersionInfor);
            UdpClientManager.getInstance().require(Constant.getFirmwareVersion);
            Log.e( "ShowInforVersionDeveice: ", "request done");
            String dataReceive = UdpClientManager.getInstance().response();
            if(dataReceive.equals("Error: TimeoutException")){
                Toast.makeText(this, "Không nhận được phản hồi từ thiết bị", Toast.LENGTH_SHORT).show();
            }else{
                int data = dataReceive.indexOf("=");
                String textInforVersion = dataReceive.substring(data + 1);
                Log.e("textInforVersion: ", textInforVersion);
                runOnUiThread(()->tvVersionInfor.setText(textInforVersion));
            }


        }).start();

    }
}