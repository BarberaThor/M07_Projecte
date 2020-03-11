package cat.barbera.m07_projecte.ui.chats;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import cat.barbera.m07_projecte.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class ChatFragment extends AppCompatActivity {

    private Context context;

    private ChatViewModel chatViewModel;

    private CircleImageView fotoPerfilCV;
    private TextView nombre;
    private RecyclerView recyclerView;
    private EditText edMissatge;
    private Button btnEnviar;
    private ImageButton imgButton;

    private String email;

    private FirebaseAuth mAuth;
    private String id;

    private AdapterMessage adapter;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private FirebaseStorage storage;
    private StorageReference storageReference   ;

    private static final int PHOTO_SEND = 1;
    private static final int PHOTO_PERFIL = 2;

    private String fotoPerfilCadena;

    private String contactoAChatear;

    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.fragment_chat);

        contactoAChatear = getIntent().getStringExtra("contactoAChatear");

         context = this;
         nombre = (TextView) findViewById(R.id.nombre);
         recyclerView = (RecyclerView) findViewById(R.id.rvMissatges);
         edMissatge = (EditText) findViewById(R.id.ed_missatge);
         btnEnviar = (Button) findViewById(R.id.btn_enviar);
         fotoPerfilCV = (CircleImageView) findViewById(R.id.fotoPerfil);
         imgButton = (ImageButton) findViewById(R.id.img_button);
         fotoPerfilCadena = "";

        database = FirebaseDatabase.getInstance();

        mAuth = FirebaseAuth.getInstance();

        id = mAuth.getCurrentUser().getUid();

        DatabaseReference myRefUser = database.getReference("User/"+ id);
        myRefUser.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    HashMap map =  (HashMap)dataSnapshot1.getValue();

                    email = map.get("email").toString();
                    nombre.setText(email);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



         myRef = database.getReference("Chat" + contactoAChatear); //Sala de chat (nombre)

         storage = FirebaseStorage.getInstance();

         adapter = new AdapterMessage(this);

         LinearLayoutManager ly = new LinearLayoutManager(this);
         recyclerView.setLayoutManager(ly);
         recyclerView.setAdapter(adapter);

         btnEnviar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                /*adapter.addMissatge(new Missatge(edMissatge.getText().toString(), nombre.getText().toString(), "00:00", "1", ""));
                edMissatge.setText(" ");*/

                myRef.push().setValue(new MissatgeEnviar(edMissatge.getText().toString(), nombre.getText().toString(), "1", fotoPerfilCadena, ServerValue.TIMESTAMP));
                edMissatge.setText("");
             }
         });

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Escull una imatge"), PHOTO_SEND);
            }
        });

        fotoPerfilCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/jpeg");
                i.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
                startActivityForResult(Intent.createChooser(i, "Escull una imatge"), PHOTO_PERFIL);
            }
        });

         adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
             @Override
             public void onItemRangeInserted(int positionStart, int itemCount) {
                 super.onItemRangeInserted(positionStart, itemCount);
                 setScrollbar();
             }
         });

         myRef.addChildEventListener(new ChildEventListener() {
             @Override
             public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                 MissatgeRebut m = dataSnapshot.getValue(MissatgeRebut.class);
                 adapter.addMissatge(m);

             }

             @Override
             public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

             }

             @Override
             public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

             }

             @Override
             public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {

             }
         });

    }

    private void setScrollbar() {
        recyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PHOTO_SEND && resultCode == RESULT_OK) {

            Uri u = data.getData();

            storageReference = storage.getReference("imagenes_chat");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getUploadSessionUri();
                    MissatgeEnviar m = new MissatgeEnviar("Xavi t'ha enviat una foto", u.toString(), nombre.getText().toString(), "2", fotoPerfilCadena, ServerValue.TIMESTAMP);
                    myRef.push().setValue(m);
                }
            });


        } else if (requestCode == PHOTO_PERFIL && resultCode == RESULT_OK) {

            Uri u = data.getData();

            storageReference = storage.getReference("foto_perfil");
            final StorageReference fotoReferencia = storageReference.child(u.getLastPathSegment());
            fotoReferencia.putFile(u).addOnSuccessListener(this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Uri u = taskSnapshot.getUploadSessionUri();
                    fotoPerfilCadena = u.toString();
                    MissatgeEnviar m = new MissatgeEnviar("Xavi ha actualitzat el seu perfil", u.toString(), nombre.getText().toString(), "2", fotoPerfilCadena, ServerValue.TIMESTAMP);
                    myRef.push().setValue(m);
                    Glide.with(ChatFragment.this).load(u.toString()).into(fotoPerfilCV);
                }
            });


        }
    }

}