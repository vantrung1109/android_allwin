package com.base.mvvm.utils;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.base.mvvm.ui.fragment.account.AccountFragment;
import com.base.mvvm.ui.fragment.activity.ActivityFragment;
import com.base.mvvm.ui.fragment.favourite.FavouriteFragment;
import com.base.mvvm.ui.fragment.home.HomeFragment;


public class MyViewPager2Adapter extends FragmentStateAdapter {

    public MyViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int i) {
        switch (i) {
            case 1:
                Log.e("MyViewPager2Adapter", "createFragment: ActivityFragment" + i);
                return new ActivityFragment();

            case 2:
                Log.e("MyViewPager2Adapter", "createFragment: FavouriteFragment" + i);
                return new FavouriteFragment();
            case 3:
                Log.e("MyViewPager2Adapter", "createFragment: AccountFragment" + i);
                return new AccountFragment();
            default:
                Log.e("MyViewPager2Adapter", "createFragment: HomeFragment" + i);
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
