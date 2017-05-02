package com.example.gabi.aplicacaologin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Pacientes extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().setTitle(getResources().getText(R.string.pacientes));
        return inflater.inflate(R.layout.fragment_pacientes, container, false);
    }
}
