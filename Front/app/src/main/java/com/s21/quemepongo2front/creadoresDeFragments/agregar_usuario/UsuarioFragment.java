package com.s21.quemepongo2front.creadoresDeFragments.agregar_usuario;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.s21.quemepongo2front.CreacionUsuario_Activity;
import com.s21.quemepongo2front.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class UsuarioFragment extends Fragment {

    private UsuarioViewModel usuarioViewModel;
    Button botonnuevousuario;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        usuarioViewModel =
                ViewModelProviders.of(this).get(UsuarioViewModel.class);
        View root = inflater.inflate(R.layout.fragment_agregar_usuario, container, false);
        final TextView textView = root.findViewById(R.id.textView2);
        usuarioViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        botonnuevousuario = getActivity().findViewById(R.id.botonNuevoUsuario);
        botonnuevousuario.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent gonuevousuario = new Intent(getActivity(), CreacionUsuario_Activity.class);
                startActivity(gonuevousuario);
            }
        });
        return root;
    }


}