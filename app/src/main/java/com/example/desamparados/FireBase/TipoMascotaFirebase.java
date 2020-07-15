package com.example.desamparados.FireBase;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.desamparados.Activity.CrearAviso;
import com.example.desamparados.Clases.TipoMascota;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TipoMascotaFirebase {


    public TipoMascotaFirebase() {
    }








    /**
     * * @param context contexto de la actividad o fragment en la que se cargará la información
    * @param spinner elemento en donde se carga la información
    * @return ArrayList<TipoMascota> con todos los tipos de mascotas desde firebase
    */
    public ArrayList<TipoMascota> getTipoMascotas(final Context context, final Spinner spinner){
        final ArrayList<TipoMascota> arrayTipoMascota = new ArrayList<TipoMascota>();

        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        database.child("tipo_mascotas").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot ds : snapshot.getChildren()){
                    TipoMascota tipoMascota = new TipoMascota();
                    tipoMascota.setId(Integer.parseInt(ds.getKey()));
                    tipoMascota.setNombre(ds.child("nombre").getValue().toString());
                    arrayTipoMascota.add(tipoMascota);
                }
                ArrayAdapter<TipoMascota> adapterMascota = new ArrayAdapter(context,android.R.layout.simple_dropdown_item_1line,arrayTipoMascota);
                spinner.setAdapter(adapterMascota);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        TipoMascota tipo = (TipoMascota) parent.getItemAtPosition(position);
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
        return arrayTipoMascota;
    }
}