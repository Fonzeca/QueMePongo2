package com.s21.quemepongo2front.ui.objetos_personales;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ObjetosViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ObjetosViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Agrega tus objetos personales :D");
    }

    public LiveData<String> getText() {
        return mText;
    }
}