package com.example.apptestkztek.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.apptestkztek.screens.fragments.ChangeIpAddressFragment;
import com.example.apptestkztek.screens.fragments.SetUpTimeFragment;

public class SettingIpAndTimeViewPager2 extends FragmentStateAdapter {
    public SettingIpAndTimeViewPager2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new ChangeIpAddressFragment();
            case 1:
                return new SetUpTimeFragment();
            default:
                return new ChangeIpAddressFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
