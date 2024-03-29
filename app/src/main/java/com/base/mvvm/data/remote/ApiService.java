package com.base.mvvm.data.remote;

import com.base.mvvm.data.model.api.ResponseWrapper;
import com.base.mvvm.data.model.api.request.LoginRequest;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.data.model.api.response.LoginResponse;
import com.base.mvvm.data.model.api.response.SigninResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {

    @POST("v1/account/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<LoginResponse>> login(@Body LoginRequest request);
    @GET("v1/account/profile")
    Observable<ResponseWrapper<LoginResponse>> profile();

    @POST("v1/customer/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<SigninResponse>> login2(@Body SigninRequest request);

}
