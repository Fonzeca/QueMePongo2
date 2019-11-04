package com.s21.quemepongo2front;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.R;

public class LoginActivity extends AppCompatActivity {
    Api response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        RestClient restClient = Api.getRetrofit().create(RestClient.class);

    }

}
