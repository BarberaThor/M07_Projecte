package cat.barbera.m07_projecte.ui.chats;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import cat.barbera.m07_projecte.R;

public class LlistaChats extends AppCompatActivity {

    private ListView listView;
    private CustomAdapter customAdapter;

    public static final String TEXTO = "texto";
    public static final String AUDIO = "audio";
    public static final String IMAGEN = "imagen";
    public static final String VIDEO = "video";

    private String contactoRecibido;
    private String fechaRecibida;

    private String email;

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_llista_chats);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        /*id = mAuth.getCurrentUser().getUid();

        DatabaseReference myRef = database.getReference("User/"+ id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    HashMap map =  (HashMap)dataSnapshot1.getValue();

                    email = map.get("email").toString();

                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

        email = currentUser.getEmail();

        Intent intent = getIntent();

        contactoRecibido = getIntent().getStringExtra("contacto");
        fechaRecibida = getIntent().getStringExtra("fecha");

        
        init();

        this.listView.setNestedScrollingEnabled(true);
        this.listView.setAdapter(customAdapter);

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),ChatFragment.class);
                intent.putExtra("contactoAChatear", contactoRecibido);
                startActivity(intent);
            }
        });
    }

    private void init() {
        this.listView = findViewById(R.id.listView_chats);
        this.customAdapter = new CustomAdapter(this, getArrayList());
    }

    private String getEmoji(int unicode) {
        return new String(Character.toChars(unicode));
    }

    private ArrayList<ObjetoListView> getArrayList() {
        ArrayList<ObjetoListView> arrayList = new ArrayList<>();

        arrayList.add(new ObjetoListView("Administrador" + getEmoji(0x1F601), email +"; " + getEmoji(0x1F64C),
                "10/3/2020", TEXTO, false, 3, "", R.drawable.playstore));

        arrayList.add(new ObjetoListView(contactoRecibido, email + "; " ,
                fechaRecibida, TEXTO, false, 0, "", R.drawable.playstore));

        return arrayList;
    }

    public void crearChat(View view) {
        Intent intent = new Intent(getApplicationContext(),FormulariNouChat.class);
        startActivity(intent);

    }
}
