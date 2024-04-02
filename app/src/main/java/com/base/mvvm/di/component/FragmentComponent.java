package com.base.mvvm.di.component;

import com.base.mvvm.di.module.FragmentModule;
import com.base.mvvm.di.scope.FragmentScope;
import com.base.mvvm.ui.fragment.account.AccountFragment;
import com.base.mvvm.ui.fragment.activity.ActivityFragment;
import com.base.mvvm.ui.fragment.favourite.FavouriteFragment;
import com.base.mvvm.ui.fragment.home.HomeFragment;

import dagger.Component;

@FragmentScope
@Component(modules = {FragmentModule.class}, dependencies = AppComponent.class)
public interface FragmentComponent {
    void inject(HomeFragment fragment);
    void inject(ActivityFragment fragment);
    void inject(FavouriteFragment fragment);
    void inject(AccountFragment fragment);

}
