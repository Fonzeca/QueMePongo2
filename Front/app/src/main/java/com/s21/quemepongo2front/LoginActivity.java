package com.s21.quemepongo2front;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.LoginRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.LoginRq;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    TextView txtusuario,txtclave;
    String usuario,clave;
    LoginRq loginsend;
    LoginRs loginRecibe;
    Button botonLogin,botonRegistrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtusuario= findViewById(R.id.txtUsuarioLogin);
        txtclave = findViewById(R.id.txtClaveLogin);
        botonLogin = findViewById(R.id.botonLogin);
        botonRegistrase=findViewById(R.id.botonSignUp);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logear();
            }
        });

//TODO Borrar el hardcodeo de fonzin
        txtusuario.setText("Alexis");
        txtclave.setText("123456");
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

                }
            });
        }


    }
    private void registrarse(){
        Intent registro=new Intent(this,CreacionUsuario_Activity.class);
        finish();
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
        TextView textViewUsuario = findViewById(R.id.textViewAlertadeusuarioincorrecto);
        textViewUsuario.setText("");
        if(usuario==""){
            textViewUsuario.setText("El usuario no puede estar vacio, porfavor ingrese uno");
            resultado=false;
        }else if(usuario.length()<=3){
            textViewUsuario.setText("Por favor ingrese una clave superior a 3 letras");
            resultado=false;
        }

        return resultado;
    }
    private Boolean validarClave(String clave){
        TextView textViewclave = findViewById(R.id.textViewClaveIncorrecta);
        textViewclave.setText("");
        Boolean resultado = true;
        if(clave==""){
            textViewclave.setText("La contraseña no puede estar vacia, profavor ingrese una");
            resultado=false;
        }else if(clave.length()<=3){
            textViewclave.setText("Por favor ingrese una clave superior a 3 letras");
            resultado=false;
        }

        return resultado;
    }
}
