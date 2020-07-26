package com.baru.projectkelas.db;

import android.content.Context;
import android.content.SharedPreferences;

public class Preference {

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static String KEY_EMAIL ="key_email";
    private static String KEY_PASSWORD ="key_password";
    private static String KEY_ID = "key_id";
    private static String KEY_NAME = "key_name";

    public static void setContext(Context context){

        sharedPreferences = context.getSharedPreferences("LOGIN", Context.MODE_PRIVATE);

    }

    public static void actionLogin(String email, String password, int idMember, String name){
        editor =sharedPreferences.edit();               //ini ngebuka file / agar bisa mengedit file penyimpanan
        editor.putString(KEY_EMAIL, email);             // ini menyimpan / update file nya
        editor.putString(KEY_PASSWORD, password);
        editor.putInt(KEY_ID, idMember);
        editor.putString(KEY_NAME, name);
        editor.apply();                                 //ketika sudah selesai, maka ini digunakan untuk save perubahan yang tadi

    }

    public static String getEmail(){
        return sharedPreferences.getString(KEY_EMAIL, null);
    }

    public static String getPassword(){
        return sharedPreferences.getString(KEY_PASSWORD, null);
    }

    public static String getName(){
        return sharedPreferences.getString(KEY_NAME, null);
    }

    public static int getIdMeber(){
        return sharedPreferences.getInt(KEY_ID, 0);
    }

    public static void actionLogout(){
        editor = sharedPreferences.edit();
        editor.clear().apply();
    }

}
