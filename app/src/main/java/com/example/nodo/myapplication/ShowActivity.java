package com.example.nodo.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowActivity extends AppCompatActivity {
    private static final String EXTRA_PERSON = "person";

    @BindView(R.id.image_view_show)
    ImageView imageView;

    Person person;
    int position;

    @BindView(R.id.person_name)
    TextView personName;

    @BindView(R.id.person_age)
    TextView personAge;


    public static Intent launchIntent(Context context, Person person) {
        Intent intent = new Intent(context, ShowActivity.class);
        intent.putExtra(EXTRA_PERSON, person);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ButterKnife.bind(ShowActivity.this);

        person = (Person) getIntent().getSerializableExtra(EXTRA_PERSON);

        personName.setText(person.name);
        personAge.setText(person.age);

        Glide.with(this)
                .load(person.image)
                .into(imageView);


    }

}
