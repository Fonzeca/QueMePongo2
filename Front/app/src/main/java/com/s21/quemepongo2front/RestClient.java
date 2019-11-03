//Interfaz donde se listan los endpoints de la API
package com.s21.quemepongo2front;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

//Interfaz donde se listan los endpoints de la API
public interface RestClient {
    @GET("Pronostico?IdCiudad=3860259")
    Call<PronosticoRs> getData();

    @POST("CrearUsuario")
    Call crearUsuario (@Body UsuarioRq crearusuario);
}
