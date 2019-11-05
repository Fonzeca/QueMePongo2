package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.s21.quemepongo2front.ui.AdapterListaCiudad;
import com.s21.quemepongo2front.ui.ObjetosRS.CiudadRs;
import com.s21.quemepongo2front.ui.ObjetosRS.PreferenciaRs;
import com.s21.quemepongo2front.ui.ObjetosRq.PreferenciaRq;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private AppBarConfiguration mAppBarConfiguration;
    RestClient restClient = Api.getRetrofit().create(RestClient.class);
    String temperatura, nombre, viento, humedad,buscador,ciudadSeleccionada;
    public static String token;
    ImageButton bufanda,protector,lentes,gorra,paraguas;
    EditText editBuscador;
    TextView txtCiudadSeleccion;
    PreferenciaRq preferencias= new PreferenciaRq();
    ArrayList<CiudadRs> listaCiudadesRecibe;
    RecyclerView recycler;

    protected void onCreate(Bundle savedInstanceState) {
        if(token==null) {
            obtenertoken();
        }
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
    private void obtenertoken() {
        if(token==null){
            Intent goLogin= new Intent(this,LoginActivity.class);
            startActivity(goLogin);
        }else{
            Toast.makeText(this, "ocurrio un error", Toast.LENGTH_SHORT).show();
        }
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

    public void loginusuario(View v ){
        Intent gologin = new Intent(this, CreacionUsuario_Activity.class);
        startActivity(gologin);
    }

        //TODO Setear las preferencias dea cuerdo al check de el view de preferencias
    public void guardarPref(View v){

        preferencias.setBufanda(false);
        preferencias.setGorra(false);
        preferencias.setLentes(false);
        preferencias.setParaguas(false);
        preferencias.setProtectorSolar(false);
        Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
        //Codigo para la llamada a la api
        //TODO Agregar token dinamico
        Call<PreferenciaRs> call = restClient.actualizarPreferencias(token,preferencias);
        call.enqueue(new Callback<PreferenciaRs>() {

            public void onResponse(Call<PreferenciaRs> call, Response<PreferenciaRs> response) {
                if(response.isSuccessful()){


                    Toast.makeText(MainActivity.this, "Holi" , Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this, "Fallo en responder", Toast.LENGTH_SHORT).show();
                    finish();

                }
            }

            public void onFailure(Call<PreferenciaRs> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void llenarlistaciudades(View view){
        editBuscador = findViewById(R.id.editTextBuscador);
        editBuscador.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                buscador = editBuscador.getText().toString();

                final Call <ArrayList<CiudadRs>> listar = restClient.obtenerCiudad(buscador,token);
                listar.enqueue(new Callback<ArrayList<CiudadRs>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                        if(response.isSuccessful()){

                            listaCiudadesRecibe = response.body();
                            agregaradaptador(listaCiudadesRecibe);

                        }else{
                            Toast.makeText(MainActivity.this, "Error intentelo denuevo", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                buscador = editBuscador.getText().toString();

                final Call <ArrayList<CiudadRs>> listar = restClient.obtenerCiudad(buscador,token);
                listar.enqueue(new Callback<ArrayList<CiudadRs>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                        if(response.isSuccessful()){

                            listaCiudadesRecibe = response.body();
                            agregaradaptador(listaCiudadesRecibe);

                        }else{
                            Toast.makeText(MainActivity.this, "Error intentelo denuevo", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {
                buscador = editBuscador.getText().toString();

                final Call <ArrayList<CiudadRs>> listar = restClient.obtenerCiudad(buscador,token);
                listar.enqueue(new Callback<ArrayList<CiudadRs>>() {
                    @Override
                    public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                        if(response.isSuccessful()){

                            listaCiudadesRecibe = response.body();
                            agregaradaptador(listaCiudadesRecibe);

                        }else{
                            Toast.makeText(MainActivity.this, "Error intentelo denuevo", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {

                    }
                });
            }
        });




    }
    public void agregaradaptador(ArrayList <CiudadRs>lista){
        recycler = findViewById(R.id.recyclerViewCiudades);
        txtCiudadSeleccion = findViewById(R.id.textViewCiudadSeleccionada);
        recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
        AdapterListaCiudad adaptador = new AdapterListaCiudad(lista);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciudadSeleccionada = listaCiudadesRecibe.get(recycler.getChildAdapterPosition(v)).getNombre();
                txtCiudadSeleccion.setText(ciudadSeleccionada);
            }
        });
        recycler.setAdapter(adaptador);
    }
}
