package cat.barbera.m07_projecte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.HashMap;
import java.util.Map;

public class RegistreActivity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;
    EditText medtUsuari;
    EditText medtContra;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        medtUsuari = findViewById(R.id.edtUsuari);
        medtContra = findViewById(R.id.edtContra);

        mAuth = FirebaseAuth.getInstance();

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For night mode theme
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For day mode theme
        //setContentView(R.layout.activity_registre);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void registrarUsuari(View view) {

        String email = medtUsuari.getText().toString();
        String password = medtContra.getText().toString();
            mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task    ) {
                        if (task.isSuccessful()) {
                            
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("taag", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("taag", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        /*
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
         */
    }

    private void updateUI(FirebaseUser user) {

        Intent intent2 = new Intent(this, MainActivity.class);
        startActivityForResult(intent2, TEXT_REQUEST);

    }

/*
    public void canviarMode(View view) {

        int nightMode = AppCompatDelegate.getDefaultNightMode();

        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode
                    (AppCompatDelegate.MODE_NIGHT_YES);

            }

            recreate();
        }
*/


}


