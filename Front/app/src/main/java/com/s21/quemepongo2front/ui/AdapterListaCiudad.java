package com.s21.quemepongo2front.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.s21.quemepongo2front.R;
import com.s21.quemepongo2front.ui.ObjetosRS.CiudadRs;


import org.w3c.dom.Text;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterListaCiudad extends RecyclerView .Adapter<AdapterListaCiudad.ViewHolderciudades> {
    ArrayList<CiudadRs> listaCiudades;

    public AdapterListaCiudad(ArrayList<CiudadRs> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }


    public ViewHolderciudades onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_view,null,false);

        return new ViewHolderciudades(view);
    }

    public void onBindViewHolder(@NonNull ViewHolderciudades holder, int position) {
        holder.asignardatos(listaCiudades.get(position));

    }

    public int getItemCount() {
        return listaCiudades.size();
    }

    public class ViewHolderciudades extends RecyclerView.ViewHolder {
        TextView nombreciudad;
        public ViewHolderciudades(@NonNull View itemView) {
            super(itemView);
            nombreciudad= (TextView)itemView.findViewById(R.id.ciudadLista);
        }

        public void asignardatos(CiudadRs ciudad) {
            nombreciudad.setText(ciudad.getNombre()+", "+ciudad.getPais());
        }

    }
}
