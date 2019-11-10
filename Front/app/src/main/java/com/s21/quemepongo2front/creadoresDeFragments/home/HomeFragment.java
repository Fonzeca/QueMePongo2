package com.s21.quemepongo2front.creadoresDeFragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.ClimaActualRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.SugerenciaRs;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private SugerenciaRs sugerencia;
    private String climaActual;
    private TextView txtViewsugerencia,textViewDescripcion;
    private ClimaActualRs climaActualRs;
    private ArrayList<String> ciudadesUsuario;
    private ArrayAdapter<String> adapter;
    private  ArrayList<CiudadRs> ciudadesRsRecibe;
    private ImageView clima;
    private ClimaActualRs data;
    Spinner spinnerHome;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mostrarCiudadesDeUsuario();

        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ClimaActualRs> call = restClient.recibirPronostico(3860259, MainActivity.token);

        call.enqueue(new Callback<ClimaActualRs>() {
            public void onResponse(Call<ClimaActualRs> call, Response<ClimaActualRs> response) {
                if (response.isSuccessful()){
                    data =response.body();

                    TextView temp_actual = getView().findViewById(R.id.temperatura_actual);
                    int temp = (int) Math.round(data.getTemperatura());
                    temp_actual.setText( temp+"°C");
                    TextView ubicacion = getView().findViewById(R.id.textViewUbicacion);
                    ubicacion.setText(getText(R.string.ubicacion)+data.getCiudadNombre());

                    TextView viento = getView().findViewById(R.id.textViento);
                    int numeroViento = (int)Math.round(data.getViento());
                    viento.setText("Viento: "+ numeroViento +" km/h");

                    TextView textHumedad= getView().findViewById(R.id.textHumedad);
                    textHumedad.setText("Humedad: "+data.getHumedad()+"%");
                    climaActual = data.getNombreClima();
                    textViewDescripcion.setText(climaActual);
                    mostrarIcono(climaActual);
                }
            }
            public void onFailure(Call<ClimaActualRs> call, Throwable t) {
            }
        });

        mostrarsugerencia();
        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        climaActualRs = MainActivity.climapredeterminado;
        textViewDescripcion = getActivity().findViewById(R.id.textViewDescripcionClima);
        spinnerHome = getActivity().findViewById(R.id.spinnerhome);
        clima = getActivity().findViewById(R.id.imageViewClima);
        mostrarCiudadesDeUsuario();
        mostrarIcono(climaActual);
    }

    public void mostrarsugerencia(){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        Call<SugerenciaRs> call= restClient.recibirsugerencia(MainActivity.token, 3860259);
        call.enqueue(new Callback<SugerenciaRs>() {

            public void onResponse(Call<SugerenciaRs> call, Response<SugerenciaRs> response) {
                if(response.isSuccessful()){
                    sugerencia = response.body();
                    setearsugerencia();
                }else{
                    Toast.makeText(getActivity(), "Error en el response", Toast.LENGTH_SHORT).show();
                    Log.e("responsedemierda", ""+ response.errorBody());
                }
            }
            public void onFailure(Call<SugerenciaRs> call, Throwable t) {
            }
        });
    }

    public void setearsugerencia( ){

        txtViewsugerencia= getActivity().findViewById(R.id.txtViewSugerencia);

        txtViewsugerencia.setText(txtViewsugerencia.getText()+" "+sugerencia.getSugerencia()+"  En prueba") ;
    }

    private void cargarListaCiudadesUsuario(ArrayList<CiudadRs> listaobjetos){
        ciudadesUsuario = new ArrayList<String>();

        for (int i = 0; i <listaobjetos.size(); i++) {
            ciudadesUsuario.add((listaobjetos.get(i)).getNombre()+", "+ listaobjetos.get(i).getPais());
        }
        adapter = new ArrayAdapter<>(getActivity(),R.layout.support_simple_spinner_dropdown_item,ciudadesUsuario);

        spinnerHome.setAdapter(adapter);
    }

    private void mostrarCiudadesDeUsuario(){

        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ArrayList<CiudadRs>> ubicaciones = restClient.misCiudades(MainActivity.token);

        ubicaciones.enqueue(new Callback<ArrayList<CiudadRs>>() {

            public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ciudadesRsRecibe = response.body();
                        cargarListaCiudadesUsuario( ciudadesRsRecibe);
                    } else {

                        Toast.makeText(getActivity() , "Ocurrio un error: No hay ciudades cargadas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Ocurrio un error " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }
            public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {
            }
        });
    }
    public void mostrarIcono(String estado){
        clima= getActivity().findViewById(R.id.imageViewClima);
        String c1="Clear", c2="Rain",c3="Clouds",c4="Thunderstorm";
       if(c1.equals(estado)){
            clima.setImageResource(R.mipmap.contrast);
       }else if(c2.equals(estado)){
           clima.setImageResource(R.mipmap.rain);
       }else if (c3.equals(estado)){
           clima.setImageResource(R.mipmap.clouds);
       }else if (c4.equals(estado)){
           clima.setImageResource(R.mipmap.storm);
       }
    }
   /* public String recuperaricono(){
        String nombreclima;
        nombreclima = ciudadesRsRecibe.get(spinnerHome.getSelectedItemId()).;
        return nombreclima;
    }*/
}



