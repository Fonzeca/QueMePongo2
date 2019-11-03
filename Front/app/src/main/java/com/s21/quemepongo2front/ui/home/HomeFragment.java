package com.s21.quemepongo2front.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.ui.ObjetosRS.PronosticoRs;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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
//TODO
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        //Aca creamos el objeto "llamada" el cual va a ser el endpoint a cual vamos a llamar
        Call<PronosticoRs> call = restClient.recibirPronostico(3860259,"Gcj6Clo8JHvFfcjVIywn7w==");

        //Ejecutamos la llamada en  un thread a parte, el cual si te deja hacer modificaciones en la view
        call.enqueue(new Callback<PronosticoRs>() {
            @Override
            //Este es el metodo en caso que la llamada a la API devuelva algo
            public void onResponse(Call<PronosticoRs> call, Response<PronosticoRs> response) {
                //Obtenemos el body de la llamada, ya parseado a una clase java
                PronosticoRs data = response.body();

                TextView temp_actual = getView().findViewById(R.id.temperatura_actual);
                temp_actual.setText(data.getTemperatura()+"℃");

                TextView ubicacion = getView().findViewById(R.id.textViewUbicacion);
                ubicacion.setText(getText(R.string.ubicacion)+data.getCiudadNombre());

                TextView viento = getView().findViewById(R.id.textViento);
                viento.setText("Viento: "+data.getViento()+" m/s");

                TextView textHumedad= getView().findViewById(R.id.textHumedad);
                textHumedad.setText("Humedad: "+data.getHumedad()+"%");
            }
            @Override
            public void onFailure(Call<PronosticoRs> call, Throwable t) {
            }
        });

        return root;
    }
}



