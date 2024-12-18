package com.example.apptestkztek.view.activity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
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
        ImageView imgLogo = findViewById(R.id.ivLogo);
        // Tạo hiệu ứng di chuyển từ dưới lên
        ObjectAnimator animator = ObjectAnimator.ofFloat(imgLogo, "translationY", 1000f, 0f);
        // Từ vị trí dưới (1000f) đến vị trí ban đầu (0f)
        animator.setDuration(2000);  // Thời gian di chuyển
        animator.setInterpolator(new android.view.animation.OvershootInterpolator());  // Tạo hiệu ứng nhảy
        animator.start();  // Bắt đầu hiệu ứng
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, OptionModeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }

}