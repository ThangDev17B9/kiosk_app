package com.example.apptestkztek.screens.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.apptestkztek.BaseActivity;
import com.example.apptestkztek.R;
import com.example.apptestkztek.api.Constant;
import com.example.apptestkztek.client.UdpClient;
import com.example.apptestkztek.client.UdpClientManager;

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
            String dataReceive = UdpClient.response();
            Log.e("receive done", dataReceive);
            int data = dataReceive.indexOf("=");
            String textInforVersion = dataReceive.substring(data + 1);
            Log.e("textInforVersion: ", textInforVersion);
            runOnUiThread(()->tvVersionInfor.setText(textInforVersion));
        }).start();

    }
}