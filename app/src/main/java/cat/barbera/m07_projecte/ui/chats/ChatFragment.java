package cat.barbera.m07_projecte.ui.chats;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

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

    private AdapterMessage adapter;



    protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);

         setContentView(R.layout.fragment_chat);

         context = this;
         nombre = (TextView) findViewById(R.id.nombre);
         recyclerView = (RecyclerView) findViewById(R.id.rvMissatges);
         edMissatge = (EditText) findViewById(R.id.ed_missatge);
         btnEnviar = (Button) findViewById(R.id.btn_enviar);
         fotoPerfilCV = (CircleImageView) findViewById(R.id.fotoPerfil);

         adapter = new AdapterMessage(context);

         LinearLayoutManager ly = new LinearLayoutManager(context);
         recyclerView.setLayoutManager(ly);
         recyclerView.setAdapter(adapter);

         btnEnviar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                adapter.addMissatge(new Missatge(edMissatge.getText().toString(), nombre.getText().toString(), "00:00", "1", ""));
             }
         });

    }

}