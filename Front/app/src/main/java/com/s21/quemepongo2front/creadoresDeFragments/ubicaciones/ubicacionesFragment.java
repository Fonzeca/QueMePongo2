package com.s21.quemepongo2front.creadoresDeFragments.ubicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.adaptadores.AdaptadorTabView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

public class ubicacionesFragment extends Fragment {

    private UbicacionesViewModel ubicacionesViewModel;
    private ViewPager mViewPager;
    public View onCreateView(@NonNull LayoutInflater inflater,

            ViewGroup container, Bundle savedInstanceState) {
                ubicacionesViewModel =
                        ViewModelProviders.of(this).get(UbicacionesViewModel.class);
                View root = inflater.inflate(R.layout.fragment_ubicaciones, container, false);

            mViewPager = root.findViewById(R.id.viewPagerUbicaciones);
            setViewPager(mViewPager);
            TabLayout tabLayout = root.findViewById(R.id.tabLayout);
            tabLayout.setupWithViewPager(mViewPager);

        return root;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        }

    private void setViewPager(ViewPager viewPage ) {
        AdaptadorTabView adaptador = new AdaptadorTabView(getFragmentManager());
        adaptador.addFragment(new fragment_ubicaciones_lista(),"Ubicaciones");
        adaptador.addFragment(new fragment_mas_ubicaciones(),"Nueva ubicacion");
        viewPage.setAdapter(adaptador);

    }

    /*
    //busca la ciudad cuando se escribe en el editText del buscador
    private void buscarCiudadOnTextChange(){
        //le seteamos a la variable buscador el texto que se escribe, para enviarlo a la api
        buscador = editBuscador.getText().toString();

        RestClient restClient = Api.getRetrofit().create(RestClient.class);

        Call<ArrayList<CiudadRs>> listar = restClient.obtenerCiudad(buscador,token);

        listar.enqueue(new Callback<ArrayList<CiudadRs>>() {

            public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {

                if(response.isSuccessful()){
                    listaCiudadesRecibe = response.body();
                    agregaradaptador(listaCiudadesRecibe);
                }else{
                    Toast.makeText(getContext(), "Error intentelo denuevo", Toast.LENGTH_SHORT).show();
                }
            }
            public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {

            }
        });
    }
    public void agregaradaptador(ArrayList <CiudadRs>lista){
        recycler = getActivity().findViewById(R.id.recyclerViewCiudades);
        txtCiudadSeleccion = getActivity().findViewById(R.id.textViewCiudadSeleccionada);
        recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false));
         adapterListaCiudad = new AdapterListaCiudad(lista);
        //on click de cuando se toca una ciudad en el adapter
        adapterListaCiudad.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ciudadSeleccionada = listaCiudadesRecibe.get(recycler.getChildAdapterPosition(v)).getNombre()+", "+listaCiudadesRecibe.get(recycler.getChildAdapterPosition(v)).getPais();
                Toast.makeText(getContext(), "Selecciono la ciudad:  "+ ciudadSeleccionada, Toast.LENGTH_SHORT).show();
                txtCiudadSeleccion.setText(ciudadSeleccionada);
                ubicacionSeleccionada = listaCiudadesRecibe.get(recycler.getChildAdapterPosition(v));
                agregarubicacion(ubicacionSeleccionada,v);

            }
        });
        recycler.setAdapter(adapterListaCiudad);
    }

    public void agregarubicacion(final CiudadRs ubicacion, View v){
        agregarUbicacion = getActivity().findViewById(R.id.botonAgregarCiudadSeleccionada);
        ubicacion1 = listaCiudadesRecibe.get(recycler.getChildAdapterPosition(v));
        agregarUbicacion.setOnClickListener(new View.OnClickListener() {
        //crear metodo para la seleccion de ciudades revisar que se esten mostrando antes las ciudades del usuario
            public void onClick(View v) {
                mostrarCiudadesDeUsuario();
                agregarCiudadaMisCiudades(ubicacion);
            }
        });
    }




    */
}