package com.s21.quemepongo2front.ui.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    String temperatura, nombre, viento, humedad;

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


        AsyncTask.execute(new Runnable() {
            @Override

            public void run() {
                try {
                    String urlS = getString(R.string.ipApi)+ getString(R.string.json_pronostico);
                    JSONObject jsonObject= new JSONObject(readUrl(urlS));

                    temperatura = jsonObject.get("temperatura").toString();
                    nombre = jsonObject.get("ciudadNombre").toString();

                    // viento= jsonObject.get("viento").toString();
                    //humedad=jsonObject.get("humedad").toString();
                    TextView temp_actual = getView().findViewById(R.id.temperatura_actual);
                    temp_actual.setText(temperatura+"℃");
                    TextView ubicacion = getView().findViewById(R.id.textViewUbicacion);
                    ubicacion.setText("Ubicacion: "+nombre);
                    // TextView viento =findViewById(R.id.textViento);
                    // viento.setText("Viento: "+viento+" km/h");

                    TextView textHumedad=getView().findViewById(R.id.textHumedad);
                    textHumedad.setText("Humedad: "+humedad+"%");
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return root;
    }
}



