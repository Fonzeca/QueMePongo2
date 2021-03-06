package com.s21.quemepongo2front.creadoresDeFragments.Preferencias;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.s21.quemepongo2front.objetosDeLaApi.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.PreferenciaRs;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRq.PreferenciaRq;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PreferenciasFragment extends Fragment {

    private Button botonGuardar;
    private CheckBox bufandaCheck, gorraCheck, bloqueadorCheck, paraguasCheck, lentesCheck;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_preferencias, container, false);

        return v;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        bufandaCheck = getActivity().findViewById(R.id.checkBoxBufanda);
        gorraCheck = getActivity().findViewById(R.id.checkBoxGorra);
        bloqueadorCheck = getActivity().findViewById(R.id.checkBoxBloqueador);
        paraguasCheck = getActivity().findViewById(R.id.checkBoxParaguas);
        lentesCheck = getActivity().findViewById(R.id.checkBoxLentes);

        botonGuardar = getActivity().findViewById(R.id.boton_guardarPreferencias);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                guardarPreferencias();
            }
        });

        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        Call<PreferenciaRs> obtenerPreferncia = restClient.obtenerPreferencias(MainActivity.token);
        obtenerPreferncia.enqueue(new Callback<PreferenciaRs>() {
            public void onResponse(Call<PreferenciaRs> call, Response<PreferenciaRs> response) {
                if(response.isSuccessful()){
                    PreferenciaRs rs = response.body();
                    bufandaCheck.setChecked(rs.isBufanda());
                    lentesCheck.setChecked(rs.isLentes());
                    paraguasCheck.setChecked(rs.isParaguas());
                    bloqueadorCheck.setChecked(rs.isProtectorSolar());
                    gorraCheck.setChecked(rs.isGorra());
                }
            }
            public void onFailure(Call<PreferenciaRs> call, Throwable t) {
            }
        });
    }

    private void guardarPreferencias(){

        PreferenciaRq rq = new PreferenciaRq(
            bufandaCheck.isChecked(),
            lentesCheck.isChecked(),
            paraguasCheck.isChecked(),
            bloqueadorCheck.isChecked(),
            gorraCheck.isChecked()
        );


        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<PreferenciaRs> call = restClient.actualizarPreferencias(MainActivity.token,rq);

        call.enqueue(new Callback<PreferenciaRs>() {
            public void onResponse(Call<PreferenciaRs> call, Response<PreferenciaRs> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getActivity(),"Se guardaron las preferencias",Toast.LENGTH_SHORT).show();

                }
            }

            public void onFailure(Call<PreferenciaRs> call, Throwable t) {

            }
        });
    }
}