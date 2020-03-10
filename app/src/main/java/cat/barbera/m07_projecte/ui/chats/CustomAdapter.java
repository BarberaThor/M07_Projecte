package cat.barbera.m07_projecte.ui.chats;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import cat.barbera.m07_projecte.R;

public class CustomAdapter extends ArrayAdapter<ObjetoListView> {

    private Activity activity;
    private ArrayList<ObjetoListView> arrayList;

    public CustomAdapter( Activity activity, ArrayList<ObjetoListView> arrayList) {
        super(activity, R.layout.custom_layout, arrayList);
        this.activity = activity;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null) {
            convertView =
                    LayoutInflater.from(getContext())
                            .inflate(R.layout.custom_layout, parent, false);
        }

        TextView txtNombre = convertView.findViewById(R.id.txt_NombreListView);
        TextView txtMensaje = convertView.findViewById(R.id.txt_mensaje);
        TextView txtFecha = convertView.findViewById(R.id.txt_FechaListView);
        TextView txtBadge = convertView.findViewById(R.id.badge_notification);
        TextView txtSmallIcon = convertView.findViewById(R.id.txtSmallIcon);


        ImageView imgAudio = convertView.findViewById(R.id.iconMic);
        ImageView imgVideo = convertView.findViewById(R.id.iconVideo);
        ImageView imgFoto = convertView.findViewById(R.id.iconPhoto);

        ImageView imgP = convertView.findViewById(R.id.img_profile_listView);
        imgP.setImageDrawable(activity.getResources().getDrawable(
                arrayList.get(position).getImg()
        ));

        txtNombre.setText(arrayList.get(position).getNombre());
        txtMensaje.setText(arrayList.get(position).getMensaje());
        txtFecha.setText(arrayList.get(position).getFecha());

        String tipoMensaje = arrayList.get(position).getTipoUltimoMensaje();

        switch (tipoMensaje) {
            case LlistaChats.TEXTO:
                txtMensaje.setVisibility(View.VISIBLE);
                imgAudio.setVisibility(View.GONE);
                imgFoto.setVisibility(View.GONE);
                imgVideo.setVisibility(View.GONE);
                txtSmallIcon.setVisibility(View.GONE);
                break;
            case LlistaChats.AUDIO:
                txtMensaje.setVisibility(View.GONE);
                imgAudio.setVisibility(View.VISIBLE);
                txtSmallIcon.setVisibility(View.VISIBLE);
                txtSmallIcon.setText(arrayList.get(position).getTxtSmallIcon());
                break;
            case LlistaChats.IMAGEN:
                txtMensaje.setVisibility(View.GONE);
                imgFoto.setVisibility(View.VISIBLE);
                txtSmallIcon.setVisibility(View.VISIBLE);
                txtSmallIcon.setText(arrayList.get(position).getTxtSmallIcon());
                break;
            case LlistaChats.VIDEO:
                txtMensaje.setVisibility(View.GONE);
                imgVideo.setVisibility(View.VISIBLE);
                txtSmallIcon.setVisibility(View.VISIBLE);
                txtSmallIcon.setText(arrayList.get(position).getTxtSmallIcon());
                break;
        }

        if(!arrayList.get(position).isMensajeLeido()){
            txtFecha.setTextColor(activity.getResources().getColor(R.color.color_notification));
            txtBadge.setVisibility(View.VISIBLE);
            txtBadge.setText(String.valueOf(arrayList.get(position).getNrMensajesNoLeidos()));
        } else {
            txtFecha.setTextColor(Color.GRAY);
            txtBadge.setVisibility(View.GONE);
        }




        return convertView;
    }
}
