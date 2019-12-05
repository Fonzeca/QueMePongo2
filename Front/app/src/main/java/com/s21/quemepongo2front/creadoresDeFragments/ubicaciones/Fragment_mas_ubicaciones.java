package com.s21.quemepongo2front.creadoresDeFragments.ubicaciones;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.s21.quemepongo2front.HostNavigation;
import com.s21.quemepongo2front.objetosDeLaApi.Api;
import com.s21.quemepongo2front.MainActivity;
import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.RestClient;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Fragment_mas_ubicaciones extends Fragment {

	private ArrayList<CiudadRs> listaCiudadesRecibe;
	private ArrayList<String> nombreDeCiudad;
	private String buscador;
	EditText editBuscador;
	private Spinner spinner;
	private Button botonGuardar;

	public Fragment_mas_ubicaciones() {
		// Required empty public constructor
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_ubicaciones_agregar, container, false);
	}

	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		spinner = getActivity().findViewById(R.id.spinner);
		editBuscador = getActivity().findViewById(R.id.ediTextBuscador);
		botonGuardar = getActivity().findViewById(R.id.botonGuardarCiudad);
		botonGuardar.setEnabled(false);
		editBuscador.addTextChangedListener(new TextWatcher() {
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}

			public void onTextChanged(CharSequence s, int start, int before, int count) {
				buscarCiudadOnTextChange();
				botonGuardar.setEnabled(true);
			}

			public void afterTextChanged(Editable s) {
			}
		});

		botonGuardar.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				int posicion = spinner.getSelectedItemPosition();
				agregarCiudadaMisCiudades(listaCiudadesRecibe.get(posicion));
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(editBuscador.getWindowToken(), 0);

					}
		});

	}
	//busca la ciudad cuando se escribe en el editText del buscador

	private void buscarCiudadOnTextChange() {
		//le seteamos a la variable buscador el texto que se escribe, para enviarlo a la api
		buscador = editBuscador.getText().toString();

		RestClient restClient = Api.getRetrofit().create(RestClient.class);

		Call<ArrayList<CiudadRs>> listar = restClient.obtenerCiudad(buscador, MainActivity.token);

		listar.enqueue(new Callback<ArrayList<CiudadRs>>() {

			public void onResponse(Call<ArrayList<CiudadRs>> call, Response<ArrayList<CiudadRs>> response) {

				if (response.isSuccessful()) {
					listaCiudadesRecibe = response.body();
					agregaradaptador(listaCiudadesRecibe);
				} else {
					Toast.makeText(getContext(), "Error intentelo denuevo", Toast.LENGTH_SHORT).show();
				}
			}

			public void onFailure(Call<ArrayList<CiudadRs>> call, Throwable t) {

			}
		});
	}

	private void agregaradaptador(ArrayList<CiudadRs> listaCiudadesRecibe) {
		nombreDeCiudad = new ArrayList<String>();
		for (int i = 0; i < listaCiudadesRecibe.size(); i++) {
			nombreDeCiudad.add((listaCiudadesRecibe.get(i)).getNombre() + ", " + listaCiudadesRecibe.get(i).getPais());
		}
		ArrayAdapter<String> adaptadorCiudadBuscada = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_dropdown_item, nombreDeCiudad);
		spinner.setAdapter(adaptadorCiudadBuscada);
	}

	public void agregarCiudadaMisCiudades(final CiudadRs ciudad) {
		RestClient restClient = Api.getRetrofit().create(RestClient.class);
		int idciudad = ciudad.getId();
		Call<Void> agregarciudad = restClient.agregarCiudad(idciudad, MainActivity.token);
		agregarciudad.enqueue(new Callback<Void>() {
			public void onResponse(Call<Void> call, Response<Void> response) {
				if (response.isSuccessful()) {
					//Recarga el fragment  de ubicaciones  llamando al metodo de change fragmentr del main activity;
					((HostNavigation) getActivity()).changeFragment(new UbicacionesFragment(), false);
					Toast.makeText(getContext(), "Se agrego la ciudad: " + ciudad.getNombre(), Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getContext(), "Se produjo un error en agregar la ciudad", Toast.LENGTH_SHORT).show();
				}
			}

			public void onFailure(Call<Void> call, Throwable t) {

			}
		});
	}
}