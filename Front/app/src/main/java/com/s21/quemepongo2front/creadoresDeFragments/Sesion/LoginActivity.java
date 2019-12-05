package com.s21.quemepongo2front.creadoresDeFragments.Sesion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.Api;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.LoginRq;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText txtusuario,txtclave;
    TextView botonRegistrase;
    String usuario,clave;
    LoginRq loginsend;
    LoginRs loginRecibe;
    Button botonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtusuario= findViewById(R.id.edittext_usuario);
        txtclave = findViewById(R.id.edit_text_pasw);

        botonLogin = findViewById(R.id.botonLogin);
        botonRegistrase=findViewById(R.id.botonSignUp);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logear();
            }
        });

        //TODO borrar hardocedo
        botonRegistrase.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                registrarse();
            }
        });
    }


    protected void logear(){
        usuario = txtusuario.getText().toString();
        clave = txtclave.getText().toString();

        Boolean usr=true, cla=true;
        usr= validarUsuario(usuario);
        cla = validarClave(clave);
        if(usr&&cla){

            loginsend= new LoginRq();
            loginsend.setUsuario(usuario);
            loginsend.setClave(clave);

            RestClient restClient = Api.getRetrofit().create(RestClient.class);
            Call<LoginRs> logeo = restClient.loginUsuario(loginsend);

            logeo.enqueue(new Callback<LoginRs>() {
                public void onResponse(Call<LoginRs> call, Response<LoginRs> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(LoginActivity.this, "Se logeo correctamente", Toast.LENGTH_SHORT).show();
                        loginRecibe= response.body();
                        pasarAlHome();
                    }else{
                        Toast.makeText(LoginActivity.this, "¡Error no se pudo Logear, usuario o contraseña incorrectos!", Toast.LENGTH_SHORT).show();
                    }
                }
                public void onFailure(Call<LoginRs> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "Error al conectar", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }


    }
    private void registrarse(){
        Intent registro=new Intent(this, CreacionUsuario_Activity.class);
        startActivity(registro);
    }
    private void pasarAlHome(){
        Intent goHome = new Intent(this, MainActivity.class);
        MainActivity.token = loginRecibe.getToken();
        finish();
        startActivity(goHome);
    }

    private boolean validarUsuario(String usuario){
        Boolean resultado = true;
        //TODO hacer los snack bars
        if(usuario==""){
            resultado=false;
        }else if(usuario.length()<=3){
            resultado=false;
        }

        return resultado;
    }
    private Boolean validarClave(String clave){
        //TODO hacer los snack bars
        Boolean resultado = true;
        if(clave==""){
            resultado=false;
        }else if(clave.length()<=3){
            resultado=false;
        }

        return resultado;
    }
}
