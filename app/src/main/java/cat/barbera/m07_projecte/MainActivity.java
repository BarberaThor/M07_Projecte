package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;


    private EditText mUsuari, mContrasenya;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public static final int TEXT_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsuari = findViewById(R.id.edUsuari);
        mContrasenya = findViewById(R.id.edContrasenya);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        
    }

    private void updateUI(FirebaseUser currentUser) {
        Intent intent = new Intent(this, SecondActivity.class);

        startActivityForResult(intent, TEXT_REQUEST);
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

        String email = mUsuari.getText().toString();
        String password = mContrasenya.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("tag", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("tag", "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });



        /*
        SharedPreferences mPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);

        String usuarish = mPreferences.getString("Usuari", "");
        String contrash = mPreferences.getString("Contrasenya", "");

        final String usuarilog = mUsuari.getText().toString();
        final String contralog = mContrasenya.getText().toString();


        if (usuarilog.equalsIgnoreCase(usuarish) && contralog.equalsIgnoreCase(contrash)) {



        } else {

            Toast.makeText(this, "Les dades no són correctes", Toast.LENGTH_SHORT).show();
        }

         */
    }
}
