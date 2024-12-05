package com.example.apptestkztek.screens.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import androidx.viewpager2.widget.ViewPager2;
import com.example.apptestkztek.BaseActivity;
import com.example.apptestkztek.MainActivity;
import com.example.apptestkztek.R;
import com.example.apptestkztek.viewpager2.SettingIpAndTimeViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SettingActivity extends BaseActivity {
    public SettingIpAndTimeViewPager2 settingIpAndTimeViewPager2;

    public ViewPager2 viewPager2;
    public TabLayout tabLayout;
    public ImageView ivBackToMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        viewPager2 = findViewById(R.id.viewPager2);
        tabLayout = findViewById(R.id.tabLayout);
        ivBackToMainActivity = findViewById(R.id.ivBackToMainActivity);
        ivBackToMainActivity.setOnClickListener(v->IntentMainActivity(this));
        settingIpAndTimeViewPager2 = new SettingIpAndTimeViewPager2(this);
        viewPager2.setAdapter(settingIpAndTimeViewPager2);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, i) -> {
            switch (i) {
                case 0:
                    tab.setText("Cấu hình IP");
                    break;
                case 1:
                    tab.setText("Cấu hình time");
                    break;
            }
        }).attach();
    }
}