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
    }
}

class conversores {
    double [][] valores = {
            {1,100,39.3701,3.28084,1.193,1.09361,0.001,0.000621371},
            {1},
            {1}
    };
    public double convertir(int opcion, int de, int a, double cantidad){
        return valores[opcion][a]/valores[opcion][de]*cantidad;
    }
}