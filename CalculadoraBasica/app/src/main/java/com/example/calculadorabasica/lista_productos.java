package com.example.calculadorabasica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
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
        buscarProductos();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mimenu,menu);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        cproductos.moveToPosition(info.position);
        menu.setHeaderTitle(cproductos.getString(2));//codigo
    }

    @Override
    public boolean onOptionsItemSelected( MenuItem item) {
        try {
            int id = item.getItemId();

            if (id == R.id.mnxAgregar) {
                parametros.putString("accion", "nuevo");
                abrirActividad(parametros);
            } else if (id == R.id.mnxModificar) {
                String[] productos = {
                        cproductos.getString(0),
                        cproductos.getString(1),
                        cproductos.getString(2),
                        cproductos.getString(3),
                        cproductos.getString(4),
                        cproductos.getString(5),
                        cproductos.getString(6),
                };


                parametros.putString("accion", "modificar");
                parametros.putStringArray("productos", productos);
                abrirActividad(parametros);
            } else if (id == R.id.mnxEliminar) {
                eliminarProductos();

            }

        }catch (Exception e){
            mostrarMsg("Error alseleccionar una opcion del menu: "+e.getMessage());
        }
        return super.onOptionsItemSelected(item);
    }
    private void eliminarProductos(){
        try {
            AlertDialog.Builder confirmar = new AlertDialog.Builder(lista_productos.this);
            confirmar.setTitle("Estas seguro de eliminar a: ");
            confirmar.setMessage(cproductos.getString(2));//codigo
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
            });
            confirmar.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            confirmar.create().show();
        }catch (Exception e){
            mostrarMsg("Error al eliminar: "+ e.getMessage());
        }
    }
    private void buscarProductos(){
        TextView tempVal = findViewById(R.id.txtBuscarProducto);
        tempVal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                try {
                    alProductos.clear();
                    String valor= tempVal.getText().toString().trim().toLowerCase();
                    if (valor.length()<=0){
                        alProductos.addAll(alProductosCopy);
                    }else {
                        for (productos producto: alProductosCopy){
                            String codigo = producto.getCodigo();
                            String presentacion = producto.getPresentacion();
                            String descripcion = producto.getDescripcion();
                            String marca = producto.getMarca();
                            String precio = producto.getPrecio();
                            if (presentacion.trim().toLowerCase().contains(valor) || codigo.trim().toLowerCase().contains(valor) || descripcion.trim().toLowerCase().contains(valor) || marca.trim().toLowerCase().contains(valor) || precio.trim().toLowerCase().contains(valor)) {
                                alProductos.add(producto);
                            }
                        }
                        adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(),alProductos);
                        lts.setAdapter(adaptadorImagenes);
                    }
                }catch (Exception e){
                    mostrarMsg("Error al buscar: "+e.getMessage());
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void obtenerDatosProductos(){
        try {
            alProductos.clear();
            alProductosCopy.clear();
            db = new CRUD_DB(getApplicationContext(), "", null, 1);
            cproductos = db.consultar_producto();
            if (cproductos.moveToFirst()) {
                lts.findViewById(R.id.ltsProductos);
                do {
                    misproductos = new productos(
                            cproductos.getString(0),
                            cproductos.getString(1),
                            cproductos.getString(2),
                            cproductos.getString(3),
                            cproductos.getString(4),
                            cproductos.getString(5),
                            cproductos.getString(6)
                    );
                    alProductos.add(misproductos);
                } while (cproductos.moveToNext());
                alProductosCopy.addAll(alProductos);
                adaptadorImagenes adaptadorImagenes = new adaptadorImagenes(getApplicationContext(), alProductos);
                lts.setAdapter(adaptadorImagenes);

                registerForContextMenu(lts);
            } else {
                parametros.putString("accion", "nuevo");
                abrirActividad(parametros);
                mostrarMsg("No hay datos de productos");
            }
        }catch (Exception e){
            mostrarMsg("Error al obtener los productos"+e.getMessage());
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