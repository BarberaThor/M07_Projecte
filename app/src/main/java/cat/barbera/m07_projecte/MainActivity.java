package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mUsuari, mContrasenya;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;


    //private String sharedPrefFile = "cat.barbera.m07_projecte";;
    //private SharedPreferences mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsuari = findViewById(R.id.edUsuari);
        mContrasenya = findViewById(R.id.edContrasenya);

    }

    @Override
    protected void onResume()
    {
        super.onResume();


    }

    @Override
    protected void onPause()
    {
        super.onPause();

    }

    public void ferRegistre(View view) {

        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, RegistreActivity.class);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void ferLogin(View view) {

        SharedPreferences mPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String usuarish = mPreferences.getString("Usuari", "");
        String contrash = mPreferences.getString("Contrasenya", "");

        final String usuarilog = mUsuari.getText().toString();
        final String contralog = mContrasenya.getText().toString();


        if (usuarilog.equalsIgnoreCase(usuarish) && contralog.equalsIgnoreCase(contrash)) {

            Intent intent = new Intent(this, SecondActivity.class);

            startActivityForResult(intent, TEXT_REQUEST);

        } else {

            Toast.makeText(this, "Les dades no són correctes", Toast.LENGTH_SHORT).show();
        }
    }
}
