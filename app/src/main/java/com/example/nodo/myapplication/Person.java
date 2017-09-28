package com.example.nodo.myapplication;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nodo on 25/09/17.
 */

public class Person implements Serializable {

    @SerializedName("name")
    String name;

    @SerializedName("age")
    String age;

    @SerializedName("image")
    String image;

    @SerializedName("type")
    String type;


}
