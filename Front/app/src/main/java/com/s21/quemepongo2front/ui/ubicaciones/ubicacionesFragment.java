package com.s21.quemepongo2front.ui.ubicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class ubicacionesFragment extends Fragment {

    private UbicacionesViewModel ubicacionesViewModel;
    Api response;
    ArrayList ciudades;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ubicacionesViewModel =
                ViewModelProviders.of(this).get(UbicacionesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_ubicaciones, container, false);
        final TextView textView = root.findViewById(R.id.editTextBuscador);
        ubicacionesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        return root;
    }
}