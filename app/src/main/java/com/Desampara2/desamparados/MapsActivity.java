package com.Desampara2.desamparados;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.Desampara2.desamparados.Activity.CrearAviso;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        this.myLocation();
        getLocationName();
        LatLng drawer = new LatLng(CrearAviso.latitude, CrearAviso.longitude);
        CameraUpdate myLocation = CameraUpdateFactory.newLatLngZoom(drawer, 16); //hace zoom a la canara
        mMap.animateCamera(myLocation);
        mMap.addMarker(new MarkerOptions().position(drawer)
                .title("Mi ubicación actual")
                .snippet("nombre de la dirección actuak")

        );//añade un marcador a la ubicación actual
    }

    //setea la longitud y la latitud actual
    private void updateLocation(Location location) {
        if (location != null) {
            CrearAviso.latitude = location.getLatitude();
            CrearAviso.longitude = location.getLongitude();
            CrearAviso.latitud.setText(String.valueOf(location.getLatitude()));
            CrearAviso.longitud.setText(String.valueOf(location.getLongitude()));
    }}

    //cambia la ubicación si se va moviendo
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateLocation(location);
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        @Override
        public void onProviderEnabled(String provider) {
        }
        @Override
        public void onProviderDisabled(String provider) {
        }
    };


    //método que pide autorización para el mapa, y encuentra la ubicción actual
    public void myLocation() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        this.updateLocation(location);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000, 0, locationListener);

        LocationServices.getFusedLocationProviderClient(MapsActivity.this).getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MapsActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                mMap.setMyLocationEnabled(true);

            }
        });

    }

        //obtiene el nombre de la dirección actual y la setea en el campo de texto
        public void getLocationName(){
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> list = null;
            try {
                list = geocoder.getFromLocation(
                        CrearAviso.latitude,CrearAviso.longitude, 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!list.isEmpty()) {
                Address DirCalle = list.get(0);
                String address = DirCalle.getAddressLine(0);
                Toast.makeText(this," Dirección: " + address, Toast.LENGTH_LONG).show();
                CrearAviso.direccion.setText(address);

            }
        }



    }
