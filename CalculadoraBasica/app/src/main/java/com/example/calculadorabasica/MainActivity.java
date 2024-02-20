package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    LocationManager locationManager;
    LocationListener locationListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        tempVal = findViewById(id.lblSensorGPS);
        obtenerPosicion();
    }
    private void obtenerPosicion(){
        try{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION},1);
                tempVal.setText("Solicitando permisos de localización");
            }

           /* Location location;
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        mostrarPosicion(location);*/
            locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(@NonNull Location location) {
                    mostrarPosicion(location);
                }
            };
        }catch (Exception e){
            tempVal.setText(e.getMessage());
        }
    }
    private void mostrarPosicion( Location location){
        tempVal.setText("Posicion Latitud: "+ location.getLatitude()+"; Longitud: "+ location.getLongitude()+"; Altitud: "+ location.getAltitude());
    }
}

