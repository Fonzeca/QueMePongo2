package com.s21.quemepongo2front.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.ui.ObjetosRS.ClimaActualRs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

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

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.textViewUbicacion);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ClimaActualRs> call = restClient.recibirPronostico(3860259, MainActivity.token);

        call.enqueue(new Callback<ClimaActualRs>() {
            @Override
            public void onResponse(Call<ClimaActualRs> call, Response<ClimaActualRs> response) {
                if (response.isSuccessful()){
                    ClimaActualRs data = response.body();

                    TextView temp_actual = getView().findViewById(R.id.temperatura_actual);
                    temp_actual.setText(data.getTemperatura()+"℃");

                    TextView ubicacion = getView().findViewById(R.id.textViewUbicacion);
                    ubicacion.setText(getText(R.string.ubicacion)+data.getCiudadNombre());

                    TextView viento = getView().findViewById(R.id.textViento);
                    viento.setText("Viento: "+data.getViento()+" km/h");

                    TextView textHumedad= getView().findViewById(R.id.textHumedad);
                    textHumedad.setText("Humedad: "+data.getHumedad()+"%");
                }
            }
            @Override
            public void onFailure(Call<ClimaActualRs> call, Throwable t) {

            }
        });


        return root;
    }
}



