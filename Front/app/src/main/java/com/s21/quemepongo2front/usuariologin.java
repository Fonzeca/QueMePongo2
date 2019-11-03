package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class usuariologin extends AppCompatActivity {
    Button nuevoUsuario;
    UsuarioRq User;
    //TextView txtNombre, txtPasw;
    String nombre, pasword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuariologin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

        public void agregarusuario(View v){


        Intent gohome = new Intent(this, MainActivity.class);
        Toast.makeText(this, "Se agregó el usuario a la base de datos", Toast.LENGTH_SHORT).show();

            startActivity(gohome);

    }
}
