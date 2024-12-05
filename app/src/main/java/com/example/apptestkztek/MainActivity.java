package com.example.apptestkztek;

import android.content.Intent;
import android.os.Bundle;
import com.example.apptestkztek.databinding.ActivityMainBinding;
import com.example.apptestkztek.view.activity.BaseActivity;
import com.example.apptestkztek.view.activity.ConnectDeviceActivity;
import com.example.apptestkztek.view.activity.SettingActivity;
import com.example.apptestkztek.view.activity.StatusActivity;
import com.example.apptestkztek.view.activity.UserCardActivity;
import com.example.apptestkztek.view.activity.VersionInforActivity;

public class MainActivity extends BaseActivity {
    public ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.lnNextToSettingActivity.setOnClickListener(v->NextToSettingActivity());
        binding.lnNextToAddCardActivity.setOnClickListener(v->NextToAddCardActivity());
        binding.lnNextToStatusActity.setOnClickListener(v->NextToStatusActivity());
        binding.lnNextToVersionInforActivity.setOnClickListener(v->NextToVersionInforActivity());
        binding.lnNextToResetActivity.setOnClickListener(v->NextToResetActivity());
        binding.ivBackToConnectDeviceActivity.setOnClickListener(v-> BackToConnectDeviceActivity());
    }

    private void BackToConnectDeviceActivity() {
        startActivity(new Intent(this, ConnectDeviceActivity.class));
        finish();
    }

    private void NextToResetActivity() {

    }

    private void NextToVersionInforActivity() {
        startActivity(new Intent(this, VersionInforActivity.class));
        finish();
    }

    private void NextToStatusActivity() {
        startActivity(new Intent(this, StatusActivity.class));
        finish();
    }

    private void NextToAddCardActivity() {
        startActivity(new Intent(this, UserCardActivity.class));
        finish();
    }

    private void NextToSettingActivity() {
        startActivity(new Intent(this, SettingActivity.class));
        finish();
    }
}