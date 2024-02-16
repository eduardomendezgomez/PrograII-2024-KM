package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TabHost tbh;
    TextView tempVal;
    Spinner spn;
    Button btn;
    conversores miObj = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        tbh = findViewById(id.tbhConversores);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("LOM").setContent(id.tabLongitud).setIndicator("LONGITUD",null));
        tbh.addTab(tbh.newTabSpec("ALM").setContent(id.tabAlmacenamiento).setIndicator("ALMACENAMIENTO",null));
        tbh.addTab(tbh.newTabSpec("MON").setContent(id.tabMonedas).setIndicator("MONEDAS",null));
        tbh.addTab(tbh.newTabSpec("MON").setContent(id.tabVolumen).setIndicator("VOLUMEN",null));
        tbh.addTab(tbh.newTabSpec("MON").setContent(id.tabTransferenciaDeDatos).setIndicator("TRANS. DE DATOS",null));
        tbh.addTab(tbh.newTabSpec("MON").setContent(id.tabTiempo).setIndicator("TIEMPO",null));
        tbh.addTab(tbh.newTabSpec("MON").setContent(id.tabMasa).setIndicator("MASA",null));

        btn = findViewById(id.btnConvertirLongitud);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeLongitud);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnALongitud);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadLongitud);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(0,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
        btn = findViewById(id.btnConvertirAlmacenamiento);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeAlmacenamiento);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnAAlmacenamiento);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadAlmacenamiento);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(1,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
        /*Monedas*/
        btn = findViewById(id.btnConvertirMonedas);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeMonedas);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnAMonedas);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadMonedas);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(2,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
        /*Tiempo*/
        btn = findViewById(id.btnConvertirTiempo);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeTiempo);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnATiempo);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadTiempo);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(3,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
        /*Masa*/
        btn = findViewById(id.btnConvertirMasa);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeMasa);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnAMasa);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadMasa);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(4,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
        /*Transferencia*/
        btn = findViewById(id.btnConvertirTransferencia);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeTransferencia);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnATransferencia);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadTransferencia);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(5,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
        /*Volumen*/
        btn = findViewById(id.btnConvertirVolumen);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeVolumen);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnAVolumen);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadVolumen);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double resp = miObj.convertir(6,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+resp, Toast.LENGTH_LONG).show();
            }
        });
    }
}

class conversores {
    double [][] valores = {
            {1,100,39.3701,3.28084,1.193,1.09361,0.001,0.000621371},
            {1,0.125 ,0.001 ,9.5367431641,1.073741824,1.09955116277761,125.899906842624,1.152921504606846976,1.180591620717411303424,1.208925819614629174706176},
            {1,0.93,7.81,2.02,36.82,24.69,516.64,8.75,1336.20,150.54},
            {1,0.01667,0.00027783333,1.1576389E-5 ,1.65377E-6},
            {1,0.001,0.0022046244201837776,0.03527399072294044,1000.0000000000001,100000 ,0.000001},
            {1,0.001,1000000,1000000000,1000000000000.00},
            {1,35.3147,61023.801579099}
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a]/valores[opcion][de]*cantidad;
    }
}