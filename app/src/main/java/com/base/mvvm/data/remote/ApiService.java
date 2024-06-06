package com.base.mvvm.data.remote;

import com.base.mvvm.data.model.api.ResponseListObj;
import com.base.mvvm.data.model.api.ResponseWrapper;
import com.base.mvvm.data.model.api.address_by_placeid.AddressByPlaceId;
import com.base.mvvm.data.model.api.api_search.SearchPlaceApi;
import com.base.mvvm.data.model.api.distance.DistanceResponse;
import com.base.mvvm.data.model.api.request.BookingCreateRequest;
import com.base.mvvm.data.model.api.request.LoginRequest;
import com.base.mvvm.data.model.api.request.SignUpRequest;
import com.base.mvvm.data.model.api.request.SigninRequest;
import com.base.mvvm.data.model.api.request.UpdateProfileRequest;
import com.base.mvvm.data.model.api.response.booking.MyBookingResponse;

import com.base.mvvm.data.model.api.response.booking.booking_create.BookingCreateResponse;
import com.base.mvvm.data.model.api.response.customer.AccountResponse;
import com.base.mvvm.data.model.api.response.customer.LoginResponse;
import com.base.mvvm.data.model.api.response.customer.SigninResponse;
import com.base.mvvm.data.model.api.response.customer.UploadFileResponse;
import com.base.mvvm.data.model.api.response.discount.DiscountResponse;
import com.base.mvvm.data.model.api.response.service.ServiceResponse;

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
                                                                                 @Query("page") Integer page,
                                                                                 @Query("size") Integer size,
                                                                                 @Query("state") Integer state);

    @GET("/v1/promotion/client-list")
    @Headers({"IgnoreAuth: 1"})
    Observable<ResponseWrapper<ResponseListObj<DiscountResponse>>> getDiscount(@Query("endDate") String endDate,
                                                                               @Query("startDate") String startDate,
                                                                               @Query("page") Integer page,
                                                                               @Query("size") Integer size,
                                                                               @Query("state") Integer state);

    @GET("place/queryautocomplete/json")
    @Headers({"isSearchPlaces: 1"})
    Observable<SearchPlaceApi> getSearchPlacesGG(@Query("input") String input,
                                           @Query("key") String key);

    @GET("geocode/json")
    @Headers({"isSearchPlaces: 1"})
    Observable<AddressByPlaceId> getDetailAddress(@Query("place_id") String placeId,
                                                  @Query("key") String key);


    @GET("distancematrix/json")
    @Headers({"isSearchPlaces: 1"})
    Observable<DistanceResponse> getDistance(@Query("destinations") String destinations,
                                             @Query("origins") String origins,
                                                  @Query("key") String key);

    @GET("directions/json")
    @Headers({"isSearchPlaces: 1"})
    Observable<DistanceResponse> getDirection(@Query("origin") String origin,
                                             @Query("destination") String destination,
                                             @Query("key") String key);

    @POST("v1/booking/create")
    Observable<ResponseWrapper<BookingCreateResponse>> createBooking(@Body BookingCreateRequest request);


    @GET("v1/user-service/auto-complete")
    Observable<ResponseWrapper<ResponseListObj<ServiceResponse>>> getServices();

}
