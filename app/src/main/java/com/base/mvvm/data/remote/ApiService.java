package com.base.mvvm.data.remote;

import com.base.mvvm.data.model.api.ResponseWrapper;
import com.base.mvvm.data.model.api.request.LoginRequest;
import com.base.mvvm.data.model.api.request.SignUpRequest;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.data.model.api.response.AccountResponse;
import com.base.mvvm.data.model.api.response.LoginResponse;
import com.base.mvvm.data.model.api.response.SignUpResponse;
import com.base.mvvm.data.model.api.response.SigninResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("v1/account/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<LoginResponse>> login(@Body LoginRequest request);
    @GET("v1/account/profile")
    Observable<ResponseWrapper<LoginResponse>> profile();

    @GET("v1/customer/profile")
    Observable<ResponseWrapper<AccountResponse>> profile2();

    @POST("v1/customer/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<SigninResponse>> login2(@Body SigninRequest request);

    @POST("v1/customer/register")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper> signup(@Body SignUpRequest request);

}
