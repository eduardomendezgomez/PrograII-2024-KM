package com.example.calculadorabasica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CRUD_DB extends SQLiteOpenHelper {
    private static final String dbname ="tienda";
    private static final int v = 1;
    private static final String SQLdb= "CREATE TABLE tienda(idTienda INTEGER PRIMARY KEY AUTOINCREMENT, foto TEXT, codigo TEXT, descripcion TEXT, marca TEXT, presentacion TEXT, precio TEXT)"

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

}
