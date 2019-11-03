package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class usuariologin extends AppCompatActivity {
    Api retrofit ;
    Button nuevoUsuario;
    UsuarioRq user = new UsuarioRq();
    EditText txtNombre, txtPasw;
    String nombre, clave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuariologin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nuevoUsuario = findViewById(R.id.botonNuevoUsuario);
        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Comentar esta parte
                txtNombre= (EditText)findViewById(R.id.edittext_usuario);
                txtPasw = (EditText)findViewById(R.id.edit_text_pasw);
                nombre = txtNombre.getText().toString();
                clave = txtPasw.getText().toString();

                user.setClave(clave);
                user.setUsuario(nombre);

                RestClient restClient = Api.getRetrofit().create(RestClient.class);
                Call<Void> call = restClient.crearUsuario(user);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(usuariologin.this, "Usuario Creado Correctamente", Toast.LENGTH_SHORT).show();
                            Intent gohome = new Intent(usuariologin.this, MainActivity.class);
                            startActivity(gohome);
                        }else {
                            Toast.makeText(usuariologin.this, "Error Fatal", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {

                    }
                });
            }
        });
        }
}
