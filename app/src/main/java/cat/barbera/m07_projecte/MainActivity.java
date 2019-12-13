package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String sharedPrefFile = "cat.barbera.m07_projecte";;
    private SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




    }
}
