package com.maquipuray.maquipuray_apk.data.remote.model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by  on 26/06/2020.
 * Copyright Ⓒ 2020 . All rights reserved.
 */
public class GenericClass<T> {

    List<T> Genericlist = new ArrayList<T>();
    public GenericClass(){

    }
    public void populate(T t){
        Genericlist.add(t);
    }

    public void savePreferences(String valueSharePreferences){
        Type type = new TypeToken<ArrayList<T>>() {}.getType();

        Genericlist = new Gson().fromJson(valueSharePreferences, type);
        if (Genericlist == null) {
            Genericlist = new ArrayList<>();
        }
    }

}
