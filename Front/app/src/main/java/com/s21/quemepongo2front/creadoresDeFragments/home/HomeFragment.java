package com.s21.quemepongo2front.creadoresDeFragments.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.ClimaActualRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.SugerenciaRs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    SugerenciaRs sugerencia;
    TextView txtViewsugerencia;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textViewUbicacion);
        homeViewModel.getText().observe(this, new Observer<String>() {
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ClimaActualRs> call = restClient.recibirPronostico(3860259, MainActivity.token);

        call.enqueue(new Callback<ClimaActualRs>() {
            public void onResponse(Call<ClimaActualRs> call, Response<ClimaActualRs> response) {
                if (response.isSuccessful()){
                    ClimaActualRs data = response.body();

                    TextView temp_actual = getView().findViewById(R.id.temperatura_actual);
                    int temp = (int) Math.round(data.getTemperatura());
                    temp_actual.setText( temp+"℃");
                    TextView ubicacion = getView().findViewById(R.id.textViewUbicacion);
                    ubicacion.setText(getText(R.string.ubicacion)+data.getCiudadNombre());

                    TextView viento = getView().findViewById(R.id.textViento);
                    int numeroViento = (int)Math.round(data.getViento());
                    viento.setText("Viento: "+ numeroViento +" km/h");

                    TextView textHumedad= getView().findViewById(R.id.textHumedad);
                    textHumedad.setText("Humedad: "+data.getHumedad()+"%");
                }
            }
            public void onFailure(Call<ClimaActualRs> call, Throwable t) {

            }
        });
        mostrarsugerencia();
        return root;
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
        txtViewsugerencia.setText(txtViewsugerencia.getText()+" +  "+sugerencia.getSugerencia()) ;
    }
}



