package cat.barbera.m07_projecte.ui.chats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cat.barbera.m07_projecte.R;

public class AdapterMessage extends RecyclerView.Adapter<HolderMessage> {

    private List<MissatgeRebut> llistaMissatges = new ArrayList<>();
    private Context c;

    public AdapterMessage( Context c) {
        this.c = c;
    }

    public void addMissatge(MissatgeRebut mMissatge) {
        llistaMissatges.add(mMissatge);
        notifyItemInserted(llistaMissatges.size());
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.card_message, parent, false);
        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        holder.getNombre().setText(llistaMissatges.get(position).getNom());
        holder.getMensaje().setText(llistaMissatges.get(position).getMisstage());
        if(llistaMissatges.get(position).getType_message().equals("2")) {
            holder.getImgMissatge().setVisibility(View.VISIBLE);
            holder.getMensaje().setVisibility(View.VISIBLE);
            Glide.with(c).load(llistaMissatges.get(position).getUrlFoto()).into(holder.getFotoMensaje());
        } else if (llistaMissatges.get(position).getType_message().equals("1")) {
            holder.getImgMissatge().setVisibility(View.GONE);
            holder.getMensaje().setVisibility(View.VISIBLE);
        }

        if (llistaMissatges.get(position).getFotoPerfil().isEmpty()) {
                holder.getFotoMensaje().setImageResource(R.mipmap.ic_launcher);
        } else {
            Glide.with(c).load(llistaMissatges.get(position).getFotoPerfil()).into(holder.getFotoMensaje());
        }

        Long codigoHora = llistaMissatges.get(position).getHora();
        Date d = new Date(codigoHora);
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
        holder.getHora().setText(sdf.format(d));

    }

    @Override
    public int getItemCount() {
        return llistaMissatges.size();
    }
}
