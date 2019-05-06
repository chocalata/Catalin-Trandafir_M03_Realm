package com.example.cata6.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.cata6.myapplication.model.Persona;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class CrearPersonasActivity extends AppCompatActivity {
    EditText nom;
    EditText dni;
    EditText tel;
    EditText edat;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_personas);
        nom = findViewById(R.id.nom);
        dni = findViewById(R.id.dni);
        edat = findViewById(R.id.edat);
        tel = findViewById(R.id.tel);
        
        findViewById(R.id.crear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(crearPersona()) {
                    finish();
                }
            }
        });
    }

    private boolean crearPersona(){
        if(dni.getText().toString().equals("")){
            dni.setError("El dni es un campo obligatorio");
            return false;
        }

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();

        Persona persona = realm.createObject(Persona.class, dni.getText().toString());

        persona.setNom(nom.getText().toString());

        try{
            if(!tel.getText().toString().equals("")) {
                persona.setTel(Integer.parseInt(tel.getText().toString()));
            }
        }catch (NumberFormatException e){
            tel.setError("Coloque un telefono");
            realm.cancelTransaction();
            return false;
        }
        try {
            if (!edat.getText().toString().equals("")) {
                persona.setEdat(Integer.parseInt(edat.getText().toString()));
            }
        }catch (NumberFormatException e){
            edat.setError("Coloque una edad");
            realm.cancelTransaction();
            return false;
        }

        realm.commitTransaction();

        return true;

    }
}
