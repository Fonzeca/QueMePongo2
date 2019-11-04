package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.s21.quemepongo2front.ui.ObjetosRS.PreferenciaRs;

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
    Api retrofit;
    Button botonlogin, botonhome, botonobjetos, botonGuardar;
    String temperatura, nombre, viento, humedad;
    public static String token;
    ImageButton bufanda,protector,lentes,gorra,paraguas;
    PreferenciaRs preferencias= new PreferenciaRs();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        obtenertoken();
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
        //token= getIntent().getStringExtra("token");

    }

    private void obtenertoken() {
        if(token==null){
            Intent goLogin= new Intent(this,CreacionUsuario_Activity.class);
            startActivity(goLogin);
        }else{
            Toast.makeText(this, "ocurrio un error", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void loginusuario(View v ){
            Intent gologin = new Intent(this, CreacionUsuario_Activity.class);
                startActivity(gologin);
        }

        //TODO crear un string que contenga el token para enviarlo por este metodo
    public void guardarPref(View v){

                preferencias.setBufanda(false);
                preferencias.setGorra(false);
                preferencias.setLentes(false);
                preferencias.setParaguas(false);
                preferencias.setProtectorSolar(false);
                Toast.makeText(MainActivity.this, "Holaa", Toast.LENGTH_SHORT).show();
                //Codigo para la llamada a la api
                RestClient restClient = Api.getRetrofit().create(RestClient.class);
                //TODO Agregar token dinamico
                Call<PreferenciaRs> call = restClient.actualizarPreferencias(token,preferencias);
                call.enqueue(new Callback<PreferenciaRs>() {
                    @Override
                    public void onResponse(Call<PreferenciaRs> call, Response<PreferenciaRs> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Holi" , Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MainActivity.this, "Fallo en responder", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }

                    @Override
                    public void onFailure(Call<PreferenciaRs> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
                    }
                });
            }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        MainActivity.token = token;
    }
    }
