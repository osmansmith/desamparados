package com.example.desamparados;

import android.app.Application;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.desamparados.Activity.CrearAviso;


import com.example.desamparados.Activity.Iniciar_sesion;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private AppBarConfiguration mAppBarConfiguration;
    private  FloatingActionButton fab;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TOOLBAR
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //BOTON FLOTANTE
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(this);

        //NAVIGATION DRAWER
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        View headerView = navigationView.getHeaderView(0);

        TextView navUsername = (TextView) headerView.findViewById(R.id.userName);
        TextView navUserEmail = (TextView) headerView.findViewById(R.id.userEmail);
        ImageView navUserImage = (ImageView) headerView.findViewById(R.id.userImage);


        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null) {
            navUsername.setText(account.getDisplayName());
            navUserEmail.setText(account.getEmail());
            Picasso.get().load(account.getPhotoUrl()).into(navUserImage);
        }









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Este menu esta al lado derecho en la barra
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null) {
            getMenuInflater().inflate(R.menu.main, menu);
        }
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    //ABRIR EL ACTIVITY PARA CREAR UNA NUEVA PUBLICACION
    public void CrearAviso(View v){
        //Intent i =new Intent(this, CrearAviso.class);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
        if(account !=  null) {
            Intent i =new Intent(this, CrearAviso.class);
            startActivity(i);
        }else {
            Intent i = new Intent(this, Iniciar_sesion.class);
            startActivity(i);
        }

}

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == fab.getId()){

            CrearAviso(v);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.sing_out:
                // your action goes here
                GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getApplicationContext());
                if(account !=  null){
                    GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                            .requestIdToken(getString(R.string.default_web_client_id))
                            .requestEmail()
                            .build();

                    mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
                            mGoogleSignInClient.signOut();
                            Toast.makeText(MainActivity.this,"You are Logged Out",Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);

                   Toast.makeText(this,"cerrar sesión",Toast.LENGTH_LONG).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



}
