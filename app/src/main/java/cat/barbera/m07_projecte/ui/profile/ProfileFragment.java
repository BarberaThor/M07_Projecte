package cat.barbera.m07_projecte.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import cat.barbera.m07_projecte.NavigationMenu;
import cat.barbera.m07_projecte.R;
import cat.barbera.m07_projecte.ui.home.HomeFragment;

import static cat.barbera.m07_projecte.MainActivity.TEXT_REQUEST;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Context context;
    private String emailPerfil;
    private String passwordPerfil;

    private TextView txt_usuario;
    private TextView txt_psw;

    private FirebaseAuth mAuth;
    private String id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mAuth = FirebaseAuth.getInstance();


        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        //final TextView textView = root.findViewById(R.id.text_profile);

        txt_usuario = root.findViewById(R.id.txt_usuarioSalida);
        txt_psw = root.findViewById(R.id.txt_contraSalida);


                id = mAuth.getCurrentUser().getUid();

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference("User/"+ id);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    HashMap map =  (HashMap)dataSnapshot1.getValue();

                    emailPerfil = map.get("email").toString();
                    passwordPerfil = map.get("password").toString();

                    txt_usuario.setText(emailPerfil);
                    txt_psw.setText(passwordPerfil);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        return root;
    }

}