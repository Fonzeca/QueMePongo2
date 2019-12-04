package com.s21.quemepongo2front.creadoresDeFragments.ubicaciones;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.s21.quemepongo2front.adaptadores.AdapterListaCiudad;
import com.s21.quemepongo2front.adaptadores.RecyclerViewClickListener;
import com.s21.quemepongo2front.objetosDeLaApi.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;

import java.math.BigDecimal;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_ubicaciones_lista extends Fragment implements RecyclerViewClickListener {
	private ArrayList<CiudadRs> ciudadesUsuario;
	private RecyclerView recyclerViewMisCiudades;
	private AdapterListaCiudad adapter;
	private Button botonEliminar, botonPredeterminar, botonSatelite;
	private TextView textViewSeleccionCiudad;



	public Fragment_ubicaciones_lista() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_ubicaciones_lista, container, false);
	}

	@Override
	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		botonPredeterminar = getActivity().findViewById(R.id.buttonPredeterminar_miCiudad2);
		textViewSeleccionCiudad = getActivity().findViewById(R.id.textView_seleccionCiudad);

		recyclerViewMisCiudades = getActivity().findViewById(R.id.recyclerViewCiudadesUsuario);
		recyclerViewMisCiudades.setHasFixedSize(true);
		recyclerViewMisCiudades.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

		botonEliminar = getActivity().findViewById(R.id.buttonEliminar_miCiudad);
		botonEliminar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				quitarCiudad(adapter.getItemSelected().getId());
			}
		});

		botonSatelite = getActivity().findViewById(R.id.imageViewSatelite);
		botonSatelite.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CiudadRs ciudad = adapter.getItemSelected();
				BigDecimal lat = ciudad.getLatitud();
				BigDecimal lon = ciudad.getLongitud();

				Uri webpage = Uri.parse("https://openweathermap.org/weathermap?basemap=map&cities=true&layer=precipitation&lat=" +
						lat + "&lon=" + lon + "zoom=10");
				Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
				startActivity(webIntent);
			}
		});

		mostrarCiudadesDeUsuario();
	}

	private void mostrarCiudadesDeUsuario() {
		RestClient restClient = Api.getRetrofit().create(RestClient.class);

		Call<ArrayList<CiudadRs>> ubicaciones = restClient.misCiudades(MainActivity.token);

		ubicaciones.enqueue(new Callback<ArrayList<CiudadRs>>() {
			public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {
				if (response.isSuccessful()) {
					if (response.body() != null) {
						ciudadesUsuario = response.body();
						adapter = new AdapterListaCiudad(ciudadesUsuario, Fragment_ubicaciones_lista.this);
						recyclerViewMisCiudades.setAdapter(adapter);
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

	private void quitarCiudad(int idCiudad) {
		RestClient restClient = Api.getRetrofit().create(RestClient.class);
		botonEliminar.setEnabled(false);

		restClient.borrarciudad(idCiudad, MainActivity.token).enqueue(new Callback<Void>() {
			public void onResponse(Call<Void> call, Response<Void> response) {
				if (response.isSuccessful()) {
					adapter.removeSelectedItem();
					textViewSeleccionCiudad.setText("Seleccionaste: ");
				}
				botonEliminar.setEnabled(true);
			}

			public void onFailure(Call<Void> call, Throwable t) {
				botonEliminar.setEnabled(true);
			}
		});
	}

	public void recyclerViewListClicked(View v, int position) {
		CiudadRs ciudad = adapter.getItemSelected();
		String textoMostrar =  ciudad.getNombre() + ", " + ciudad.getPais();
		textViewSeleccionCiudad.setText("Seleccionaste: " + textoMostrar);
	}

	private void predetermiarCiudad(int idCiudad, final int indexInAdapter) {
		RestClient restClient = Api.getRetrofit().create(RestClient.class);
		botonPredeterminar.setEnabled(false);
	}
}

