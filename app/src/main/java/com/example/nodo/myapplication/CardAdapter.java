package com.example.nodo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nodo on 25/09/17.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.PersonViewHolder> implements Serializable{


    public List<Person> persons;



    public CardAdapter(List<Person> persons) {
        this.persons = persons;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_main, viewGroup, false);
        return new PersonViewHolder(v);
    }


    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, final int i) {
        final Context context = personViewHolder.itemView.getContext();
        final Person person = persons.get(i);

        personViewHolder.personName.setText(person.name);
        personViewHolder.personAge.setText(person.age);



        personViewHolder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(context,ShowActivity.class);
                intent.putExtra("data",person.name);

                context.startActivity(intent);

            }

        });

        Glide.with(context).load(person.image).into(personViewHolder.imageView);

    }

    public int getItemCount() {
        return persons.size();
    }


    class PersonViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.person_name) TextView personName;
        @BindView(R.id.person_age) TextView personAge;
        @BindView(R.id.image_view) ImageView imageView;

        PersonViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

    }

}
