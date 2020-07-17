package com.Desampara2.desamparados.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.RecyclerView;

import com.Desampara2.desamparados.Adaptador.AdaptadorTiendas;
import com.Desampara2.desamparados.Clases.LoadingDialog;
import com.Desampara2.desamparados.Clases.Network;
import com.Desampara2.desamparados.Clases.Tiendas;
import com.Desampara2.desamparados.FireBase.TiendaFirebase;
import com.Desampara2.desamparados.R;

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
        recyclerView=(RecyclerView) vista.findViewById(R.id.recyclerview_tienda);
        return vista;


    }
    @Override
    public void onResume() {
        if(Network.isOnline(getContext())){
            LoadingDialog loadingDialog = new LoadingDialog(this.getActivity());
            MostraLista(loadingDialog);
        }else{
            Toast.makeText(getContext(),"No hay conexión a internet", Toast.LENGTH_LONG).show();
        }
        super.onResume();
    }

    public void MostraLista(LoadingDialog loadingDialog){
        TiendaFirebase tiendaFirebase = new TiendaFirebase();
        tiendaFirebase.getTiendas(getContext(),recyclerView,loadingDialog);
    }
}
