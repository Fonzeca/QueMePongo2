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

import com.s21.quemepongo2front.objetosDeLaApi.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.creadoresDeFragments.ubicaciones.UbicacionesFragment;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.ClimaActualRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.SugerenciaRs;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private SugerenciaRs sugerencia;
    private String climaActual;
    private TextView txtViewsugerencia;
    private ImageView clima;
    private ClimaActualRs data;
    private Spinner spinnerHome;
    private ArrayAdapter<CiudadRs> adapterSpinner;
    private View layouthome;

    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    //TODO AGREGAR DIFERENTES FONDOS SEGUN LA HORA DEL DIA
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        spinnerHome = getActivity().findViewById(R.id.spinnerhome);
        clima = getActivity().findViewById(R.id.imageViewClima);
        txtViewsugerencia = getActivity().findViewById(R.id.txtViewSugerencia);

        spinnerHome.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mostrarTodo();
            }
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mostrarCiudadesDeUsuario();
    }

    public void mostrarSugerencia(int id){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        Call<SugerenciaRs> call= restClient.recibirsugerencia(MainActivity.token, id);
        call.enqueue(new Callback<SugerenciaRs>() {

            public void onResponse(Call<SugerenciaRs> call, Response<SugerenciaRs> response) {
                if(response.isSuccessful()){
                    sugerencia = response.body();
                    setearSugerencia();
                }else{
                    Toast.makeText(getActivity(), "Error en el response de mostrar sugerencia", Toast.LENGTH_SHORT).show();
                    Log.e("responsedemierda", ""+ response.errorBody());
                }
            }
            public void onFailure(Call<SugerenciaRs> call, Throwable t) {
            }
        });
    }

    public void setearSugerencia( ){
        txtViewsugerencia.setText(sugerencia.getSugerencia()) ;
    }

    public void mostrarIcono(String estado){
        clima= getActivity().findViewById(R.id.imageViewClima);
        layouthome = getActivity().findViewById(R.id.layout_home);
        String c1="Clear", c2="Rain",c3="Clouds",c4="Thunderstorm";
        if(c1.equals(estado)){
            layouthome.setBackgroundResource(R.mipmap.wp_sun);
            clima.setImageResource(R.mipmap.contrast);
        }else if(c2.equals(estado)){
            layouthome.setBackgroundResource(R.mipmap.wp_rain);

            clima.setImageResource(R.mipmap.rain);
        }else if (c3.equals(estado)){
            layouthome.setBackgroundResource(R.mipmap.wp_clouds);

            clima.setImageResource(R.mipmap.clouds);
        }else if (c4.equals(estado)){
            layouthome.setBackgroundResource(R.mipmap.wp_thunderstorm2);
            clima.setImageResource(R.mipmap.storm);
        }
    }

    public void mostrarTodo(){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        CiudadRs rs = (CiudadRs) spinnerHome.getSelectedItem();
        if(rs == null){
            if(adapterSpinner.getCount() != 0){
                rs = adapterSpinner.getItem(0);
            }else {
                UbicacionesFragment fr = new UbicacionesFragment();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, fr).addToBackStack(null).commit();
                Toast.makeText(getActivity(), "Por favor, agregue una ciudad.", Toast.LENGTH_LONG).show();
                return;
            }
        }

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
                    mostrarIcono(climaActual);
                }else{
                    Toast.makeText(getActivity(), "error en el cargar pantalla", Toast.LENGTH_SHORT).show();
                }
            }
            public void onFailure(Call<ClimaActualRs> call, Throwable t) {
            }
        });
        mostrarSugerencia(rs.getId());
    }

    private void mostrarCiudadesDeUsuario(){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ArrayList<CiudadRs>> ubicaciones = restClient.misCiudades(MainActivity.token);
        ubicaciones.enqueue(new Callback<ArrayList<CiudadRs>>() {
            public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        adapterSpinner = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, response.body());
                        if(spinnerHome.getAdapter() == null){
                            spinnerHome.setAdapter(adapterSpinner);
                        }
                        adapterSpinner.notifyDataSetChanged();
                        mostrarTodo();
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
}



