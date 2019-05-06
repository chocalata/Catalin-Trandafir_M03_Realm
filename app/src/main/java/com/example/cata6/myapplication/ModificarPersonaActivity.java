package com.example.cata6.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.cata6.myapplication.model.Persona;

import io.realm.Realm;
import io.realm.RealmResults;

public class ModificarPersonaActivity extends AppCompatActivity {

    EditText nom;
    EditText edat;
    EditText tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_persona);

        nom = findViewById(R.id.modificar_nom);
        edat = findViewById(R.id.modificar_edat);
        tel = findViewById(R.id.modificar_tel);

        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        final Persona persona = realm.where(Persona.class).equalTo("dni", getIntent().getStringExtra("dniPersona")).findFirst();

        nom.setText(persona.getNom());
        edat.setText(String.valueOf(persona.getEdat()));
        tel.setText(String.valueOf(persona.getTel()));

        realm.commitTransaction();

        findViewById(R.id.modificar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();

                persona.setNom(nom.getText().toString());
                persona.setTel(Integer.parseInt(tel.getText().toString()));
                persona.setEdat(Integer.parseInt(edat.getText().toString()));

                realm.insertOrUpdate(persona);

                realm.commitTransaction();;

                finish();
            }
        });

    }
}
