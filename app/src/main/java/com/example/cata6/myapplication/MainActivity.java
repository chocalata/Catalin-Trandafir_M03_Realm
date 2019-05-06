package com.example.cata6.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.cata6.myapplication.Adapter.PersonasAdapter;
import com.example.cata6.myapplication.model.Persona;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    private PersonasAdapter personasAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("asd","ASDDDDDDDDDDDDA");
        Realm.init(this);

        recyclerView = findViewById(R.id.personas_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personasAdapter = new PersonasAdapter(recyclerView);

        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        personasAdapter.setPersonas(realm.where(Persona.class).findAll());

        realm.commitTransaction();

        recyclerView.setAdapter(personasAdapter);

//        for (int i = personasAdapter.getPersonas().size(); i < 5; i++) {
//
//            realm.beginTransaction();
//
//            Persona persona = realm.createObject(Persona.class, "dni" + i);
//            persona.setEdat(i);
//            persona.setTel(i);
//            persona.setNom("nombre" + i);
//
//            realm.commitTransaction();
//        }



        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CrearPersonasActivity.class));
            }
        });
        findViewById(R.id.fab_refresh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();

                realm.beginTransaction();

                RealmResults<Persona> personas = realm.where(Persona.class).findAll();

                realm.commitTransaction();

                personasAdapter.setPersonas(personas);

                recyclerView.setAdapter(personasAdapter);

            }
        });
        findViewById(R.id.fab_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BuscarPersonaActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();

        personasAdapter.setPersonas(realm.where(Persona.class).findAll());

        realm.commitTransaction();

        recyclerView.setAdapter(personasAdapter);
    }
}
