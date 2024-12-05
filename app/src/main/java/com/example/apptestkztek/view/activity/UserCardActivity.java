package com.example.apptestkztek.view.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.viewpager2.widget.ViewPager2;

import com.example.apptestkztek.R;
import com.example.apptestkztek.controller.viewpager2.AddCardAndListCardViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class UserCardActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_card);
        TabLayout tabLayout = findViewById(R.id.tabLayoutUser);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2User);
        ImageView ivBackToMainActivity = findViewById(R.id.ivBackToMainActivity);
        ivBackToMainActivity.setOnClickListener(v->IntentMainActivity(this));
        AddCardAndListCardViewPager2 addCardAndListCardViewPager2 = new AddCardAndListCardViewPager2(this);
        viewPager2.setAdapter(addCardAndListCardViewPager2);
        new TabLayoutMediator(tabLayout, viewPager2, (tab, i) -> {
            switch (i) {
                case 0:
                    tab.setText("Nạp thẻ");
                    break;
                case 1:
                    tab.setText("Hủy thẻ");
                    break;
            }
        }).attach();

    }
}