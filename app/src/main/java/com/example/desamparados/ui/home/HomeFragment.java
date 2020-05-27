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
import com.example.desamparados.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    AdapterAvisos adaptadorAviso;
    private RecyclerView recyclerView;
    private ArrayList<Aviso>listaAvisos;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View vista=inflater.inflate(R.layout.fragment_home, container,false);

        recyclerView=(RecyclerView) vista.findViewById(R.id.recyclerview);
        listaAvisos=new ArrayList<>();
        AñadirDatos();//INGRESA OBJETOS A LA LISTA
        MostraLista();//MUESTRA LOS OBJETOD DE LA LISTA

        return vista;
    }
    //iNGRESA LOS DATOS A LA LISTA
    public void AñadirDatos(){
            listaAvisos.add(new Aviso("Chocolate","Manchas cafes es muy amistoso y recien tiene 4 meses",
                    R.drawable.descarga ));
            listaAvisos.add(new Aviso("Silvestre","Gato Gris de 3 años tiene un collar azul",
                    R.drawable.gato));
    }
    public void MostraLista(){
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL, false);
        adaptadorAviso =new AdapterAvisos(getContext(),listaAvisos);
       /* adaptadorAviso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adaptadorAviso);
    }

}
