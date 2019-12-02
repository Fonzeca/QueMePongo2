package com.s21.quemepongo2front.creadoresDeFragments.Sesion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.snackbar.Snackbar;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.Api;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.UsuarioRq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreacionUsuario_Activity extends AppCompatActivity {
    Button botonNuevoUsuario;
    UsuarioRq user = new UsuarioRq();
    EditText txtNombre, txtPasw;
    String usuario, clave, token;
    Button botonH,botonM;
    MaterialButtonToggleGroup group;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearusuario);
        Toolbar toolbar = findViewById(R.id.toolbar);
        botonH = findViewById(R.id.boton_hombre);
        botonM = findViewById(R.id.boton_mujer);

        setSupportActionBar(toolbar);
        }

    protected void onResume() {
        super.onResume();
        botonNuevoUsuario = findViewById(R.id.botonNuevoUsuario);


        botonH.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                cambiarEstado();
            }
        });
        botonNuevoUsuario.setOnClickListener(new View.OnClickListener() {

            public void onClick(final View v) {

                //Seteamos el texto del edittext en un string
                txtNombre= findViewById(R.id.edittext_usuario);
                txtPasw = findViewById(R.id.edit_text_pasw);
                usuario = txtNombre.getText().toString();
                clave = txtPasw.getText().toString();
                // agregamos los string al objeto user
                user.setClave(clave);
                user.setUsuario(usuario);
                boolean usr= true, cla= true;
                cla= validarClave(clave);
                usr= validarUsuario(usuario);
                if(usr&&cla){

                    //instanciamos el RestClient y enviamos los parametros del usuario
                    RestClient restClient = Api.getRetrofit().create(RestClient.class);
                    Call<LoginRs> call = restClient.crearUsuario(user);
                    call.enqueue(new Callback<LoginRs>() {

                        public void onResponse(Call<LoginRs> call, Response<LoginRs> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(CreacionUsuario_Activity.this, "Usuario Creado Correctamente", Toast.LENGTH_SHORT).show();
                                LoginRs login = response.body();
                                token= login.getToken();
                                Intent gohome = new Intent(CreacionUsuario_Activity.this, MainActivity.class);
                                MainActivity.token = token;
                                finish();
                                startActivity(gohome);
                            }else {
                                Toast.makeText(CreacionUsuario_Activity.this, "Error Fatal, Intentelo de nuevo :)", Toast.LENGTH_SHORT).show();
                            }
                        }

                        public void onFailure(Call<LoginRs> call, Throwable t) {

                        }
                    });
                }else{
                    Toast.makeText(CreacionUsuario_Activity.this, "Error en la creacion de usuario intentelo de nuevo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private boolean validarUsuario(String usuario){
        Boolean resultado = true;
        if(usuario==""){
            resultado=false;
        }else if(usuario.length()<=3){
            resultado=false;
        }

        return resultado;
    }
    //TODO crear los snack bar para los errores de ingreso

    private Boolean validarClave(String clave){
        Boolean resultado = true;
        if(clave==""){
            resultado=false;
        }else if(clave.length()<=3){
            resultado=false;
        }

        return resultado;
    }

    private void cambiarEstado(){
    if(botonH.isPressed()){
        botonM.setEnabled(false);
    }else if (botonM.isPressed()){
        botonH.setEnabled(false);
        }
    }
}

