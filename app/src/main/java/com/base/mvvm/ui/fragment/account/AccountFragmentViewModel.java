package com.base.mvvm.ui.fragment.account;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import androidx.databinding.ObservableField;

import com.base.mvvm.MVVMApplication;
import com.base.mvvm.R;
import com.base.mvvm.data.Repository;
import com.base.mvvm.data.local.prefs.PreferencesService;
import com.base.mvvm.data.model.api.response.AccountResponse;
import com.base.mvvm.ui.base.BaseFragmentViewModel;
import com.base.mvvm.ui.home.HomeActivity;
import com.base.mvvm.ui.main.MainActivity;
import com.base.mvvm.ui.update_account.UpdateAccountActivity;
import com.base.mvvm.utils.NetworkUtils;

import java.util.concurrent.atomic.AtomicReference;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class AccountFragmentViewModel extends BaseFragmentViewModel {

    public ObservableField<AccountResponse> profile = new ObservableField<>(new AccountResponse());
    public AccountFragmentViewModel(Repository repository, MVVMApplication application) {
        super(repository, application);
        callApiGetProfile();
    }
    public void callApiGetProfile(){

        showLoading();

        compositeDisposable.add(repository.getApiService().profile2()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(throwable ->
                        throwable.flatMap(new Function<Throwable, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(Throwable throwable) throws Throwable {
                                if (NetworkUtils.checkNetworkError(throwable)) {
                                    return application.showDialogNoInternetAccess();
                                }else{
                                    return Observable.error(throwable);
                                }
                            }
                        })
                )
                .subscribe(response -> {
                    if(response.isResult()){
                        profile.set(response.getData());
                        //repository.getSqliteService().insertAccount(profile.get());
                        showSuccessMessage("Get Profile Information Successfully");

                    }else{
                        showErrorMessage(response.getMessage());
                    }
                    hideLoading();
                }, throwable -> {
                    showErrorMessage(application.getResources().getString(R.string.no_internet));
                    hideLoading();
                }));
    }
    public void goToUpdateAccount(){
        Intent intent = new Intent(application, UpdateAccountActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        application.startActivity(intent);
    }
    public void clearToken(){
        repository.getSharedPreferences().removeKey(PreferencesService.KEY_BEARER_TOKEN);
        repository.setToken(null);
    }
    public void doSignout(){
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        Intent intent = new Intent(application.getCurrentActivity(), HomeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        clearToken();
                        application.getCurrentActivity().startActivity(intent);
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(application.getCurrentActivity());
        builder.setMessage("Bạn muốn đăng xuất?").setPositiveButton(application.getCurrentActivity().getString(R.string.confirm), dialogClickListener)
                .setNegativeButton(application.getCurrentActivity().getString(R.string.cancel), dialogClickListener).show();
    }
}
