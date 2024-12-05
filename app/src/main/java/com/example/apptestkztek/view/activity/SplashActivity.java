package com.example.apptestkztek.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.root.shell.OverscanHelper;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        OverscanHelper overscanHelper = new OverscanHelper();
        overscanHelper.setFullScreen();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, OptionModeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

}