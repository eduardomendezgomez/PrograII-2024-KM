package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    FloatingActionButton fabRegresar;
    String idt="",accion = "nuevo";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        
        fabRegresar = findViewById(id.btnListaProductos);
        fabRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regresarLista = new Intent(getApplicationContext(),lista_productos.class);
                startActivity(regresarLista);
            }
        });
        btn = findViewById(id.btnGuardarProducto);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tempVal = findViewById(id.txtCodigo);
                String codigo = tempVal.getText().toString();

                tempVal = findViewById(id.txtDescripcion);
                String descripcion = tempVal.getText().toString();

                tempVal = findViewById(id.txtMarca);
                String marca = tempVal.getText().toString();

                tempVal = findViewById(id.txtPresentacion);
                String presentacion = tempVal.getText().toString();

                tempVal = findViewById(id.txtPrecio);
                String precio = tempVal.getText().toString();


                String[] datos = new String[]{idt,codigo,descripcion,marca,presentacion,precio};
                CRUD_DB db = new CRUD_DB(getApplicationContext(),"",null,1);
                String respuesta = db.administrarTienda(accion,datos);
                if (respuesta.equals("ok")){
                    mostrarMsg("Producto guardado correctamente");
                    listarProductos();
                }else {
                    mostrarMsg("Erroral registrar el producto: "+respuesta);
                }
            }
        });
    }
    private void mostrarMsg(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    private void listarProductos(){
        Intent regresarLista = new Intent(getApplicationContext(),lista_productos.class);
        startActivity(regresarLista);
    }
}