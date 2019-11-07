package com.s21.quemepongo2front.activitys;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.PreferenciaRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.PreferenciaRq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    RestClient restClient = Api.getRetrofit().create(RestClient.class);
    public static String token;
    PreferenciaRq preferencias= new PreferenciaRq();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "QueMePongo@gmail.com", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        //Inicializar el navigation_bar
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_objetos_personales,
                R.id.nav_ubicaciones, R.id.nav_share, R.id.nav_nuevo_usuario)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void guardarPref(View v){

        preferencias.setBufanda(false);
        preferencias.setGorra(false);
        preferencias.setLentes(false);
        preferencias.setParaguas(false);
        preferencias.setProtectorSolar(false);
        //Codigo para la llamada a la api
        Call<PreferenciaRs> call = restClient.actualizarPreferencias(token,preferencias);
        call.enqueue(new Callback<PreferenciaRs>() {

            public void onResponse(Call<PreferenciaRs> call, Response<PreferenciaRs> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Se cargaron correctamente :D" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Error intentelo otra vez", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            public void onFailure(Call<PreferenciaRs> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //TODO Comentar este metodo

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainActivity.token = token;
    }
}

