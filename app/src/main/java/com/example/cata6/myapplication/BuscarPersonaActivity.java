package com.example.cata6.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BuscarPersonaActivity extends AppCompatActivity {

    EditText nom;
    EditText dni;
    EditText edatBaixa;
    EditText edatAlta;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar_persona);
        nom = findViewById(R.id.buscar_nom);
        dni = findViewById(R.id.buscar_dni);
        edatBaixa = findViewById(R.id.edatBaixa);
        edatAlta = findViewById(R.id.edatAlta);

        findViewById(R.id.buscar_per_nom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
