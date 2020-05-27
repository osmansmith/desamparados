package com.example.desamparados.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desamparados.Adaptador.AdaptadorTiendas;
import com.example.desamparados.Clases.Tiendas;
import com.example.desamparados.R;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {
    private AdaptadorTiendas adaptadorTiendas;
    private RecyclerView recyclerView;
    private ArrayList<Tiendas> listaTiendas;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_gallery, container,false);

        recyclerView=(RecyclerView) vista.findViewById(R.id.recyclerview);
        listaTiendas=new ArrayList<>();

        AñadirDatos();
        MostraLista();
        return vista;

    }

    public void AñadirDatos(){
        listaTiendas.add(new Tiendas("PetHappy","Veterinaria y petshop",
                R.drawable.floridaop ));
        listaTiendas.add(new Tiendas("mascogar","Todo para mascotas",
                R.drawable.mascogar));
    }
    public void MostraLista(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        adaptadorTiendas =new AdaptadorTiendas(getContext(),listaTiendas);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorTiendas);
    }
}
