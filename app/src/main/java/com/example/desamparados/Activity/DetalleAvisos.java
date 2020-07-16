package com.example.desamparados.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.desamparados.R;
import com.squareup.picasso.Picasso;

public class DetalleAvisos extends AppCompatActivity {
    TextView estado;
    TextView nombre;
    TextView descripcion;
    TextView direccion;
    TextView tipoAviso;
    TextView tipoMascota;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_avisos);
         estado         =   (TextView) findViewById(R.id.estado);
         nombre         =   (TextView) findViewById(R.id.nombre);
         descripcion    =   (TextView) findViewById(R.id.descripcion);
         direccion      =   (TextView) findViewById(R.id.direccion);
         tipoAviso      =   (TextView) findViewById(R.id.tipoAviso);
         tipoMascota    =   (TextView) findViewById(R.id.tipoMascota);
         imagen         =   (ImageView)findViewById(R.id.imagen);

        String status = getIntent().getStringExtra("estado");
        if(status.equalsIgnoreCase("1")){
            status = "Abierto";
        }else{
            status = "Cerrado";
        }
        estado.setText(status);
        Picasso.get().load(getIntent().getStringExtra("imagen_firebase")).into(imagen);
        nombre.setText(getIntent().getStringExtra("nombre").toUpperCase());
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        direccion.setText(getIntent().getStringExtra("direccion"));
        tipoAviso.setText(getIntent().getStringExtra("tipo_aviso"));
        tipoMascota.setText(getIntent().getStringExtra("tipo_mascota"));

    }


}