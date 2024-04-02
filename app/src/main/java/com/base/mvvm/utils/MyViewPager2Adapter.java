package com.base.mvvm.utils;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.base.mvvm.ui.fragment.account.AccountFragment;
import com.base.mvvm.ui.fragment.activity.ActivityFragment;
import com.base.mvvm.ui.fragment.favourite.FavouriteFragment;
import com.base.mvvm.ui.fragment.home.HomeFragment;


public class MyViewPager2Adapter extends FragmentStateAdapter {
    Bundle bundle;

    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity, Bundle bundle) {
        super(fragmentActivity);
        this.bundle = bundle;
    }

    @NonNull
    @Override
    public Fragment createFragment(int i) {
        switch (i) {
            case 0:
                return new HomeFragment();
            case 1:
                return new ActivityFragment();
            case 2:
                return new FavouriteFragment();
            case 3:{
                AccountFragment accountFragment = new AccountFragment();
//                accountFragment.setArguments(bundle);
                return accountFragment;
            }

            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
