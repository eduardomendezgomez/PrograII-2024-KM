package com.example.calculadorabasica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class lista_productos extends AppCompatActivity {
    ListView lts;
    FloatingActionButton fab;
    Cursor cproductos;
    CRUD_DB db;
    Bundle parametros = new Bundle();
    productos misproductos;
    final ArrayList<productos> alProductos = new ArrayList<productos>();
    final ArrayList<productos> alProductosCopy = new ArrayList<productos>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_productos);
        fab = findViewById(R.id.fabAgregarProducto);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parametros.putString("accion","nuevo");
                abrirActividad(parametros);
            }
        });
        obtenerDatosProductos();
        buscarAmigos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu,menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        cproductos.moveToPosition(info.position);
        menu.setHeaderTitle(cproductos.getString(2))//codigo
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        try {
            switch (item.getItemId()){
                case R.id.mnxAgregar:
                    parametros.putString("accion","nuevo");
                    abrirActividad(parametros);
                    break;
                case R.id.mnxModificar:
                    String[]productos = {
                            cproductos.getString(0),
                            cproductos.getString(1),
                            cproductos.getString(2),
                            cproductos.getString(3),
                            cproductos.getString(4),
                            cproductos.getString(5),
                            cproductos.getString(6),
                    };
                    parametros.putString("accion","modificar");
                    parametros.putStringArray("productos",productos);
                    abrirActividad(parametros);
                    break;
                case R.id.mnxEliminar:
                    eliminarProductos();
                    break;
            }
            return true;
        }catch (Exception e){
            mostrarMsg("Error alseleccionar una opcion del menu: "e.getMessage());
            return super.onContextItemSelected(item);
        }
    }
    private void    eliminarProductos(){
        try {
            AlertDialog.Builder confirmar = new AlertDialog.Builder(lista_productos.this);
            confirmar.setTitle("Estas seguro de eliminar a: ");
            confirmar.setMessage(cproductos.getString(2))//codigo
            confirmar.setPositiveButton("SI", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String respuesta = db.administrarTienda("eliminar",new String[]{cproductos.getString(0)});//codigo
                    if (respuesta.equals("ok")){
                        mostrarMsg("Producto eliminado con exito");
                        obtenerDatosProductos();
                    }else {
                        mostrarMsg("Error al eliminar el producto"+respuesta);
                    }
                }
            })
        }
    }

    private void abrirActividad(Bundle parametros){
        Intent abrirActividad = new Intent(getApplicationContext(),MainActivity.class);
        abrirActividad.putExtras(parametros);
        startActivity(abrirActividad);
    }
    private void mostrarMsg(String msg){
        Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_LONG).show();
    }
}