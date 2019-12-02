package com.s21.quemepongo2front.creadoresDeFragments.Sesion;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.s21.quemepongo2front.R;

import androidx.fragment.app.Fragment;

public class CerrarSesion extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_cerrar_sesion, container, false);
        logout();
        return v;
    }

    private void logout() {
        System.exit(0);
    }
}