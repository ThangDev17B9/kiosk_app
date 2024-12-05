package com.example.apptestkztek.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.apptestkztek.controller.root.shell.OverscanHelper;
import com.example.apptestkztek.databinding.ActivityOptionModeBinding;

public class OptionModeActivity extends BaseActivity {

    public ActivityOptionModeBinding binding;
    public OverscanHelper overscanHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOptionModeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnSubmit.setOnClickListener(v-> NextToConnectDeviceActivity());
        binding.actvLanguage.setOnClickListener(v-> ListItemLanguage());
        binding.actvMode.setOnClickListener(v-> ListItemMode());
        binding.ivLogo.setOnLongClickListener(v -> {
            BackToHomeDivece();
            return false;
        });
        overscanHelper = new OverscanHelper();
        overscanHelper.setOverscan("0,-50,0,-100");
    }

    private void BackToHomeDivece() {
        overscanHelper = new OverscanHelper();
        overscanHelper.setOverscan("reset");
        stopLockTask();
        finishAffinity();
    }

    private void ListItemLanguage(){
        String[] items = {"Tiếng Việt"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        binding.actvLanguage.setAdapter(adapter);
        binding.actvLanguage.showDropDown();

    }
    private void ListItemMode(){
        String[] items = {"Test thiết bị Kztek"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        binding.actvMode.setAdapter(adapter);
        binding.actvMode.showDropDown();
    }
    private void NextToConnectDeviceActivity() {
        String language = binding.actvLanguage.getText().toString();
        String mode = binding.actvMode.getText().toString();
        if(language.isEmpty() && mode.isEmpty()){
            Toast.makeText(this, "Bạn chưa chọn đủ thông tin", Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, ConnectDeviceActivity.class);
            startActivity(intent);
            finish();
        }
    }
}