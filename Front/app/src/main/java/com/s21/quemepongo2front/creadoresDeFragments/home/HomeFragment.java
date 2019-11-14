package com.s21.quemepongo2front.creadoresDeFragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private int idCiudad;
    private TextView txtViewsugerencia,textViewDescripcion;
    private ClimaActualRs climaActualRs;
    private ImageView clima;
    private ClimaActualRs data;
    private Spinner spinnerHome;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);






















        return root;


    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerHome= getActivity().findViewById(R.id.spinnerhome);
        textViewDescripcion = getActivity().findViewById(R.id.textViewDescripcionClima);
        clima = getActivity().findViewById(R.id.imageViewClima);
        climaActualRs = MainActivity.climapredeterminado;
        cargarListaCiudadesUsuario(MainActivity.ciudadesRsRecibe);

        spinnerHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                RestClient restClient = Api.getRetrofit().create(RestClient.class);
                CiudadRs rs = (CiudadRs) spinnerHome.getSelectedItem();

                Call<ClimaActualRs> call = restClient.recibirPronostico(rs.getId(), MainActivity.token);

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
                        }else{
                            Toast.makeText(getActivity(), "error en el cargar pantalla", Toast.LENGTH_SHORT).show();
                        }
                    }
                    public void onFailure(Call<ClimaActualRs> call, Throwable t) {
                    }
                });
                mostrarsugerencia(rs.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }


    public void mostrarsugerencia(int id){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        Call<SugerenciaRs> call= restClient.recibirsugerencia(MainActivity.token, id);
        call.enqueue(new Callback<SugerenciaRs>() {

            public void onResponse(Call<SugerenciaRs> call, Response<SugerenciaRs> response) {
                if(response.isSuccessful()){
                    sugerencia = response.body();
                    setearsugerencia();
                }else{
                    Toast.makeText(getActivity(), "Error en el response de mostrar sugerencia", Toast.LENGTH_SHORT).show();
                    Log.e("responsedemierda", ""+ response.errorBody());
                }
            }
            public void onFailure(Call<SugerenciaRs> call, Throwable t) {
            }
        });
    }

    public void setearsugerencia( ){

        txtViewsugerencia= getActivity().findViewById(R.id.txtViewSugerencia);
//TODO: en prueba boyz
        txtViewsugerencia.setText(txtViewsugerencia.getText()+" "+sugerencia.getSugerencia()+"  En prueba") ;
    }

    private void cargarListaCiudadesUsuario(ArrayList<CiudadRs> listaobjetos){
        ArrayAdapter<CiudadRs> adapter = new ArrayAdapter(this.getContext(),android.R.layout.simple_spinner_dropdown_item,listaobjetos);
        spinnerHome.setAdapter(adapter);
        idCiudad= 3860259;
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



