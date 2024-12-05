package com.example.apptestkztek;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        hideNavigationBar();
    }
    private boolean isNavigationBarVisible() {
        int uiOptions = getWindow().getDecorView().getSystemUiVisibility();
        return (uiOptions & View.SYSTEM_UI_FLAG_HIDE_NAVIGATION) == 0;
    }
    @SuppressLint("ObsoleteSdkInt")
    @Override
    protected void onResume() {
        super.onResume();
        final View contentView = findViewById(android.R.id.content);
        contentView.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            if (isNavigationBarVisible()) {
                hideNavigationBar();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
            if (am != null && am.getLockTaskModeState() == ActivityManager.LOCK_TASK_MODE_NONE) {
                startLockTask(); // Bắt đầu Lock Task Mode
            }
        }
    }
    @SuppressLint("ObsoleteSdkInt")
    private void hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
            );
        }
    }
    public void IntentMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

}