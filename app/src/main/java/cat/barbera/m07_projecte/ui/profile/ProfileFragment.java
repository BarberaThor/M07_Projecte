package cat.barbera.m07_projecte.ui.profile;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import cat.barbera.m07_projecte.R;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private Context context;

    private RadioButton mAngles, mCatala, mCastella;
    private RadioGroup mGroupRdb;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        //final TextView textView = root.findViewById(R.id.text_profile);

        // Perfil Usuari


        return root;
    }

    private void handle_Guardar(View view) {
        //Toast.makeText(this, "Les dades s'han actualitzat correctament", Toast.LENGTH_SHORT).show();

    }
}