package cat.barbera.m07_projecte.ui.tools;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cat.barbera.m07_projecte.R;

public class ToolsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tools);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.contenedorFragment, new ToolsFragment())
                .commit();
    }

    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btnIconoAtras:
                finish();
                break;
        }
    }
}
