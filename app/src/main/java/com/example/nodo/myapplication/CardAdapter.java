package com.example.nodo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by nodo on 25/09/17.
 */

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Serializable{
    private final int TYPE_NORMAL = 0;
    private final int TYPE_IMAGE = 1;
    public int selectedItem = RecyclerView.NO_POSITION;


    public List<Person> persons;


    public CardAdapter(List<Person> persons) {
        this.persons = persons;
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        if (viewType == TYPE_NORMAL)
            return new NormalViewHolder(inflater.inflate(R.layout.item_card_normal, viewGroup, false));
        else
            return new ImageViewHolder(inflater.inflate(R.layout.item_card_image, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder viewHolder, final int position) {

        final Context context = viewHolder.itemView.getContext();
        final Person person = persons.get(position);


        if (viewHolder instanceof NormalViewHolder) {
            final NormalViewHolder normalViewHolder = (NormalViewHolder) viewHolder;


            normalViewHolder.personName.setText(person.name);
            normalViewHolder.personAge.setText(person.age);
            normalViewHolder.radioButton.setChecked(selectedItem == position);


            Glide.with(context)
                    .load(person.image)
                    .into(normalViewHolder.imageView);

            normalViewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notifyItemChanged(selectedItem);
                    selectedItem = normalViewHolder.getAdapterPosition();
                }
            });

            normalViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    normalViewHolder.radioButton.performClick();
                }
            });


            normalViewHolder.imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    if(normalViewHolder.radioButton.isChecked()) {
                        Intent intent = new Intent(context, ZoomActivity.class);

                        intent.putExtra("person", person);

                        context.startActivity(intent);
                    }

                }

            });



            Glide.with(context).load(person.image).into(((NormalViewHolder) viewHolder).imageView);

        }
        else if (viewHolder instanceof ImageViewHolder) {
            final ImageViewHolder imageViewHolder = (ImageViewHolder) viewHolder;

            imageViewHolder.imageView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){

                    Intent intent = new Intent(context, ZoomActivity.class);

                    intent.putExtra("person", person);

                    context.startActivity(intent);

                }

            });


            Glide.with(context).load(person.image).into(((ImageViewHolder) viewHolder).imageView); //envio
        }


    }

    @Override
    public int getItemViewType(int position) {
        if (persons.get(position).type.equals("normal"))
            return TYPE_NORMAL;
        else
            return TYPE_IMAGE;
    }


    public int getItemCount() {
        return persons.size();
    }


    class NormalViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.person_name) TextView personName;
        @BindView(R.id.person_age) TextView personAge;
        @BindView(R.id.image_view) ImageView imageView;
        @BindView(R.id.radio) RadioButton radioButton;

        NormalViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }
    }


    class ImageViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_view) ImageView imageView;

        ImageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

        }

    }


}
