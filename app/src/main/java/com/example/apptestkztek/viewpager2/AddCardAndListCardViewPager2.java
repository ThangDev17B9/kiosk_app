package com.example.apptestkztek.viewpager2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.apptestkztek.screens.fragments.AddCardFragment;
import com.example.apptestkztek.screens.fragments.ListCardFragment;

public class AddCardAndListCardViewPager2 extends FragmentStateAdapter {
    public AddCardAndListCardViewPager2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
         switch (position){
             case 0:
                 return new AddCardFragment();
             case 1:
                 return new ListCardFragment();
             default:
                 return new AddCardFragment();
         }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
