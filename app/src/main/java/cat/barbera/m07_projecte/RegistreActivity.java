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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class RegistreActivity extends AppCompatActivity {

    private static final int TEXT_REQUEST = 1;

    EditText medtUsuari;
    EditText medtContra;

    private String email = " ";
    private String password = " ";

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registre);

        medtUsuari = findViewById(R.id.edtUsuari);
        medtContra = findViewById(R.id.edtContra);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    public void registrarUsuari(View view) {

        email = medtUsuari.getText().toString();
        password = medtContra.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()) {

            if (password.length() >= 6) {
                registerUsuari();
            } else {
                Toast.makeText(RegistreActivity.this, "La contrasenya ha de tenir com a minim 6 digits", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(RegistreActivity.this, "Ha de completar els camps", Toast.LENGTH_SHORT).show();
        }

            /*mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task    ) {
                        if (task.isSuccessful()) {
                            Map<String,Object> map = new HashMap<>();
                            map.put("email", email);
                            map.put("password", password);

                            String id = mAuth.getCurrentUser().getUid();

                            mDatabase.child("Users").child(id).setValue(map);
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
                });*/
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

    private void registerUsuari() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Map<String,Object> map = new HashMap<>();
                    map.put("email", email);
                    map.put("password", password);

                    String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(RegistreActivity.this, MainActivity.class));
                                finish();
                            } else {
                                Toast.makeText(RegistreActivity.this, "No s'ha pogut crear les dades", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(RegistreActivity.this, "No s'ha pogut registrar", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /*private void updateUI(FirebaseUser user) {

        Intent intent2 = new Intent(this, MainActivity.class);
        startActivityForResult(intent2, TEXT_REQUEST);

    }*/

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


