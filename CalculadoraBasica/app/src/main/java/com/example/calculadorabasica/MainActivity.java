package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
        tempVal = findViewById(id.lblSensorAcelerometro);
        activarSensorAcelerometro();
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

    private void activarSensorAcelerometro(){
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor ==null){
            tempVal.setText("Tu dispositivo no posee el sensor del acelerometro");
            finish();
        }
        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                tempVal.setText("Acelerometro: X="+ sensorEvent.values[0] + "; Y="+sensorEvent.values[1]+ "; Z="+sensorEvent.values[2]);
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
