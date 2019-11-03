//Interfaz donde se listan los endpoints de la API
package com.s21.quemepongo2front;

import com.s21.quemepongo2front.ui.LoginRq;
import com.s21.quemepongo2front.ui.ObjetosRS.ClimaActualRs;
import com.s21.quemepongo2front.ui.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.ui.ObjetosRS.PreferenciaRs;
import com.s21.quemepongo2front.ui.UsuarioRq;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Query;

//Interfaz donde se listan los endpoints de la API
    public interface RestClient {
    @GET("Pronostico?")
    Call<ClimaActualRs> recibirPronostico(@Query("idCiudad") int idCiudad, @Query("token") String token);

    @POST("CrearUsuario")
    Call <Void> crearUsuario (@Body UsuarioRq crearusuario);

    @POST("ActualizarPreferencias")
    Call<Void> actualizarPreferencias(@Body PreferenciaRs preferenciaRq, @Query("token") String token);

    @POST("Login")
    Call<LoginRs> loginUsuario(@Body LoginRq logeo);

}
