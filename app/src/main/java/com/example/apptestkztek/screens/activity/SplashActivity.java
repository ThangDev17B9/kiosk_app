package com.example.apptestkztek.screens.activity;

import android.annotation.SuppressLint;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptestkztek.BaseActivity;
import com.example.apptestkztek.MyDeviceAdminReceiver;
import com.example.apptestkztek.R;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, OptionModeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

}