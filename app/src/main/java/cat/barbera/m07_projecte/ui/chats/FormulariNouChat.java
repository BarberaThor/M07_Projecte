package cat.barbera.m07_projecte.ui.chats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import cat.barbera.m07_projecte.R;

public class FormulariNouChat extends AppCompatActivity {

    public static final String TEXTO = "texto";
    public static final String AUDIO = "audio";
    public static final String IMAGEN = "imagen";
    public static final String VIDEO = "video";

    private EditText ed_contacte;
    private EditText ed_fecha;
    private Button mButtonCrearChat;

    private String contacte;
    private String fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulari_nou_chat);

        ed_contacte = findViewById(R.id.ed_contacte);
        ed_fecha = findViewById(R.id.ed_fecha);
        mButtonCrearChat = findViewById(R.id.btn_crearChat);
        mButtonCrearChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contacte = ed_contacte.getText().toString();
                fecha = ed_fecha.getText().toString();


                ArrayList<ObjetoListView> arrayListNewChat = new ArrayList<>();

                arrayListNewChat.add(new ObjetoListView(contacte, "",
                        fecha, "", false, 0, "", R.drawable.playstore));

                Intent intent = new Intent(getApplicationContext(),LlistaChats.class);
                startActivity(intent);
            }
        });
    }


}
