package com.s21.quemepongo2front.creadoresDeFragments.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.s21.quemepongo2front.R;

import androidx.fragment.app.Fragment;

public class ShareFragment extends Fragment {

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_share, container, false);

        return v;
    }
}