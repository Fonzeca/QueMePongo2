package com.s21.quemepongo2front.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.objetosDeLaApi.ObjetosRS.CiudadRs;


import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListaCiudad extends RecyclerView.Adapter<AdapterListaCiudad.ViewHolderCiudades> implements View.OnClickListener {

	private ArrayList<CiudadRs> listaCiudades;
	private TextView textViewSeleccionCiudad;
	private RecyclerView recyclerView;
	private int selectedPosition;

	public AdapterListaCiudad(ArrayList<CiudadRs> listaCiudades, View view) {
		this.listaCiudades = listaCiudades;
		textViewSeleccionCiudad = view.findViewById(R.id.textView_seleccionCiudad);
	}

	@Override
	public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
		super.onAttachedToRecyclerView(recyclerView);
		this.recyclerView = recyclerView;
	}

	public ViewHolderCiudades onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view, parent, false);
		view.setOnClickListener(this);
		return new ViewHolderCiudades(view);
	}

	public void onBindViewHolder(@NonNull ViewHolderCiudades holder, int position) {
		if (listaCiudades != null && position < listaCiudades.size()) {
			holder.asignarDatos(listaCiudades.get(position));
		}
	}

	public int getItemCount() {
		return listaCiudades.size();
	}

	public void removeSelectedItem(){
		if(selectedPosition < listaCiudades.size()){
			listaCiudades.remove(selectedPosition);
			selectedPosition = -1;
			notifyDataSetChanged();
		}
	}

	public CiudadRs getItemSelected(){
		if(selectedPosition != -1){
			return listaCiudades.get(selectedPosition);
		}
		return null;
	}

	@Override
	public void onClick(View view) {
		selectedPosition = recyclerView.getChildLayoutPosition(view);
		CiudadRs ciudad = listaCiudades.get(selectedPosition);
		textViewSeleccionCiudad.setText("Seleccionaste: " + ciudad.toString());
	}

	//general la vista de cada uno de los renglones del edittext como un objeto del xml ciudadLista
	class ViewHolderCiudades extends RecyclerView.ViewHolder  {
		private TextView nombreciudad;

		public ViewHolderCiudades(@NonNull View itemView) {
			super(itemView);
			nombreciudad = itemView.findViewById(R.id.ciudadLista);
		}

		//Asigna a cada renglon el nombre de la ciudad y el pais de donde viene.
		public void asignarDatos(CiudadRs ciudad) {
			nombreciudad.setText(ciudad.getNombre() + ", " + ciudad.getPais());
		}

	}
}
