package cat.barbera.m07_projecte;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Objects;
import java.util.Random;

import static cat.barbera.m07_projecte.MainActivity.varus;

public class FormulariActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText mTitol, mContingut;
    private Spinner sp;

    private FirebaseDatabase database;
    private FirebaseAuth mAuth;

    private  long contador;
    private String post;

    private DatabaseReference ref2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari);

        mTitol = findViewById(R.id.edtTitol);
        mContingut = findViewById(R.id.edtContingut);
        sp = findViewById(R.id.sp);

        database = FirebaseDatabase.getInstance();


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.titol_assignatura, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void publicarPost(View view) {


        ref2 = database.getReference("Contador");

        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                contador = dataSnapshot.getValue(Long.class);

                //post = "Post" + (contador+1);
                post = "Post3";
                DatabaseReference myRefName = database.getReference("Posts/" + post);


                final String A = mTitol.getText().toString();
                final String B = mContingut.getText().toString();
                final String C = sp.getSelectedItem().toString();
                final String D = varus;
                myRefName.child("Titol").setValue(A);
                myRefName.child("Contingut").setValue(B);
                myRefName.child("Assignatura").setValue(C);
                myRefName.child("Autor").setValue(D);


                ref2.setValue(contador+1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}