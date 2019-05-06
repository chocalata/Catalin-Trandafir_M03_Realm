package com.example.cata6.myapplication.Adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cata6.myapplication.MainActivity;
import com.example.cata6.myapplication.ModificarPersonaActivity;
import com.example.cata6.myapplication.R;
import com.example.cata6.myapplication.model.Persona;

import io.realm.Realm;
import io.realm.RealmResults;

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonaViewHolder> {

    private RealmResults<Persona> personas;

    private RecyclerView recyclerView;

    public PersonasAdapter(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }


    public void setPersonas(RealmResults<Persona> personas) {
        this.personas = personas;
    }

    public RealmResults<Persona> getPersonas() {
        return personas;
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemPersona = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_persona, viewGroup, false);

        return new PersonaViewHolder(itemPersona);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder personaViewHolder, final int i) {
        final Persona persona = personas.get(i);

        personaViewHolder.persona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ModificarPersonaActivity.class);
                intent.putExtra("dniPersona", persona.getDni());
                v.getContext().startActivity(intent);
            }
        });

        personaViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();

                RealmResults<Persona> personaToDelete = realm.where(Persona.class).equalTo("dni", persona.getDni()).findAll();
                personaToDelete.deleteFromRealm(0);

                personas =  realm.where(Persona.class).findAll();

                recyclerView.setAdapter(PersonasAdapter.this);

                realm.commitTransaction();

            }
        });

        assert persona != null;
        personaViewHolder.tv_nom.setText(persona.getNom());
        personaViewHolder.tv_dni.setText(persona.getDni());
        personaViewHolder.tv_edat.setText(String.valueOf(persona.getEdat()));
        personaViewHolder.tv_tel.setText(String.valueOf(persona.getTel()));
    }

    @Override
    public int getItemCount() {
        return personas != null ? personas.size() : 0;
    }


    class PersonaViewHolder extends RecyclerView.ViewHolder{
        CardView persona;
        TextView tv_nom;
        TextView tv_dni;
        TextView tv_edat;
        TextView tv_tel;
        ImageButton delete;

        public PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            persona = itemView.findViewById(R.id.persona);
            tv_nom = itemView.findViewById(R.id.item_nom);
            tv_dni = itemView.findViewById(R.id.item_dni);
            tv_edat = itemView.findViewById(R.id.item_edat);
            tv_tel = itemView.findViewById(R.id.item_tel);
            delete = itemView.findViewById(R.id.delete);
        }
    }
}
