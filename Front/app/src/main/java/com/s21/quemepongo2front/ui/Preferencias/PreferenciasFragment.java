package com.s21.quemepongo2front.ui.Preferencias;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.ui.ObjetosRS.ClimaActualRs;

public class PreferenciasFragment extends Fragment {

    private PreferenciasViewModel preferenciasViewModel;
    Api retrofit;
    Context context;
    ImageButton bufanda,protector,lentes,gorra,paraguas;
    Button botonGuardar;
    ClimaActualRs preferencias= new ClimaActualRs();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            preferenciasViewModel =ViewModelProviders.of(this).get(PreferenciasViewModel.class);
            View root = inflater.inflate(R.layout.fragment_objetos, container, false);
            final TextView textView = root.findViewById(R.id.text_preferencias);
            preferenciasViewModel.getText().observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
    }

}