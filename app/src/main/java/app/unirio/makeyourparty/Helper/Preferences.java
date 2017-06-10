package app.unirio.makeyourparty.Helper;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Jow on 09/06/2017.
 */

/**
 * Classe responsavel por verificar se já há algum usuário logado e mantê-lo logado.
 */
public class Preferences {

    private Context context;
    private SharedPreferences preferences;
    private String FILE_NAME = "MakeYourParty.preferences";
    private int MODE = 0;
    private SharedPreferences.Editor editor;

    private final String ID_KEY = "Idetify user logged in";
    private final String NAME_KEY = "User logged name";

    public Preferences(Context context){
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveUserPreferences(String UserId, String UserName){
        editor.putString(ID_KEY, UserId);
        editor.putString(NAME_KEY, UserName);
        editor.commit();
    }

    public String getIdentifier(){
        return preferences.getString(ID_KEY, null);
    }

    public String getName(){
        return preferences.getString(NAME_KEY, null);
    }
}
