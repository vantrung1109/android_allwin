package com.base.mvvm.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.ViewModelProviderFactory;
import com.base.mvvm.data.Repository;
import com.base.mvvm.di.scope.FragmentScope;
import com.base.mvvm.ui.base.BaseFragment;
import com.base.mvvm.ui.fragment.account.AccountFragmentViewModel;
import com.base.mvvm.ui.fragment.activity.ActivityFragmentViewModel;
import com.base.mvvm.ui.fragment.favourite.FavouriteFragmentViewModel;
import com.base.mvvm.ui.fragment.home.HomeFragmentViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class FragmentModule {
    private BaseFragment<?, ?> fragment;
    public FragmentModule(BaseFragment<?, ?> fragment) {
        this.fragment = fragment;
    }

    @Named("access_token")
    @Provides
    @FragmentScope
    String provideToken(Repository repository){
        return repository.getToken();
    }

    @Provides
    @FragmentScope
    HomeFragmentViewModel provideHomeFragmentViewModel(Repository repository, Context application) {
        Supplier<HomeFragmentViewModel> supplier = () -> new HomeFragmentViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<HomeFragmentViewModel> factory = new ViewModelProviderFactory<>(HomeFragmentViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(HomeFragmentViewModel.class);
    }
    @Provides
    @FragmentScope
    ActivityFragmentViewModel provideActivityFragmentViewModel(Repository repository, Context application) {
        Supplier<ActivityFragmentViewModel> supplier = () -> new ActivityFragmentViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<ActivityFragmentViewModel> factory = new ViewModelProviderFactory<>(ActivityFragmentViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(ActivityFragmentViewModel.class);
    }

    @Provides
    @FragmentScope
    FavouriteFragmentViewModel provideFavouriteFragmentViewModel(Repository repository, Context application) {
        Supplier<FavouriteFragmentViewModel> supplier = () -> new FavouriteFragmentViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<FavouriteFragmentViewModel> factory = new ViewModelProviderFactory<>(FavouriteFragmentViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(FavouriteFragmentViewModel.class);
    }

    @Provides
    @FragmentScope
    AccountFragmentViewModel provideAccountFragmentViewModel(Repository repository, Context application) {
        Supplier<AccountFragmentViewModel> supplier = () -> new AccountFragmentViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<AccountFragmentViewModel> factory = new ViewModelProviderFactory<>(AccountFragmentViewModel.class, supplier);
        return new ViewModelProvider(fragment, factory).get(AccountFragmentViewModel.class);
    }
}
