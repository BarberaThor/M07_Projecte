package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegistreActivity extends AppCompatActivity {

    EditText edtUsuari;
    EditText edtContra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

    }


    public void registrarUsuari(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("Usuari", edtUsuari.getText().toString());
        myEdit.putString("Contrasenya", edtContra.getText().toString());
        myEdit.commit();

        finishActivity(1);
    }
}


