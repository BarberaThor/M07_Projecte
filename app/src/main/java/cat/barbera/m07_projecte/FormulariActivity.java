package cat.barbera.m07_projecte;

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

public class FormulariActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText mTitol, mContingut;
    private Spinner sp;


    FirebaseDatabase database;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari);

        mTitol = findViewById(R.id.edtTitol);
        mContingut = findViewById(R.id.edtContingut);


        sp = findViewById(R.id.sp);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.titol_assignatura, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);


        Log.d("test123", "Value is: ");
        System.out.println("abcd");
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

        String a, b, c, d;

        a = mTitol.getText().toString();
        b = mContingut.getText().toString();
        c = sp.getSelectedItem().toString();
        //d = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
        d = "hola";
        int random = new Random().nextInt(100) + 20;

        String ax = String.valueOf(random);
        String post = "post4";

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRefName = database.getReference("Posts/" + post);


        myRefName.child("Titol").setValue(a);
        myRefName.child("Contingut").setValue(b);
        myRefName.child("Assignatura").setValue(c);
        myRefName.child("Autor").setValue(d);

    }
}
