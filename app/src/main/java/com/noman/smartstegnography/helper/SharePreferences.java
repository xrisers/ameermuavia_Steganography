package com.noman.smartstegnography.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static com.noman.smartstegnography.helper.Constants.MYPREFERENCES;
import static com.noman.smartstegnography.helper.Constants.PREFERENCES_ISLOGIN;
import static com.noman.smartstegnography.helper.Constants.PREFERENCES_USEREMAIL;


public class SharePreferences {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharePreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(MYPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.apply();
    }
    public void setLogin(boolean b){
        editor = sharedPreferences.edit();
        editor.putBoolean(PREFERENCES_ISLOGIN, b);
        editor.apply();
    }
    public void setUserEmail(String email){
        editor = sharedPreferences.edit();
        editor.putString(PREFERENCES_USEREMAIL, email);
        editor.apply();
    }
    public String getUserEmail(){
        if(sharedPreferences.contains(PREFERENCES_USEREMAIL)){
            return sharedPreferences.getString(PREFERENCES_USEREMAIL, "");
        }
        return null;
    }
    public boolean checkLogin(){
        if(sharedPreferences.contains(PREFERENCES_ISLOGIN)){
            return sharedPreferences.getBoolean(PREFERENCES_ISLOGIN, false);
        }
        return false;
    }

}
