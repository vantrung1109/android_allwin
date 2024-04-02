package com.base.mvvm.ui.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.viewpager2.widget.ViewPager2;

import com.base.mvvm.BR;
import com.base.mvvm.R;
import com.base.mvvm.databinding.ActivityMainBinding;
import com.base.mvvm.di.component.ActivityComponent;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.account.AccountFragment;
import com.base.mvvm.utils.MyViewPager2Adapter;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> {
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

    private MyViewPager2Adapter mMyViewPager2Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        mActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mActivityMainBinding.getRoot());

        mMyViewPager2Adapter = new MyViewPager2Adapter(this);
        mActivityMainBinding.viewPager2.setAdapter(mMyViewPager2Adapter);


        mActivityMainBinding.viewPager2.setCurrentItem(1);
        mActivityMainBinding.bottomNavigation.getMenu().getItem(1).setChecked(true);
        mActivityMainBinding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mActivityMainBinding.bottomNavigation.getMenu().getItem(position).setChecked(true);
            }
        });

        mActivityMainBinding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_home) {
                mActivityMainBinding.viewPager2.setCurrentItem(0);
            } else if (item.getItemId() == R.id.menu_activity) {
                mActivityMainBinding.viewPager2.setCurrentItem(1);
            } else if (item.getItemId() == R.id.menu_favourite) {
                mActivityMainBinding.viewPager2.setCurrentItem(2);
            } else if (item.getItemId() == R.id.menu_account) {
//                    String accessToken = getIntent().getStringExtra("access_token");
//                    Bundle bundle = new Bundle();
//                    bundle.putString("access_token", accessToken);
//                    mActivityMainBinding.viewPager2.setAdapter(
//                            new MyViewPager2Adapter(this, bundle)
//                    );
                mActivityMainBinding.viewPager2.setCurrentItem(3);
            }
            return true;
        });
    }
}
