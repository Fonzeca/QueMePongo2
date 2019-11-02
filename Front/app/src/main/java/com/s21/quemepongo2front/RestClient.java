package com.s21.quemepongo2front;

import retrofit2.Call;
import retrofit2.http.GET;

//Interfaz donde se listan los endpoints de la API
public interface RestClient {
    @GET("Pronostico?IdCiudad=3860259")
    Call<PronosticoRs> getData();

}
