package com.mindiii.alkasilverlake.connection;

import com.mindiii.alkasilverlake.Interface.Reginterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String Base_Url = "http://dev.mindiii.com/alka_silver/service/";
    private static RetrofitClient minstance;
    private Retrofit retrofit;

    private RetrofitClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getInstance() {
        if (minstance == null) {
            minstance = new RetrofitClient();
        }
        return minstance;
    }

    public Reginterface getApi() {
        return retrofit.create(Reginterface.class);
    }


}
