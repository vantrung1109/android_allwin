package com.base.mvvm.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.databinding.BindingMethod;
import androidx.databinding.BindingMethods;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.constant.Constants;
import com.base.mvvm.databinding.ActivityMainBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.account.AccountFragment;
import com.base.mvvm.ui.fragment.activity.ActivityFragment;
import com.base.mvvm.ui.fragment.favourite.FavouriteFragment;
import com.base.mvvm.ui.fragment.home.HomeFragment;
import com.base.mvvm.ui.signin.SignInActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import lombok.NonNull;


public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
    public Fragment activeFragment = new Fragment();
    private AccountFragment accountFragment;
    private ActivityFragment activityFragment;
    private FavouriteFragment favouriteFragment;
    private HomeFragment homeFragment;
    private int currentId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        viewBinding.setA(this);
//        viewBinding.setVm(viewModel);


        switchToHomeFragment(R.id.menu_home);
        viewBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            handleNavigationItemSelected(item);
            return true;
        });


    }
    @Override
    protected void onResume() {
        super.onResume();

    }
    public boolean handleNavigationItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()) {
            case R.id.menu_home:
                switchToHomeFragment(item.getItemId());
                return true;
            case R.id.menu_activity:
                switchToActivityFragment(item.getItemId());
                return true;
            case R.id.menu_favourite:
                switchToFavouriteFragment(item.getItemId());
                return true;
            case R.id.menu_account:
                switchToAccountFragment(item.getItemId());
                return true;
            default:
                return false;
        }

    }

    private void switchToAccountFragment(int id) {
        if (accountFragment == null){
            accountFragment = new AccountFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_fragment, accountFragment,Constants.ACCOUNT_FRAGMENT).hide(activeFragment).commit();
        }
        else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(activeFragment).show(accountFragment).commit();
        }

        activeFragment = accountFragment;
        currentId = id;
    }

    private void switchToFavouriteFragment(int id) {
        if (favouriteFragment == null){
            favouriteFragment = new FavouriteFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_fragment, favouriteFragment,Constants.FAVOURITE_FRAGMENT).hide(activeFragment).commit();
        }
        else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(activeFragment).show(favouriteFragment).commit();
        }
        activeFragment = favouriteFragment;
        currentId = id;
    }

    private void switchToActivityFragment(int id) {
        if (activityFragment == null){
            activityFragment = new ActivityFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_fragment, activityFragment,Constants.ACTIVITY_FRAGMENT).hide(activeFragment).commit();
        }
        else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(activeFragment).show(activityFragment).commit();
        }
        activeFragment = activityFragment;
        currentId = id;
    }
    private void switchToHomeFragment(int id) {
        if (homeFragment == null){
            homeFragment = new HomeFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.main_fragment, homeFragment, Constants.HOME_FRAGMENT).hide(activeFragment).commit();
        }
        else{
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.hide(activeFragment).show(homeFragment).commit();
        }
//        viewBinding.bottomNavigation.setSelectedItemId(R.id.menu_home);
        activeFragment = homeFragment;
        currentId = id;
    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public int getBindingVariable() {
        return BR.vm;
    }

    @Override
    public void performDependencyInjection(ActivityComponent buildComponent) {
        buildComponent.inject(this);
    }

    ActivityMainBinding mActivityMainBinding;

//    private MyViewPager2Adapter mMyViewPager2Adapter;


    //        mMyViewPager2Adapter = new MyViewPager2Adapter(this);
//        mActivityMainBinding.viewPager2.setAdapter(mMyViewPager2Adapter);

//        mActivityMainBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position);
//                mActivityMainBinding.bottomNavigation.getMenu().getItem(position).setChecked(true);
//            }
//        });

//        mActivityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
//            if (item.getItemId() == R.id.menu_home) {
//                mActivityMainBinding.viewPager2.setCurrentItem(0);
//            } else if (item.getItemId() == R.id.menu_activity) {
//                mActivityMainBinding.viewPager2.setCurrentItem(1);
//            } else if (item.getItemId() == R.id.menu_favourite) {
//                mActivityMainBinding.viewPager2.setCurrentItem(2);
//            } else if (item.getItemId() == R.id.menu_account) {
//                mActivityMainBinding.viewPager2.setCurrentItem(3);
//            }
//            return true;
//        });
//        mActivityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
    //            if (item.getItemId() == R.id.menu_home) {
//                Fragment fragment = getFragmentByNameClass(HomeFragment.class, fragmentList);
//                if (fragment != null)
//                    replaceFragment(fragment);
//                else{
//                    replaceFragment(new HomeFragment());
//                    fragmentList.add(new HomeFragment());
//                }
//
//            } else if (item.getItemId() == R.id.menu_activity) {
//                Fragment fragment = getFragmentByNameClass(ActivityFragment.class, fragmentList);
//                if (fragment != null)
//                    replaceFragment(fragment);
//                else{
//                    replaceFragment(new ActivityFragment());
//                    fragmentList.add(new ActivityFragment());
//                }
//
//            } else if (item.getItemId() == R.id.menu_favourite) {
//                Fragment fragment = getFragmentByNameClass(FavouriteFragment.class, fragmentList);
//                if (fragment != null)
//                    replaceFragment(fragment);
//                else{
//                    replaceFragment(new FavouriteFragment());
//                    fragmentList.add(new FavouriteFragment());
//                }
//
//            } else if (item.getItemId() == R.id.menu_account) {
//                Fragment fragment = getFragmentByNameClass(AccountFragment.class, fragmentList);
//                if (fragment != null)
//                    replaceFragment(fragment);
//                else{
//                    replaceFragment(new AccountFragment());
//                    fragmentList.add(new AccountFragment());
//                }
//
//            }
//            Log.e("MainActivity", "Fragmentlist: " + fragmentList.toString());
//            return true;
//        });
}
