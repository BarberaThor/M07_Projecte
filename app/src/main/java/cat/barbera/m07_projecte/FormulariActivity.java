package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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

        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        String a, b;


        //database.push().getKey();

        a = mTitol.getText().toString();
        b = mContingut.getText().toString();

        //myRef.child("Titol").setValue(a);
        //myRef.child("Contingut").setValue(b);

        myRef.child("jan").setValue("hola");


    }
}
