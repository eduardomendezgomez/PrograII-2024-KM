package com.example.calculadorabasica;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class adaptadorImagenes extends BaseAdapter {
    Context context;
    ArrayList<productos> datosProductosArrayList;
    productos misproductos;
    LayoutInflater layoutInflater;
    public adaptadorImagenes(Context context, ArrayList<productos> datosProductosArrayList){
        this.context = context;
        this.datosProductosArrayList = datosProductosArrayList;
    }

    @Override
    public int getCount() {
        return datosProductosArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return datosProductosArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(datosProductosArrayList.get(i).getIdTienda());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.listview_imagenes,viewGroup,false);
        try {
            misproductos = datosProductosArrayList.get(i);
            TextView tempVal = itemView.findViewById(R.id.lblcodigo);
            tempVal.setText(misproductos.getCodigo());

            tempVal = itemView.findViewById(R.id.lblpresentacion);
            tempVal.setText(misproductos.getPresentacion());

            tempVal = itemView.findViewById(R.id.lblmarca);
            tempVal.setText(misproductos.getMarca());

            tempVal = itemView.findViewById(R.id.lbldescripcion);
            tempVal.setText(misproductos.getDescripcion());

            tempVal = itemView.findViewById(R.id.lblprecio);
            tempVal.setText(misproductos.getPrecio());

            ImageView imageView = itemView.findViewById(R.id.imgFoto);
            Bitmap imagenBitmap = BitmapFactory.decodeFile(misproductos.getFoto());
            imageView.setImageBitmap(imagenBitmap);
        }catch (Exception e){
            Toast.makeText(context,"Error en Adaptador imagenes: "+e.getMessage(),Toast.LENGTH_LONG).show();
        }
        return itemView;
    }
}
