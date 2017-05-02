package com.example.gabi.aplicacaologin.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gabi.aplicacaologin.R;

public class Consultas extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getText(R.string.consulta));
        return inflater.inflate(R.layout.fragment_consultas, container, false);
    }
}
