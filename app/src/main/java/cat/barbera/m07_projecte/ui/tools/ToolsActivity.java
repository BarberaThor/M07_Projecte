package cat.barbera.m07_projecte.ui.tools;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import java.util.HashSet;
import java.util.Set;

import cat.barbera.m07_projecte.R;

public class ToolsActivity extends AppCompatActivity {

    public static final String TAG = "App";
    private static boolean isNightModeEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragment, new ToolsFragment())
                .commit();

        //SharedPreference
        SharedPreferences sPref = PreferenceManager.getDefaultSharedPreferences(this);

        //CheckBox
        this.isNightModeEnabled = sPref.getBoolean("setting_nightmode", false);

        if (this.isNightModeEnabled == true) {
            if (ToolsActivity.isNightModeEnabled()) {
                setTheme(R.style.FeedActivityThemeDark);
            }

            Toast.makeText (getApplicationContext (), "Mode nit habilitat" , Toast.LENGTH_SHORT) .show ();

        } else {
            //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            //recreate();  //Modo claro

            Toast.makeText (getApplicationContext (), "Mode nit deshabilitat" , Toast.LENGTH_SHORT) .show ();
        }

        //ListPreference
        String servidor = sPref.getString("listPref", "-1");

        Set<String> categoria = new HashSet<String>();

        categoria = sPref.getStringSet("multiListPref", null);

        if(categoria != null ){
            for(String e: categoria){
                Log.d("categoria", e.toString());
            }
        }
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnIconoAtras:
                finish();
                break;
        }
    }

    public static boolean isNightModeEnabled() {
        return isNightModeEnabled;
    }

    public void setIsNightModeEnabled(boolean isNightModeEnabled) {
        this.isNightModeEnabled = isNightModeEnabled;
    }
}
