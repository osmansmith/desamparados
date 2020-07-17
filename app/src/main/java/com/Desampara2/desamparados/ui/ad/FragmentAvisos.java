package com.Desampara2.desamparados.ui.ad;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.Desampara2.desamparados.R;

public class FragmentAvisos extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_avisos, container,false);

        return vista;

    }
}
