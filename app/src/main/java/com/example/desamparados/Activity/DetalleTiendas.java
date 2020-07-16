package com.example.desamparados.Activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.example.desamparados.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class DetalleTiendas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_tiendas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       //para mostrar el icono para agregar un favorito
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.favorito, menu);
        return true;
    }
}
