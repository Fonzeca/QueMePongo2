package com.s21.quemepongo2front.ui.agregar_usuario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UsuarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UsuarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("este es el Menu para agregar usuario");
    }

    public LiveData<String> getText() {
        return mText;
    }
}