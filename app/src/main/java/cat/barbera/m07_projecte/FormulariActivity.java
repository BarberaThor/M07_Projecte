package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FormulariActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText mTitol, mContingut;


    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari);

        mTitol = findViewById(R.id.edtTitol);
        mContingut = findViewById(R.id.edtContingut);

        Spinner sp = findViewById(R.id.sp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.titol_assignatura, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);




    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void publicarPost(View view) {

        /*database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        String a, b;


        //database.push().getKey();

        a = mTitol.getText().toString();
        b = mContingut.getText().toString();

        //myRef.child("Titol").setValue(a);
        //myRef.child("Contingut").setValue(b);

        myRef.child("jan").setValue("hola");*/

        String post = "Post3";

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRefName = database.getReference("Posts/" + post);

        myRefName.child("Assignatura").setValue("Mates");
        myRefName.child("Autor").setValue("Xavi");
        myRefName.child("Contingut").setValue("Apunts");
        myRefName.child("Titol").setValue("ApuntsMates");

        myRefName.addValueEventListener(new ValueEventListener() {
            private static final String TAG = "S";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //int value = Integer.parseInt(dataSnapshot.getValue(String.class));
                //myRefName.setValue(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }
}
