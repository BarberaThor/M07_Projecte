package cat.barbera.m07_projecte.ui.chats;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cat.barbera.m07_projecte.R;
import de.hdodenhof.circleimageview.CircleImageView;

public class HolderMessage extends RecyclerView.ViewHolder {

    private TextView nombre;
    private TextView mensaje;
    private TextView hora;
    private CircleImageView fotoMensaje;

    public HolderMessage(@NonNull View itemView) {
        super(itemView);
        nombre = (TextView) itemView.findViewById(R.id.nombreMissatge);
        mensaje = (TextView) itemView.findViewById(R.id.missatgeMissatge);
        hora = (TextView) itemView.findViewById(R.id.hora);
        fotoMensaje = (CircleImageView) itemView.findViewById(R.id.fotoPerfilMissatge);
    }

    public TextView getNombre() {
        return nombre;
    }

    public void setNombre(TextView nombre) {
        this.nombre = nombre;
    }

    public TextView getMensaje() {
        return mensaje;
    }

    public void setMensaje(TextView mensaje) {
        this.mensaje = mensaje;
    }

    public TextView getHora() {
        return hora;
    }

    public void setHora(TextView hora) {
        this.hora = hora;
    }

    public CircleImageView getFotoMensaje() {
        return fotoMensaje;
    }

    public void setFotoMensaje(CircleImageView fotoMensaje) {
        this.fotoMensaje = fotoMensaje;
    }
}
