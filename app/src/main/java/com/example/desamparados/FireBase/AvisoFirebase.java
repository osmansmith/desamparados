package com.example.desamparados.FireBase;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.desamparados.Adaptador.AdapterAvisos;
import com.example.desamparados.Clases.Aviso;
import com.example.desamparados.Clases.TipoAviso;
import com.example.desamparados.Clases.TipoMascota;
import com.example.desamparados.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.UUID;

public class AvisoFirebase {

    public AvisoFirebase() {
    }

    /**
     * @param aviso objeto de la clase Aviso
     * @return boolean bandera, indicando si se insertó el aviso
     */
    public boolean insertarAviso(Aviso aviso){
        boolean bandera = false;
            try {
                DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                aviso.setId(UUID.randomUUID().toString());
                database.child("avisos").child(aviso.getId()).setValue(aviso);
                bandera = true;
            }catch(Exception e){
                System.out.println("ocurrió un error al insertar el aviso en la base de datos");
            }
         return bandera;
    }

    /**
     * @param context contexto de la actividad o fragment en la que se cargará la información
     * @param recyclerView recycler view en donde se cargarán los datos
     */
    public void getAvisos(final Context context, final RecyclerView recyclerView){
        final ArrayList<Aviso> avisos = new ArrayList<Aviso>();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("avisos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    Aviso aviso = new Aviso();
                    aviso.setId(ds.getKey());
                    aviso.setNombre(ds.child("nombre").getValue().toString());
                    aviso.setDescripcion(ds.child("descripcion").getValue().toString());
                    aviso.setImagen(R.drawable.gato);
                    aviso.setLatitud(Double.parseDouble(ds.child("latitud").getValue().toString()));
                    aviso.setLongitud(Double.parseDouble(ds.child("longitud").getValue().toString()));
                    aviso.setEstado(Integer.parseInt(ds.child("estado").getValue().toString()));
                    aviso.setTipoMascota(ds.child("tipoMascota").getValue(TipoMascota.class));
                    aviso.setTipoAviso(ds.child("tipoAviso").getValue(TipoAviso.class));
                    System.out.println(aviso.getTipoAviso().getId());
                    avisos.add(aviso);
                }

                LinearLayoutManager layoutManager=new LinearLayoutManager(context,LinearLayoutManager.VERTICAL, false);
                AdapterAvisos adaptadorAviso;
                adaptadorAviso =new AdapterAvisos(context,avisos);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(adaptadorAviso);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(context, "Ocurrió un error obteniendo los avisos",Toast.LENGTH_LONG).show();

            }
        });
    }


}
