package com.s21.quemepongo2front;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api {

    private static Retrofit retrofit = null;
    //private static String apiUrl = "http://181.31.108.164:5599/";
//    private static String apiUrl = "http://192.167.0.7:5599/"; PC DIEGO
    private static String apiUrl ="http://181.105.112.123:5599"; //PC JERE

    public static Retrofit getRetrofit(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(apiUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }



}
