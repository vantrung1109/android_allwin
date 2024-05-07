package com.base.mvvm.di.component;

import com.base.mvvm.di.module.ActivityModule;
import com.base.mvvm.di.scope.ActivityScope;
import com.base.mvvm.ui.fragment.home.cancel_trip.CancelTripActivity;
import com.base.mvvm.ui.fragment.home.cancel_trip.cancel_success.CancelTripSuccessActivity;
import com.base.mvvm.ui.fragment.home.discount.DiscountActivity;
import com.base.mvvm.ui.fragment.home.note.NoteActivity;
import com.base.mvvm.ui.fragment.home.payment_method.PaymentMethodActivity;
import com.base.mvvm.ui.home.HomeActivity;
import com.base.mvvm.ui.fragment.home.maps.MapActivity;
import com.base.mvvm.ui.home_introduce.HomeIntroduceActivity;
import com.base.mvvm.ui.home_splash.HomeSplashActivity;
import com.base.mvvm.ui.login.LoginActivity;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.ui.my_booking_detail.MyBookingDetailActivity;
import com.base.mvvm.ui.signin.SignInActivity;
import com.base.mvvm.ui.signup.SignUpActivity;
import com.base.mvvm.ui.update_account.UpdateAccountActivity;

import dagger.Component;

@ActivityScope
@Component(modules = {ActivityModule.class}, dependencies = AppComponent.class)
public interface ActivityComponent {

    void inject(LoginActivity activity);

    void inject(MainActivity activity);
    void inject(SignInActivity activity);
    void inject(SignUpActivity activity);
    void inject(HomeActivity activity);
    void inject(UpdateAccountActivity activity);
    void inject(HomeSplashActivity activity);
    void inject(HomeIntroduceActivity activity);
    void inject(MyBookingDetailActivity activity);
    void inject (MapActivity activity);

    void inject (PaymentMethodActivity activity);
    void inject (DiscountActivity activity);
    void inject (NoteActivity activity);
    void inject (CancelTripActivity activity);
    void inject (CancelTripSuccessActivity viewModel);

}

