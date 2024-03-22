package com.example.calculadorabasica;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CRUD_DB extends SQLiteOpenHelper {
    private static final String dbname ="tienda";
    private static final int v = 1;
    private static final String SQLdb= "CREATE TABLE tienda(idTienda INTEGER PRIMARY KEY AUTOINCREMENT, foto TEXT, codigo TEXT, descripcion TEXT, marca TEXT, presentacion TEXT, precio TEXT)";

    public CRUD_DB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, dbname, factory, v);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLdb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            //Actualizar la estructura de la base de datos.
    }
    public String administrarTienda(String accion, String[] datos){
        try{
            SQLiteDatabase db = getWritableDatabase();
            String sql = "";
            if (accion.equals("nuevo")){
                sql = "INSERT INTO tienda(foto,codigo, descripcion,marca,presentacion,precio) VALUES('"+datos[1]+"','"+datos[2]+"','"+datos[3]+"','"+datos[4]+"','"+datos[5]+"','"+datos[6]+"')";
            }else if (accion.equals("modificar")){
                sql = "UPDATE tienda SET foto='"+datos[1]+"',codigo='"+datos[2]+"', descripcion='"+datos[3]+"',marca='"+datos[4]+"',presentacion='"+datos[5]+"',precio='"+datos[6]+"'";
            } else if (accion.equals("eliminar")) {
                sql = "DELETE FROM tiendas WHERE idTienda='"+datos[0]+"'";
            }
            db.execSQL(sql);
            return "ok";
        }catch (Exception e){
            return e.getMessage();
        }
    }
    public Cursor consultar_producto(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tienda ORDER BY idTienda",null);
        return cursor;
    }
}
