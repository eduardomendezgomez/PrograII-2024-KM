package com.ugb.controlesbasicos;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Base64;

@RequiresApi(api = Build.VERSION_CODES.O)
public class utilidades {
    static String urlConsulta = "http://192.168.1.4:5984/amigos/_design/amigos/_view/amigos"; /*cambiar*/
    static String urlMto = "http://192.168.1.4:5984/amigos/"; /*cambiar*/
    static String user = "admin";
    static String passwd = "admin";
    static String credencialesCodificadas = Base64.getEncoder().encodeToString((user +":"+ passwd).getBytes());
    public String generarIdUnico(){
        return java.util.UUID.randomUUID().toString();
    }
}