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
import androidx.viewpager.widget.ViewPager;

public class UbicacionesFragment extends Fragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.fragment_ubicaciones, container, false);

		ViewPager mViewPager = v.findViewById(R.id.viewPagerUbicaciones);
		setViewPager(mViewPager);

		TabLayout tabLayout = v.findViewById(R.id.tabLayout);
		tabLayout.setupWithViewPager(mViewPager);
		return v;
	}

	public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
	}

	private void setViewPager(ViewPager viewPage) {
		AdaptadorTabView adaptador = new AdaptadorTabView(getChildFragmentManager());
		adaptador.addFragment(new Fragment_ubicaciones_lista(), "Ubicaciones");
		adaptador.addFragment(new Fragment_mas_ubicaciones(), "Nueva ubicacion");
		viewPage.setAdapter(adaptador);
	}

}