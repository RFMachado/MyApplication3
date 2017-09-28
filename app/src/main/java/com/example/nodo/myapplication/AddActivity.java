package com.example.nodo.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Digits;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddActivity extends AppCompatActivity implements Validator.ValidationListener{
    static final String RESULT_PERSON = "person";

    @BindView(R.id.button_send)
    Button buttonSend;

    @NotEmpty
    @BindView(R.id.edittext_name)
    EditText editTextName;

    @NotEmpty
    @Max(100)
    @BindView(R.id.edittext_age)
    EditText editTextAge;

    Validator validator = new Validator(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        validator.setValidationListener(this);

        ButterKnife.bind(this);

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validator.validate();
            }
        });

    }

    @Override
    public void onValidationSucceeded() {
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
        Person person = new Person();

        person.name = editTextName.getText().toString();
        person.age = editTextAge.getText().toString();
        person.type = "normal";
        person.image = "https://d30y9cdsu7xlg0.cloudfront.net/png/15724-200.png";

        Intent intent = getIntent();
        intent.putExtra(RESULT_PERSON, person);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

}
