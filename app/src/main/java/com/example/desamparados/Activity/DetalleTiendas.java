package com.example.desamparados.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desamparados.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.squareup.picasso.Picasso;

public class DetalleTiendas extends AppCompatActivity {
    TextView nombre;
    TextView descripcion;
    TextView direccion;
    ImageView imagen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tiendas);

        nombre         =   (TextView) findViewById(R.id.nombre);
        descripcion    =   (TextView) findViewById(R.id.descripcion);
        direccion      =   (TextView) findViewById(R.id.direccion);
        imagen         =   (ImageView)findViewById(R.id.imagen);


        Picasso.get().load(getIntent().getStringExtra("imagen_firebase")).into(imagen);
        nombre.setText(getIntent().getStringExtra("nombre").toUpperCase());
        descripcion.setText(getIntent().getStringExtra("descripcion"));
        direccion.setText(getIntent().getStringExtra("direccion"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //para mostrar el icono para agregar un favorito
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.favorito, menu);
        return true;
    }
}
