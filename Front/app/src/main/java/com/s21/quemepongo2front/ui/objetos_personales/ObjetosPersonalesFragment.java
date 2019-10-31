package com.s21.quemepongo2front.ui.objetos_personales;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.s21.quemepongo2front.R;

public class ObjetosPersonalesFragment extends Fragment {

    private ObjetosViewModel objetosViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        objetosViewModel =
                ViewModelProviders.of(this).get(ObjetosViewModel.class);
        View root = inflater.inflate(R.layout.fragment_objetos, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        objetosViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}