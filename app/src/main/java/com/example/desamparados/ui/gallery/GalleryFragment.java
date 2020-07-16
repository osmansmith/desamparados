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
import com.example.desamparados.Clases.LoadingDialog;
import com.example.desamparados.Clases.Tiendas;
import com.example.desamparados.FireBase.AvisoFirebase;
import com.example.desamparados.FireBase.TiendaFirebase;
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
        //layout = (LinearLayout) vista.findViewById(R.id.layout_progress);
        recyclerView=(RecyclerView) vista.findViewById(R.id.recyclerview);
        LoadingDialog loadingDialog = new LoadingDialog(this.getActivity());
        MostraLista(loadingDialog);
        return vista;


    }

    public void MostraLista(LoadingDialog loadingDialog){
        TiendaFirebase tiendaFirebase = new TiendaFirebase();
        tiendaFirebase.getTiendas(getContext(),recyclerView,loadingDialog);
    }
}
