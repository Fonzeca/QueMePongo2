package com.s21.quemepongo2front.ui.ubicaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UbicacionesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UbicacionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento de ubicaciones");
    }

    public LiveData<String> getText() {
        return mText;
    }
}