package com.s21.quemepongo2front.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import retrofit2.http.HTTP;

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
        //TODO crear un string que contenga el token para enviarlo por este metodo
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        //Aca creamos el objeto "llamada" el cual va a ser el endpoint a cual vamos a llamar
        //TODO Agregar token dinamico
        Call<ClimaActualRs> call = restClient.recibirPronostico(3860259, MainActivity.getToken());

        //Ejecutamos la llamada en  un thread a parte, el cual si te deja hacer modificaciones en la view
        call.enqueue(new Callback<ClimaActualRs>() {
            @Override
            //Este es el metodo en caso que la llamada a la API devuelva algo
            public void onResponse(Call<ClimaActualRs> call, Response<ClimaActualRs> response) {
                if (response.isSuccessful()){
                    //Obtenemos el body de la llamada, ya parseado a una clase java
                    ClimaActualRs data = response.body();

                    TextView temp_actual = getView().findViewById(R.id.temperatura_actual);
                    temp_actual.setText(data.getTemperatura()+"℃");

                    TextView ubicacion = getView().findViewById(R.id.textViewUbicacion);
                    ubicacion.setText(getText(R.string.ubicacion)+data.getCiudadNombre());

                    TextView viento = getView().findViewById(R.id.textViento);
                    viento.setText("Viento: "+data.getViento()+" km/h");

                    TextView textHumedad= getView().findViewById(R.id.textHumedad);
                    textHumedad.setText("Humedad: "+data.getHumedad()+"%");
                    //temporar es para ver el nombre del clima
                    TextView txtNombreClima= getView().findViewById(R.id.textView_nombreClima);
                    txtNombreClima.setText("nombreclima:  "+data.getNombreclima());
                }
            }
            @Override
            public void onFailure(Call<ClimaActualRs> call, Throwable t) {

            }
        });


        return root;
    }

    private static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }

    }
}



