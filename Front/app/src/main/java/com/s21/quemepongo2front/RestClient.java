//Interfaz donde se listan los endpoints de la API
package com.s21.quemepongo2front;

import com.s21.quemepongo2front.ui.ObjetosRS.CiudadRs;
import com.s21.quemepongo2front.ui.ObjetosRS.ClimaActualRs;
import com.s21.quemepongo2front.ui.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.ui.ObjetosRS.PreferenciaRs;
import com.s21.quemepongo2front.ui.ObjetosRq.LoginRq;
import com.s21.quemepongo2front.ui.ObjetosRq.UsuarioRq;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;
import retrofit2.http.Query;

//Interfaz donde se listan los endpoints de la API
    public interface RestClient {
    @GET("Pronostico?")
    Call<ClimaActualRs> recibirPronostico(@Query("idCiudad") int idCiudad, @Query("token") String token);

    @GET("listarCiudades")
    Call <CiudadRs> listarCiudades(@Query("Token")String token);

    @POST("CrearUsuario")
    Call <LoginRs> crearUsuario (@Body UsuarioRq crearusuario);

    @POST("ActualizarPreferencias")
    Call<PreferenciaRs> actualizarPreferencias(@Query("token") String token, @Body PreferenciaRs preferenciaRq);

    @POST("Login")
    Call<LoginRs> loginUsuario(@Body LoginRq logeo);

    @GET ("MisCiudades")
    Call<List<CiudadRs>> misCiudades(@Query("token")String token);

    @POST("AgregarCiudad")
    Call<Void> agregarCioudad(@Query("ciudadId") int idCiudad,@Query("token") String token);

    @DELETE("QuitarCiudad")
    Call<Void> borrarciudad(@Query("ciudadId")int idCiudad,@Query("token")String token);

}
