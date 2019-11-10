package com.s21.quemepongo2front.creadoresDeFragments.ubicaciones;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.s21.quemepongo2front.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class fragment_ubicaciones_lista extends Fragment {
    private Context context = getActivity();
    private ArrayList<CiudadRs>ciudadesUsuario;
    private  ArrayList <String> nombreDeCiudad = new ArrayList<String>();
    private ListView listViewMisCiudades;
    public fragment_ubicaciones_lista() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                mostrarCiudadesDeUsuario();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ubicaciones_lista, container, false);

    }

    public void mostrarCiudadesDeUsuario(){
        RestClient restClient = Api.getRetrofit().create(RestClient.class);
        listViewMisCiudades= getActivity().findViewById(R.id.listViewCiudadesUsuario);

        Call<ArrayList<CiudadRs>> ubicaciones = restClient.misCiudades(MainActivity.token);

        ubicaciones.enqueue(new Callback<ArrayList<CiudadRs>>() {

            public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        ciudadesUsuario = response.body();

                        cargarListaCiudadesUsuario(ciudadesUsuario);

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

    public void cargarListaCiudadesUsuario(ArrayList<CiudadRs> lista){

        for (int i = 0; i <lista.size() ; i++) {
            nombreDeCiudad.add((lista.get(i)).getNombre());
        }
        listViewMisCiudades=getActivity().findViewById(R.id.listViewCiudadesUsuario);
        ArrayAdapter<String> adaptadorCiudadUsuario = new ArrayAdapter(getActivity(),R.layout.item_list_ciudades,R.id.itemListaCiudades,nombreDeCiudad);
        listViewMisCiudades.setAdapter(adaptadorCiudadUsuario);
    }
}

