package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class PerfilActivity extends AppCompatActivity {

    private RadioButton mAngles, mCatala, mCastella;
    private RadioGroup mGroupRdb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
    }

    private void handle_Guardar(View view) {
        Toast.makeText(this, "Les dades s'han actualitzat correctament", Toast.LENGTH_SHORT).show();

    }
}
