/*
 * Created by
 * Copyright Ⓒ 2019 . All rights reserved.
 */

package com.maquipuray.maquipuray_apk.data.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.maquipuray.maquipuray_apk.ui.main.LoginActivity;


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
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_LOGIN, "");
    }

    public void checkLogin(){
        // Check login status
        if(this.isLogin().equals("Login")){
            // user is not logged in redirect him to LoginActivity Activity
            Intent i = new Intent(context, LoginActivity.class);
            // Closing all the Activities
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            // Staring LoginActivity Activity
            context.startActivity(i);
        }

    }

    public void Logout( ){
        //prefs.edit().clear().apply();

        SharedPreferences.Editor edits = prefs.edit();

        edits.clear(); //comente esto

//        edits.remove(IS_LOGIN).apply();

        edits.remove(MYConstantsPreferences.MAQUIPURAY_LOGIN).apply();

//
//        Intent i = new Intent(context, LoginActivity.class);
//        // Closing all the Activities
//        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//
//        // Add new Flag to start new Activity
//        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//
//        // Staring LoginActivity Activity
//        context.startActivity(i);

    }

    public void GuardarNombre(String nombre){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(MYConstantsPreferences.MAQUIPURAY_USERNAME, nombre);
        edits.apply();
    }
    public String ObtenerNombre(){
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_USERNAME, null);
    }

    public void GuardarApellido(String apellido){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(MYConstantsPreferences.MAQUIPURAY_USERLASTNAME, apellido);
        edits.apply();
    }
    public String ObtenerApellido(){
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_USERLASTNAME, null);
    }

    public void GuardarCorreo(String correo){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(MYConstantsPreferences.MAQUIPURAY_EMAL, correo);
        edits.apply();
    }
    public String ObtenerCorreo(){
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_EMAL, null);
    }

    public void GuardarTelefono(String telefono){
        SharedPreferences.Editor edits = prefs.edit();
        edits.putString(MYConstantsPreferences.MAQUIPURAY_USERTEL, telefono);
        edits.apply();
    }
    public String ObtenerTelefono(){
        return prefs.getString(MYConstantsPreferences.MAQUIPURAY_USERTEL, null);
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
