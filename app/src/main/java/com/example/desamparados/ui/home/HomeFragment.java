package com.example.desamparados.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desamparados.Adaptador.AdapterAvisos;
import com.example.desamparados.Clases.Aviso;
import com.example.desamparados.FireBase.AvisoFirebase;
import com.example.desamparados.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_home, container,false);

        recyclerView=(RecyclerView) vista.findViewById(R.id.recyclerview);
        MostraLista();//muestra la lista
        return vista;

    }

    public void MostraLista(){
       AvisoFirebase avisoFirebase = new AvisoFirebase();
       avisoFirebase.getAvisos(getContext(),recyclerView);
    }

}
