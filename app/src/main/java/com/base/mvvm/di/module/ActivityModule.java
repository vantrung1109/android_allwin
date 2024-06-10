package com.base.mvvm.di.module;

import android.content.Context;

import androidx.core.util.Supplier;
import androidx.lifecycle.ViewModelProvider;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.ViewModelProviderFactory;
import com.base.mvvm.data.Repository;
import com.base.mvvm.di.scope.ActivityScope;
import com.base.mvvm.ui.base.BaseActivity;
import com.base.mvvm.ui.fragment.home.booking_done.BookingDoneViewModel;
import com.base.mvvm.ui.fragment.home.cancel_trip.CancelTripViewModel;
import com.base.mvvm.ui.fragment.home.cancel_trip.cancel_success.CancelTripSuccessViewModel;
import com.base.mvvm.ui.fragment.home.discount.DiscountViewModel;
import com.base.mvvm.ui.fragment.home.note.NoteViewModel;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodViewModel;
import com.base.mvvm.ui.home.HomeViewModel;
import com.base.mvvm.ui.fragment.home.maps.MapViewModel;
import com.base.mvvm.ui.home_introduce.HomeIntroduceViewModel;
import com.base.mvvm.ui.home_splash.HomeSplashViewModel;
import com.base.mvvm.ui.login.LoginViewModel;
import com.base.mvvm.ui.main.MainViewModel;
import com.base.mvvm.ui.my_booking_detail.MyBookingDetailViewModel;
import com.base.mvvm.ui.signin.SignInViewModel;
import com.base.mvvm.ui.signup.SignUpViewModel;
import com.base.mvvm.ui.update_account.UpdateAccountViewModel;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {
    private BaseActivity<?, ?> activity;
    public ActivityModule(BaseActivity<?, ?> activity) {
        this.activity = activity;
    }

    @Named("access_token")
    @Provides
    @ActivityScope
    String provideToken(Repository repository){
        return repository.getToken();
    }

    @Provides
    @ActivityScope
    LoginViewModel provideLoginViewModel(Repository repository, Context application) {
        Supplier<LoginViewModel> supplier = () -> new LoginViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<LoginViewModel> factory = new ViewModelProviderFactory<>(LoginViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(LoginViewModel.class);
    }

    @Provides
    @ActivityScope
    MainViewModel provideMainViewModel(Repository repository, Context application) {
        Supplier<MainViewModel> supplier = () -> new MainViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MainViewModel> factory = new ViewModelProviderFactory<>(MainViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MainViewModel.class);
    }

    @Provides
    @ActivityScope
    SignInViewModel provideSignInViewModel(Repository repository, Context application) {
        Supplier<SignInViewModel> supplier = () -> new SignInViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<SignInViewModel> factory = new ViewModelProviderFactory<>(SignInViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SignInViewModel.class);
    }

    @Provides
    @ActivityScope
    SignUpViewModel provideSignUpViewModel(Repository repository, Context application) {
        Supplier<SignUpViewModel> supplier = () -> new SignUpViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<SignUpViewModel> factory = new ViewModelProviderFactory<>(SignUpViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(SignUpViewModel.class);
    }

    @Provides
    @ActivityScope
    HomeViewModel provideHomeViewModel(Repository repository, Context application) {
        Supplier<HomeViewModel> supplier = () -> new HomeViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<HomeViewModel> factory = new ViewModelProviderFactory<>(HomeViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeViewModel.class);
    }
    @Provides
    @ActivityScope
    UpdateAccountViewModel provideUpdateAccountViewModel(Repository repository, Context application) {
        Supplier<UpdateAccountViewModel> supplier = () -> new UpdateAccountViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<UpdateAccountViewModel> factory = new ViewModelProviderFactory<>(UpdateAccountViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(UpdateAccountViewModel.class);
    }
    @Provides
    @ActivityScope
    HomeSplashViewModel provideHomeFirstViewModel(Repository repository, Context application) {
        Supplier<HomeSplashViewModel> supplier = () -> new HomeSplashViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<HomeSplashViewModel> factory = new ViewModelProviderFactory<>(HomeSplashViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeSplashViewModel.class);
    }
    @Provides
    @ActivityScope
    HomeIntroduceViewModel provideHomeIntroduceViewModel(Repository repository, Context application) {
        Supplier<HomeIntroduceViewModel> supplier = () -> new HomeIntroduceViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<HomeIntroduceViewModel> factory = new ViewModelProviderFactory<>(HomeIntroduceViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(HomeIntroduceViewModel.class);
    }

    @Provides
    @ActivityScope
    MyBookingDetailViewModel provideMyBookingDetailViewModel(Repository repository, Context application) {
        Supplier<MyBookingDetailViewModel> supplier = () -> new MyBookingDetailViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MyBookingDetailViewModel> factory = new ViewModelProviderFactory<>(MyBookingDetailViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MyBookingDetailViewModel.class);
    }

    @Provides
    @ActivityScope
    MapViewModel provideMapViewModel(Repository repository, Context application) {
        Supplier<MapViewModel> supplier = () -> new MapViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<MapViewModel> factory = new ViewModelProviderFactory<>(MapViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(MapViewModel.class);
    }
    @Provides
    @ActivityScope
    PaymentMethodViewModel providePaymentMethodViewModel(Repository repository, Context application) {
        Supplier<PaymentMethodViewModel> supplier = () -> new PaymentMethodViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<PaymentMethodViewModel> factory = new ViewModelProviderFactory<>(PaymentMethodViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(PaymentMethodViewModel.class);
    }
    @Provides
    @ActivityScope
    DiscountViewModel provideDiscountViewModel(Repository repository, Context application) {
        Supplier<DiscountViewModel> supplier = () -> new DiscountViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<DiscountViewModel> factory = new ViewModelProviderFactory<>(DiscountViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(DiscountViewModel.class);
    }

    @Provides
    @ActivityScope
    NoteViewModel provideNoteViewModel(Repository repository, Context application) {
        Supplier<NoteViewModel> supplier = () -> new NoteViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<NoteViewModel> factory = new ViewModelProviderFactory<>(NoteViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(NoteViewModel.class);
    }

    @Provides
    @ActivityScope
    CancelTripViewModel provideCancelTripViewModel(Repository repository, Context application) {
        Supplier<CancelTripViewModel> supplier = () -> new CancelTripViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<CancelTripViewModel> factory = new ViewModelProviderFactory<>(CancelTripViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(CancelTripViewModel.class);
    }
    @Provides
    @ActivityScope
    CancelTripSuccessViewModel provideCancelTripSuccessViewModel(Repository repository, Context application) {
        Supplier<CancelTripSuccessViewModel> supplier = () -> new CancelTripSuccessViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<CancelTripSuccessViewModel> factory = new ViewModelProviderFactory<>(CancelTripSuccessViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(CancelTripSuccessViewModel.class);
    }

    @Provides
    @ActivityScope
    BookingDoneViewModel provideBookingDoneViewModel(Repository repository, Context application) {
        Supplier<BookingDoneViewModel> supplier = () -> new BookingDoneViewModel(repository, (MVVMApplication)application);
        ViewModelProviderFactory<BookingDoneViewModel> factory = new ViewModelProviderFactory<>(BookingDoneViewModel.class, supplier);
        return new ViewModelProvider(activity, factory).get(BookingDoneViewModel.class);
    }
}
