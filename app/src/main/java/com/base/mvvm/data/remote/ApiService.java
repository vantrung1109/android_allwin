package com.base.mvvm.data.remote;

import com.base.mvvm.data.model.api.ResponseListObj;
import com.base.mvvm.data.model.api.ResponseWrapper;
import com.base.mvvm.data.model.api.request.LoginRequest;
import com.base.mvvm.data.model.api.request.SignUpRequest;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.data.model.api.request.UpdateProfileRequest;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;

import com.base.mvvm.data.model.api.response.customer.AccountResponse;
import com.base.mvvm.data.model.api.response.customer.LoginResponse;
import com.base.mvvm.data.model.api.response.customer.SigninResponse;
import com.base.mvvm.data.model.api.response.customer.UploadFileResponse;

import io.reactivex.rxjava3.core.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiService {

    @POST("v1/account/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<LoginResponse>> login(@Body LoginRequest request);
    @GET("v1/account/profile")
    Observable<ResponseWrapper<LoginResponse>> profile();


    @GET("v1/customer/profile")
    Observable<ResponseWrapper<AccountResponse>> profile2();
    @GET("v1/customer/profile")
    Observable<ResponseWrapper<AccountResponse>> getProfile();
    @PUT("v1/customer/update-profile")
    Observable<ResponseWrapper<String>> updateProfile(@Body UpdateProfileRequest request);
    @POST("/v1/file/upload")
    @Headers({"isMedia:1"})
    Observable<ResponseWrapper<UploadFileResponse>> uploadFile(@Body MultipartBody requestBody);

    @POST("v1/customer/login")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<SigninResponse>> login2(@Body SigninRequest request);

    @POST("v1/customer/register")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper> signup(@Body SignUpRequest request);

    @GET("/v1/booking/my-booking")
    Observable<ResponseWrapper<ResponseListObj<MyBookingResponse>>> getMyBooking(@Query("endDate") String endDate,
                                                                                 @Query("startDate") String startDate,
                                                                                 @Query("page") Integer pageNumber,
                                                                                 @Query("size") Integer pageSize,
                                                                                 @Query("state") Integer state);

}
