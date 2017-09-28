package com.example.nodo.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CREATE = 98;
    RecyclerView rv ;
    APIInterface apiInterface;
    List<Person> users = new ArrayList<>();
    CardAdapter adapter = new CardAdapter(users);

    @BindView(R.id.button_more)
    Button buttonMore;

    @BindView(R.id.button_remove) Button buttonRemove;
    Person personSend;

    @BindView(R.id.button_add) Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        rv = findViewById(R.id.rv);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        apiInterface = APICliente.conect();

        receiveData();

        initializeAdapter();

    }

    @OnClick(R.id.button_remove)
    public void onClickRemove(){
        if (adapter.selectedItem == -1) {
            Toast.makeText(MainActivity.this,"Select item",Toast.LENGTH_LONG).show();

        }else{
            users.remove(adapter.selectedItem);
            adapter.notifyItemRemoved(adapter.selectedItem);
        }
    }

    @OnClick(R.id.button_add)
    public void onClickAdd(){
        final Intent intent = new Intent(MainActivity.this, AddActivity.class);
        startActivityForResult(intent, REQUEST_CREATE);
    }


    @OnClick(R.id.button_more)
    public void onClickMore() {
        if (adapter.selectedItem == -1) {
            Toast.makeText(MainActivity.this,"Select item",Toast.LENGTH_LONG).show();
        }
        else{
            personSend=users.get(adapter.selectedItem);
            Intent intent = ShowActivity.launchIntent(MainActivity.this, personSend);
            startActivity(intent);
        }
    }

    public void receiveData(){
        Call<List<Person>> call = apiInterface.getUsers();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                if (response.isSuccessful()) {
                    users.addAll(response.body());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Toast.makeText(MainActivity.this,"App Fail",Toast.LENGTH_LONG).show();
            }
        });

    }


    private void initializeAdapter(){
        rv.setAdapter(adapter);
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CREATE) {
                Person person = (Person) data.getSerializableExtra(AddActivity.RESULT_PERSON);
                users.add(person);
                adapter.notifyDataSetChanged();

            }
        }
    }

}
