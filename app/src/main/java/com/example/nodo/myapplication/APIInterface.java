package com.example.nodo.myapplication;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nodo on 26/09/17.
 */

public interface APIInterface {

    @GET("user")
    Call<List<Person>> getUsers();

}
