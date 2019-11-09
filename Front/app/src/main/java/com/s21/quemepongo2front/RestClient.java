//Interfaz donde se listan los endpoints de la API
package com.s21.quemepongo2front;

import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.ClimaActualRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.PreferenciaRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.SugerenciaRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.LoginRq;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.PreferenciaRq;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.UsuarioRq;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//Interfaz donde se listan los endpoints de la API
    public interface RestClient {
    @GET("Pronostico?")
    Call<ClimaActualRs> recibirPronostico(@Query("idCiudad") int idCiudad, @Query("token") String token);

    @GET("listarCiudades")
    Call <CiudadRs> listarCiudades(@Query("Token")String token);

    @GET("ObtenerCiudad")
    Call <ArrayList<CiudadRs>> obtenerCiudad(@Query("q")String nombreCiudad, @Query("token")String token);

    @POST("CrearUsuario")
    Call <LoginRs> crearUsuario (@Body UsuarioRq crearusuario);

    @POST("ActualizarPreferencias")
    Call<PreferenciaRs> actualizarPreferencias(@Query("token") String token, @Body PreferenciaRq preferenciaRq);

    @GET("ObtenerPreferencias")
    Call<PreferenciaRs> obtenerPreferencias(@Query("token") String token);

    @POST("Login")
    Call<LoginRs> loginUsuario(@Body LoginRq logeo);

    @GET ("MisCiudades")
    Call<ArrayList<CiudadRs>> misCiudades(@Query("token")String token);

    @GET ("ObtenerSugerencia?")
    Call<SugerenciaRs> recibirsugerencia(@Query("token")String token, @Query("ciudadId")int idCiudad );

    @POST("AgregarCiudad")
    Call<Void> agregarCiudad(@Query("ciudadId") int idCiudad,@Query("token") String token);


    @DELETE("QuitarCiudad")
    Call<Void> borrarciudad(@Query("ciudadId")int idCiudad,@Query("token")String token);

}
