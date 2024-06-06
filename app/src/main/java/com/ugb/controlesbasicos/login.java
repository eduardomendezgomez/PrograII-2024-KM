package com.ugb.controlesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//para mostrar el texto se supone
        EditText txtContrasena =findViewById(R.id.txtContrasena);
        String password = txtContrasena.getText().toString();
    }
}