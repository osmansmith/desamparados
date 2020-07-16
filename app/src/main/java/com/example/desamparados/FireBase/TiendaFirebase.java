package com.example.desamparados.FireBase;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.desamparados.Adaptador.AdaptadorTiendas;
import com.example.desamparados.Clases.LoadingDialog;
import com.example.desamparados.Clases.Tiendas;

import com.example.desamparados.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TiendaFirebase {

    public TiendaFirebase() {
    }


    /**
     * @param context contexto de la actividad o fragment en la que se cargará la información
     * @param recyclerView recycler view en donde se cargarán los datos
     */
    public void getTiendas(final Context context, final RecyclerView recyclerView, final LoadingDialog loadingDialog){
        final ArrayList<Tiendas> tiendas = new ArrayList<Tiendas>();
        loadingDialog.startLoadingDialog("tiendas");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("tiendas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    Tiendas tienda = new Tiendas();
                    tienda.setId(Integer.parseInt(ds.getKey()));
                    tienda.setNombre(ds.child("nombre").getValue().toString());
                    tienda.setDescripcion(ds.child("descripcion").getValue().toString());
                    tienda.setDireccion(ds.child("direccion").getValue().toString());
                    tienda.setImagen(R.drawable.gato);
                    tienda.setImagen_firebase(ds.child("imagen_firebase").getValue().toString());
                    tienda.setLatitud(Double.parseDouble(ds.child("latitud").getValue().toString()));
                    tienda.setLongitud(Double.parseDouble(ds.child("longitud").getValue().toString()));
                    tiendas.add(tienda);
                }

                LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
                AdaptadorTiendas adaptadorTienda;
                adaptadorTienda =new AdaptadorTiendas(context,tiendas);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adaptadorTienda);
                loadingDialog.dismissDialog();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Ocurrió un error obteniendo las tienditas",Toast.LENGTH_LONG).show();

            }
        });

    }


}

