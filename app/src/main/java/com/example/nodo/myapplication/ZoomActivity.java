package com.example.nodo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZoomActivity extends AppCompatActivity {

    Person person;
    @BindView(R.id.photo_view) PhotoView photoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom);
        ButterKnife.bind(ZoomActivity.this);

        person = (Person) getIntent().getSerializableExtra("person");

        photoView.setMinimumScale(0.2f);
        photoView.setMaximumScale(1.8f);

        Glide.with(this).load(person.image).into(photoView);

    }
}