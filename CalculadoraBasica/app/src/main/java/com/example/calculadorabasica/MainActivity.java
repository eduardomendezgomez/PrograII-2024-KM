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
    TextView tempVal;
    TabHost tbh;
    Button btn;
    Spinner spn;
    conversores miObj = new conversores();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        tbh = findViewById(id.tbhConsumidos);
        tbh.setup();

        tbh.addTab(tbh.newTabSpec("CON").setContent(id.TabTarifaMetrosConsumidos).setIndicator("Metros Consumidos",null));
        tbh.addTab(tbh.newTabSpec("AREA").setContent(id.tabConversorArea).setIndicator("Conversor de area",null));

        btn = findViewById(id.btnMtsConsumidos);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                tempVal = findViewById(id.txtMetrosConsumidos);
                double num1 = Double.parseDouble(tempVal.getText().toString());


                if(num1 >= 1 && num1<=18){
                    double resp = 6;
                    Toast.makeText(getApplicationContext(),"Total a pagar: "+ resp + " Dolares", Toast.LENGTH_LONG).show();

                }else if (num1 >= 19 && num1 <=28){
                    double resp = ((num1 - 18)*0.45) +6;
                    Toast.makeText(getApplicationContext(),"Total a pagar: "+ resp +" Dolares", Toast.LENGTH_LONG).show();
                }else{
                    double resp = (((num1 -28)*0.65) +((28 -18)*0.45))+6;

                    Toast.makeText(getApplicationContext(),"Total a pagar: "+ resp +" Dolares", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*CONVERSOR DE AREAS*/
        btn = findViewById(id.btnConvertirArea);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spn = findViewById(id.spnDeArea);
                int de = spn.getSelectedItemPosition();

                spn = findViewById(id.spnAArea);
                int a = spn.getSelectedItemPosition();

                tempVal = findViewById(id.txtCantidadConvertir);
                double cantidad = Double.parseDouble(tempVal.getText().toString());

                double respu = miObj.convertir(0,de,a,cantidad);
                Toast.makeText(getApplicationContext(),"Respuesta: "+ respu, Toast.LENGTH_LONG).show();
            }
        });

    }
}
class conversores {
    double [ ][ ] valores = {
            {1,0.6988,7.52,0.0000698,0.8357,10000,1000}
    };
    public double convertir(int opcion,int de, int a, double cantidad){
        return valores[opcion][a]/valores[opcion][de]*cantidad;
    }
}