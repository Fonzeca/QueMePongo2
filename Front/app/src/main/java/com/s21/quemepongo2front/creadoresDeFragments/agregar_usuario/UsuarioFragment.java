package com.s21.quemepongo2front.creadoresDeFragments.agregar_usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.CreacionUsuario_Activity;
import com.s21.quemepongo2front.R;

import androidx.fragment.app.Fragment;

public class UsuarioFragment extends Fragment {

    TextView botonnuevousuario;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_agregar_usuario, container, false);

        botonnuevousuario = getActivity().findViewById(R.id.botonNuevoUsuario);
        botonnuevousuario.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent gonuevousuario = new Intent(getActivity(), CreacionUsuario_Activity.class);
                startActivity(gonuevousuario);
            }
        });
        return v;
    }


}