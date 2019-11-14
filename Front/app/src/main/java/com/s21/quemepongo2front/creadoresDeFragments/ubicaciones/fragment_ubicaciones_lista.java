package com.s21.quemepongo2front.creadoresDeFragments.ubicaciones;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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


public class fragment_ubicaciones_lista extends Fragment {
    private ArrayList<CiudadRs> ciudadesUsuario;
    private ListView listViewMisCiudades;
    private ArrayAdapter<CiudadRs> adapter;
    private Button botonEliminar;
    private TextView textViewSeleccionCiudad;
    private Selected itemSelected;

    //TODO: hacer forma mas elegante de guardar el item que se eligio
    private class Selected{
        public int idCIudad;
        public int indexOfArray;
        public Selected(int idCIudad, int indexOfArray){
            this.idCIudad = idCIudad;
            this.indexOfArray = indexOfArray;
        }
    }


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
        configView();

        mostrarCiudadesDeUsuario();
    }

    private void configView() {
        listViewMisCiudades= getActivity().findViewById(R.id.listViewCiudadesUsuario);
        botonEliminar = getActivity().findViewById(R.id.buttonEliminar_miCiudad);
        textViewSeleccionCiudad = getActivity().findViewById(R.id.textView_seleccionCiudad);

        listViewMisCiudades.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CiudadRs ciudad = (CiudadRs)adapterView.getItemAtPosition(i);
                itemSelected = new Selected(ciudad.getId(), i);
                textViewSeleccionCiudad.setText("Seleccionaste: " + ciudad.toString());
            }
        });
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //TODO: hacer el else
                if(itemSelected != null){
                    quitarCiudad(itemSelected.idCIudad, itemSelected.indexOfArray);

                }
                mostrarDialogoEliminar(adapterView,view,i);
            }
        });
    }

    private void mostrarCiudadesDeUsuario(){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ArrayList<CiudadRs>> ubicaciones = restClient.misCiudades(MainActivity.token);

        ubicaciones.enqueue(new Callback<ArrayList<CiudadRs>>() {
            public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ciudadesUsuario = response.body();

                        adapter = new ArrayAdapter<CiudadRs>(getActivity(),R.layout.item_list_ciudades,R.id.itemListaCiudades,ciudadesUsuario);
                        listViewMisCiudades.setAdapter(adapter);
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

    private void quitarCiudad(int idCiudad, final int indexInAdapter){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        botonEliminar.setEnabled(false);
        restClient.borrarciudad(idCiudad, MainActivity.token).enqueue(new Callback<Void>() {
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(response.isSuccessful()){
                    ciudadesUsuario.remove(indexInAdapter);
                    adapter.notifyDataSetChanged();
                    textViewSeleccionCiudad.setText("Seleccionaste: ");
                }
                botonEliminar.setEnabled(true);
            }

            public void onFailure(Call<Void> call, Throwable t) {
                botonEliminar.setEnabled(true);
            }
        });
    }

    public  void mostrarDialogoEliminar(final AdapterView<?> adapterView, View view, final int i){
        final CiudadRs ciudad = (CiudadRs)adapterView.getItemAtPosition(i);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);

        builder.setTitle("Eliminar Ciudad");
        builder.setMessage("Esta seguro de eliminar la ciudad : \n"+ ciudad.toString());
        builder.setPositiveButton("Confirm",

                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        quitarCiudad(ciudad.getId(), i);
                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

}

