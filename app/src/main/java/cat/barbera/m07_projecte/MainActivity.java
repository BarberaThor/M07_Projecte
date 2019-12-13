package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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

        SharedPreferences mPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String u = mPreferences.getString("usuari", "");
        String c = mPreferences.getString("contrasenya", "");

        mUsuari.setText(u);
        mContrasenya.setText(c);
    }

    @Override
    protected void onPause()
    {
        super.onPause();

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("usuari", mUsuari.getText().toString());
        myEdit.putString("age", mContrasenya.getText().toString());
        myEdit.commit();


    }


    public void ferLogin(View view) {

        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);

        startActivityForResult(intent, TEXT_REQUEST);
    }

    public void ferRegistre(View view) {

        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, RegistreActivity.class);

        startActivityForResult(intent, TEXT_REQUEST);
    }
}
