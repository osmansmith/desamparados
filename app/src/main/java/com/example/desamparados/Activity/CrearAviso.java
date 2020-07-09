package com.example.desamparados.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.desamparados.MapsActivity;
import com.example.desamparados.R;

public class CrearAviso extends AppCompatActivity implements View.OnClickListener {
    public static double latitude;
    public static double longitude;
    public static EditText p_latitude;
    public static EditText p_longitude;
    public static EditText p_address;
    Button btn_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_aviso);
        p_latitude=(EditText) findViewById(R.id.p_latitude);
        p_longitude=(EditText) findViewById(R.id.p_longitude);
        p_address=(EditText) findViewById(R.id.p_address);
        btn_map = (Button) findViewById(R.id.btn_map) ;
        btn_map.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_map:
                Intent i = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(i);
                break;
        }
    }
}
