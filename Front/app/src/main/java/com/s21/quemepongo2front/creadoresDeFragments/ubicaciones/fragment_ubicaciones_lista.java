package com.s21.quemepongo2front.creadoresDeFragments.ubicaciones;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class fragment_ubicaciones_lista extends Fragment {
    private ArrayList<CiudadRs> ciudadesUsuario;
    private ListView listViewMisCiudades;
    private ArrayAdapter<CiudadRs> adapter;

    public fragment_ubicaciones_lista() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubicaciones_lista, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mostrarCiudadesDeUsuario();
        listViewMisCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CiudadRs ciudad = (CiudadRs)adapterView.getItemAtPosition(i);
                quitarCiudad(ciudad.getId(), i);

            }
        });
    }

    private void mostrarCiudadesDeUsuario(){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        listViewMisCiudades= getActivity().findViewById(R.id.listViewCiudadesUsuario);

        Call<ArrayList<CiudadRs>> ubicaciones = restClient.misCiudades(MainActivity.token);

        ubicaciones.enqueue(new Callback<ArrayList<CiudadRs>>() {

            public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        ciudadesUsuario = response.body();

                        cargarListaCiudadesUsuario();

                    } else {
                        Toast.makeText(getContext(), "Ocurrio un error: No hay ciudades cargadas", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), "Ocurrio un error " + response.errorBody(), Toast.LENGTH_SHORT).show();
                }
            }
            public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {
            }
        });
    }
     //TODO; Refactotr
    private void cargarListaCiudadesUsuario(){
        adapter = new ArrayAdapter(getActivity(),R.layout.item_list_ciudades,R.id.itemListaCiudades,ciudadesUsuario);
        listViewMisCiudades.setAdapter(adapter);
    }

    private void quitarCiudad(int idCiudad, final int indexInAdapter){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        restClient.borrarciudad(idCiudad, MainActivity.token).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    ciudadesUsuario.remove(indexInAdapter);
                    adapter.notifyDataSetChanged();
                }
            }

            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

}

