package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        btn = findViewById(id.btnCalcular);

        btn .setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onClick(View view) {
                tempVal = findViewById(id.txtnum1);
                double num1 = Double.parseDouble(tempVal.getText().toString());

                tempVal =findViewById(id.txtnum2);
                double num2 = Double.parseDouble(tempVal.getText().toString());
                double respuesta = 0;
                spn = findViewById(id.spnOpciones);
                switch (spn.getSelectedItemPosition()){
                    case 0:
                        respuesta = num1+num2;
                        break;
                    case 1:
                        respuesta = num1-num2;
                        break;
                    case 2:
                        respuesta = num1*num2;
                        break;
                    case 3:
                        respuesta = num1/num2;
                        break;
                    case 4:
                        respuesta = (num2*num1)/100;
                        break;
                    case 5:
                      /*exponente*/
                        respuesta = Math.pow(num1,num2);
                        break;
                    case 6:
                        /*factorial*/
                            int cont = 1;
                            int fact = 1;
                            while (cont<=num2) {
                                fact = fact * cont;
                                cont++;
                            }
                            respuesta = fact;
                        break;
                    case 7:
                        /*raiz*/
                        respuesta = Math.pow(num1,(1/num2));
                        break;

                }
                tempVal = findViewById(id.lblrespuesta);
                tempVal.setText("Respuesta: "+respuesta);
            }
        });
    }
}