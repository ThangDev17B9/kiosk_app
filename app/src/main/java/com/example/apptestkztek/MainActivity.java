package com.example.apptestkztek;

import android.content.Intent;
import android.os.Bundle;
import com.example.apptestkztek.databinding.ActivityMainBinding;
import com.example.apptestkztek.screens.activity.ConnectDeviceActivity;
import com.example.apptestkztek.screens.activity.SettingActivity;
import com.example.apptestkztek.screens.activity.StatusActivity;
import com.example.apptestkztek.screens.activity.UserCardActivity;
import com.example.apptestkztek.screens.activity.VersionInforActivity;

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
    }

    private void NextToStatusActivity() {
        ;
        startActivity(new Intent(this, StatusActivity.class));
    }

    private void NextToAddCardActivity() {
        startActivity(new Intent(this, UserCardActivity.class));
    }

    private void NextToSettingActivity() {
        startActivity(new Intent(this, SettingActivity.class));
    }
}