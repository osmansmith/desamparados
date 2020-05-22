package com.example.desamparados;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private AppBarConfiguration mAppBarConfiguration;
    private FloatingActionButton boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //BOTON FLOTANTE
        boton=(FloatingActionButton)findViewById(R.id.fab);
        boton.setOnClickListener(this);

        //MENU DESPLEGABLE
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_aviso, R.id.nav_salud, R.id.nav_petshop, R.id.nav_inicioSesion)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //PARA UTILIZAR EL BOTON FLOTANTE
    public void ShowDialog(){
        FragmentManager fragmentManager = getSupportFragmentManager ();
        CrearAviso newFragment = new CrearAviso ();
        FragmentTransaction transacción = fragmentManager.beginTransaction ();
        // Para un poco de pulido, especifique una animación de transición
        transacción.setTransition (FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        // Para que sea de pantalla completa, use la vista raíz 'contenido' como contenedor
        // para el fragmento, que siempre es la vista raíz de la actividad
        transacción.add (android.R.id.content, newFragment).addToBackStack (null) .commit ();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab:
                ShowDialog();
                break;
        }
    }
}

