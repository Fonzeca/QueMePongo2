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

public class UbicacionesFragment extends Fragment {

    private ViewPager mViewPager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_ubicaciones, container, false);

        return v;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mViewPager = view.findViewById(R.id.viewPagerUbicaciones);
        setViewPager(mViewPager);
        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    private void setViewPager(ViewPager viewPage) {
        AdaptadorTabView adaptador = new AdaptadorTabView(getFragmentManager());
        adaptador.addFragment(new fragment_ubicaciones_lista(),"Ubicaciones");
        adaptador.addFragment(new Fragment_mas_ubicaciones(),"Nueva ubicacion");
        viewPage.setAdapter(adaptador);
    }
    public ViewPager getViewPager(){
        return mViewPager;
    }
}