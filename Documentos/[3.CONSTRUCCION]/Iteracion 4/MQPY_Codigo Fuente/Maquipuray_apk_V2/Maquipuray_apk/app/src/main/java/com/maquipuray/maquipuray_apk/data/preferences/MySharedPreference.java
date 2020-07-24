/*
 * Created by
 * Copyright Ⓒ 2019 . All rights reserved.
 */

package com.maquipuray.maquipuray_apk.data.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class MySharedPreference {
    private static MySharedPreference mInstance;
    private SharedPreferences prefs;
    private Context context;

    private GsonBuilder builder = new GsonBuilder();
    private Gson gson = builder.create();

    private MySharedPreference(Context mcontext){
        context = mcontext;
        prefs = context.getSharedPreferences(MYConstantsPreferences.SHARED_PREF, Context.MODE_PRIVATE);
    }

    public static synchronized MySharedPreference getInstance(Context mcontext) {
        if (mInstance == null) {
            mInstance = new MySharedPreference(mcontext);
        }
        return mInstance;
    }

    //region PREFERENCES LOGIN
    public void GuardarCod(int CodUsuario){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putInt(MYConstantsPreferences.MAQUIPURAY_USERID, CodUsuario);
        edits.apply();
    }
    public int GetCod(){
        return prefs.getInt(MYConstantsPreferences.MAQUIPURAY_USERID, 0);
    }

    public void Login(String token){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(MYConstantsPreferences.MAQUIPURAY_LOGIN, token);
        edits.apply();
    }
    public String isLogin(){
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_TOKEN, "");
    }

    public void Logout( ){
        //prefs.edit().clear().apply();
        prefs.edit().remove(MYConstantsPreferences.MAQUIPURAY_LOGIN).apply();
    }
    //endregion

    //region TOKEN PREFERENCE
    public void GuardarToken(String token){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(MYConstantsPreferences.MAQUIPURAY_TOKEN, token);
        edits.apply();
    }
    public String ObtenerToken(){
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_TOKEN, null);
    }

    public void BorrarToken( ){
        //prefs.edit().clear().apply();
        prefs.edit().remove(MYConstantsPreferences.MAQUIPURAY_TOKEN).apply();
    }
    //endregion

//    public void saveFavoritesMarkers(String product){
//        SharedPreferences.Editor edits = prefs.edit();
//        edits.putString(MYConstantsPreferences.FAVORITE_ID, product);
//        edits.apply();
//    }
//    public String retrieveFavorites(){
//        return prefs.getString(MYConstantsPreferences.FAVORITE_ID, null);
//    }
//    public void deleteAllFavorites( ){
//        //prefs.edit().clear().apply();
//        prefs.edit().remove(MYConstantsPreferences.FAVORITE_ID).apply();
//    }
//
//    public void addFavoriteCount(int productCount){
//        SharedPreferences.Editor edits = prefs.edit();
//        edits.putInt(MYConstantsPreferences.FAVORITE_COUNT, productCount);
//        edits.apply();
//    }
//    public int retrieveFavoriteCount(){
//        return prefs.getInt(MYConstantsPreferences.FAVORITE_COUNT, 0);
//    }
//
//    public void deleteAllFavoriteCount( ){
//        //prefs.edit().clear().apply();
//        prefs.edit().remove(MYConstantsPreferences.FAVORITE_COUNT).apply();
//    }





}
