package cat.barbera.m07_projecte.ui.chats;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.barbera.m07_projecte.R;

public class AdapterMessage extends RecyclerView.Adapter<HolderMessage> {

    private List<Missatge> llistaMissatges = new ArrayList<>();
    private Context c;

    public AdapterMessage( Context c) {
        this.c = c;
    }

    public void addMissatge(Missatge mMissatge) {
        llistaMissatges.add(mMissatge);
        notifyItemInserted(llistaMissatges.size());
    }

    @NonNull
    @Override
    public HolderMessage onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.list_item, parent, false);
        return new HolderMessage(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderMessage holder, int position) {
        holder.getNombre().setText(llistaMissatges.get(position).getNom());
        holder.getMensaje().setText(llistaMissatges.get(position).getMisstage());
        holder.getHora().setText(llistaMissatges.get(position).getHora());



    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
