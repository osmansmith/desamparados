package com.example.desamparados.FireBase;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.desamparados.Clases.TipoAviso;
import com.example.desamparados.Clases.TipoMascota;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TipoAvisoFirebase {


    public TipoAvisoFirebase() {
    }









    /**
     * @param context contexto de la actividad o fragment en la que se cargará la información
     * @param spinner elemento en donde se carga la información
     * @return ArrayList<TipoMascota> con todos los tipos de aviso desde firebase
     */
    public ArrayList<TipoAviso> getTipoAvisos(final Context context, final Spinner spinner){
        final ArrayList<TipoAviso> arrayTipoAviso = new ArrayList<TipoAviso>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("tipo_avisos").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    TipoAviso tipoAviso = new TipoAviso();
                    tipoAviso.setId(Integer.parseInt(ds.getKey()));
                    tipoAviso.setNombre(ds.child("nombre").getValue().toString());
                    arrayTipoAviso.add(tipoAviso);
                }
                ArrayAdapter<TipoMascota> adapterMascota = new ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,arrayTipoAviso);
                spinner.setAdapter(adapterMascota);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TipoAviso tipo = (TipoAviso) parent.getItemAtPosition(position);
                        Toast.makeText(parent.getContext(), "Seleccionado" + tipo.getNombre(),Toast.LENGTH_LONG).show();
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        return arrayTipoAviso;
    }





}