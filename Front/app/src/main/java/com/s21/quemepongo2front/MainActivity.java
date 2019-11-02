package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    Button botonlogin,botonhome;
    String temperatura,  nombre,  viento,humedad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "QueMePongo@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Inicializar el navigation `
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_objetos_personales,
                R.id.nav_tools, R.id.nav_share, R.id.nav_nuevo_usuario)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);















        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    String urlS = getString(R.string.ipApi)+ getString(R.string.json_pronostico);
                    JSONObject jsonObject= new JSONObject(readUrl(urlS));

                    temperatura = jsonObject.get("temperatura").toString();
                    nombre = jsonObject.get("ciudadNombre").toString();
                    viento= jsonObject.get("viento").toString();
                    humedad=jsonObject.get("humedad").toString();


                    TextView temp_actual = findViewById(R.id.temperatura_actual);
                    temp_actual.setText(temperatura+"℃");
                    TextView ubicacion = findViewById(R.id.textViewUbicacion);
                    ubicacion.setText("Ubicacion: "+nombre);
                    TextView viento =findViewById(R.id.textViento);
                    //TODO: comprobar  si realmente trae los datos en km/h
                    viento.setText("Viento: "+viento+" km/h");

                    TextView textHumedad=findViewById(R.id.textHumedad);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    int visivilidad;
    Button botonobjetos;

    public void mostrarobjetos(View v){

    }

    public void loginusuario(View v ){
            Intent gologin = new Intent(this, usuariologin.class);
                startActivity(gologin);
        }
    }

