package com.mindiii.alkasilverlake.Interface;

import com.mindiii.alkasilverlake.responce.ProductListResponce;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by mindiii on 13/11/18.
 */

public interface Reginterface {
    @Multipart
    @POST("userRegistration")
    Call<ResponseBody> createuser(

            @Part("fullName") RequestBody fullName,
            @Part("email") RequestBody email,
            @Part("password") RequestBody password

    );


    @GET("product/product_list")
    Call<ProductListResponce> header(@Header("authToken") String lang);


    @POST("userLogin")
    @FormUrlEncoded
    Call<ResponseBody> login(@Field("email") String email,
                             @Field("password") String password);
}


