package com.example.calculadorabasica;

import static com.example.calculadorabasica.R.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.File;

import android.net.Uri;

public class MainActivity extends AppCompatActivity {
    TextView tempVal;
    Button btn;
    FloatingActionButton fabRegresar;
    String idt="",accion = "nuevo";
    ImageView img;
    String urlCompletaFoto;
    Intent tomarFotoIntent;
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


                String[] datos = new String[]{idt,urlCompletaFoto,codigo,descripcion,marca,presentacion,precio};
                mostrarMsg(accion);
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
        img = findViewById(id.btnImgProducto);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tomarFotoProducto();
            }
        });
        mostrarDatosProductos();
    }
    private void tomarFotoProducto(){
        tomarFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File fotoProducto=null;
        try {
            fotoProducto = crearImagenProducto();
            if(fotoProducto!=null){
                Uri uriFotoAmigo = FileProvider.getUriForFile(MainActivity.this,"com.example.calculadorabasica.fileprovider",fotoProducto);
                tomarFotoIntent.putExtra(MediaStore.EXTRA_OUTPUT,uriFotoAmigo);
               startActivityForResult(tomarFotoIntent,1);
            }else{
                mostrarMsg("No se pudo tomar la foto");
            }
        }catch (Exception e){
            mostrarMsg("Error al abrir la camara"+ e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (requestCode==1 && resultCode==RESULT_OK){
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaFoto);
                img.setImageBitmap(imagenBitmap);
            }else{
                mostrarMsg("Se cancelo la toma de la foto");
            }
        }catch (Exception e){
            mostrarMsg("Error al seleccionar la foto "+e.getMessage());
        }
    }
    private File crearImagenProducto() throws Exception{
        String fechaHoraMs = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()), fileName ="imagen_"+fechaHoraMs+"_";
        File dirAlmacenamiento = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        if (dirAlmacenamiento.exists()==false){
            dirAlmacenamiento.mkdirs();
        }
        File image = File.createTempFile(fileName,".jpg",dirAlmacenamiento);
        urlCompletaFoto = image.getAbsolutePath();
        return image;
    }
    private void mostrarDatosProductos(){
        try {
            Bundle parametros= getIntent().getExtras();
            accion = parametros.getString("accion");

            if(accion.equals("modificar")){
                String[] productos = parametros.getStringArray("productos");
                idt = productos[0];

                tempVal = findViewById(id.txtCodigo);
                tempVal.setText(productos[2]);

                tempVal = findViewById(id.txtDescripcion);
                tempVal.setText(productos[3]);

                tempVal = findViewById(id.txtMarca);
                tempVal.setText(productos[4]);

                tempVal = findViewById(id.txtPresentacion);
                tempVal.setText(productos[5]);

                tempVal = findViewById(id.txtPrecio);
                tempVal.setText(productos[6]);

                urlCompletaFoto = productos[1];
                Bitmap imagenBitmap = BitmapFactory.decodeFile(urlCompletaFoto);
                img.setImageBitmap(imagenBitmap);
            }
        }catch (Exception e){
            mostrarMsg("Error al mostrar los datos amigos");
        }
    }

    private void mostrarMsg(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
    private void listarProductos(){
        Intent regresarLista = new Intent(getApplicationContext(),lista_productos.class);
        startActivity(regresarLista);
    }
}