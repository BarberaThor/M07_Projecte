package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegistreActivity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;
    EditText medtUsuari;
    EditText medtContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        medtUsuari = findViewById(R.id.edtUsuari);
        medtContra = findViewById(R.id.edtContra);

    }


    public void registrarUsuari(View view) {

        String a, b;
        a = medtUsuari.getText().toString();
        b = medtContra.getText().toString();

        System.out.println("hola");

        if(!a.equalsIgnoreCase("")|| !b.equalsIgnoreCase("")) {

            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("Usuari", medtUsuari.getText().toString());
            myEdit.putString("Contrasenya", medtContra.getText().toString());
            myEdit.apply();

            finishActivity(1);



        }else {

            Toast.makeText(this, "Els camps no poden restar en blanc", Toast.LENGTH_SHORT).show();
        }
        Intent intent2 = new Intent(this, MainActivity.class);
        startActivityForResult(intent2, TEXT_REQUEST);
    }
}


