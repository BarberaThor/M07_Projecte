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

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For night mode theme
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES); //For day mode theme
        setContentView(R.layout.activity_registre);
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void registrarUsuari(View view) {

        String email, pass;
        email = medtUsuari.getText().toString();
        pass = medtContra.getText().toString();

        mAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = mAuth.getCurrentUser();
                    updateUI(user);
                }else{
                    Log.e("taag", "Sign-in Failed: " + task.getException().getMessage());
                    // Or if you don't use Log:
                     System.out.println("Sign-in Failed: " + task.getException().getMessage());
                    Toast.makeText(RegistreActivity.this,"Erorr Login",Toast.LENGTH_LONG).show();
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



}


