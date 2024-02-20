package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        tempVal = findViewById(id.lblSensorPoximidad);
        activarSensorProximidad();
    }

    @Override
    protected void onResume() {
        iniciar();
        super.onResume();
    }

    @Override
    protected void onPause() {
        detener();
        super.onPause();
    }

    private void activarSensorProximidad(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        if(sensor ==null){
            tempVal.setText("Tu dispositivo NO POSEE el sensor de proximidad");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
               double valor = sensorEvent.values[0];
               tempVal.setText("Luz: "+valor);
               if(valor<=4){
                   getWindow().getDecorView().setBackgroundColor(Color.BLUE);
               }else if (valor <=8){
                   getWindow().getDecorView().setBackgroundColor(Color.RED);
               }else {
                   getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
               }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
    }
    private void iniciar(){
        sensorManager.registerListener(sensorEventListener,sensor,2000*1000);
    }
    private void detener(){
        sensorManager.unregisterListener(sensorEventListener);
    }
}

