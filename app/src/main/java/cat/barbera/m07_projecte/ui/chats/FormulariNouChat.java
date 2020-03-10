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

    public static final String EXTRA_MESSAGE_PARTICIPANTES = "cat.copernic.luna.dianaranking.extra.NOU_CHAT";

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

                Intent intent = new Intent(getApplicationContext(),LlistaChats.class);
                intent.putExtra("contacto", contacte);
                intent.putExtra("fecha", fecha);
                startActivity(intent);
            }
        });
    }


}
