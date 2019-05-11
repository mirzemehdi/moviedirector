package SharedPreferences;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;

public class Prefs {

    SharedPreferences sharedPreferences;
    public Prefs(Activity activity){

        sharedPreferences=activity.getPreferences(Context.MODE_PRIVATE);

    }

    public void setPrefs(String search){
        sharedPreferences.edit().putString("search",search).commit();


    }

    public String getPrefs(){

        return sharedPreferences.getString("search","batman");
    }



}
