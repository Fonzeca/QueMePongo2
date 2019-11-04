package com.s21.quemepongo2front;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.s21.quemepongo2front.ui.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.ui.ObjetosRq.LoginRq;

public class LoginActivity extends AppCompatActivity {
    TextView txtusuario,txtclave;
    String usuario,clave;
    LoginRq loginsend;
    LoginRs loginRecibe;
    Context context= getBaseContext();
    Button botonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        botonLogin = findViewById(R.id.botonLogin);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logear();
            }
        });
    }
    protected void logear(){

        txtusuario= findViewById(R.id.txtUsuarioLogin);
        txtclave = findViewById(R.id.txtClaveLogin);
        usuario = txtusuario.getText().toString();
        clave = txtclave.getText().toString();
        loginsend= new LoginRq();
        loginsend.setUsuario(usuario);
        loginsend.setClave(clave);
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        Call<LoginRs> logeo = restClient.loginUsuario(loginsend);
        logeo.enqueue(new Callback<LoginRs>() {
            @Override
            public void onResponse(Call<LoginRs> call, Response<LoginRs> response) {
                if(response.isSuccessful()){
                    loginRecibe= response.body();
                    Intent goHome = new Intent(context, MainActivity.class);
                    Toast.makeText(LoginActivity.this, "Se logeo correctamente", Toast.LENGTH_SHORT).show();
                    MainActivity.setToken(loginRecibe.getToken());
                    finish();
                    startActivity(goHome);

                }else{
                    Toast.makeText(LoginActivity.this, "Error no se pudo procesar la peticion", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginRs> call, Throwable t) {

            }
        });
    }


}
