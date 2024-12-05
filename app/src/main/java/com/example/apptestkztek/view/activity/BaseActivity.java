package com.example.apptestkztek.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.apptestkztek.MainActivity;
import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.root.shell.OverscanHelper;

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }
    public void IntentMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }
}