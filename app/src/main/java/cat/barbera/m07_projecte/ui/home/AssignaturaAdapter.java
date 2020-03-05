package cat.barbera.m07_projecte.ui.home;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import cat.barbera.m07_projecte.DetailActivity;
import cat.barbera.m07_projecte.R;

public class AssignaturaAdapter extends RecyclerView.Adapter<AssignaturaAdapter.ViewHolder>  {

        // Member variables.
        private ArrayList<Assignatura> mAssignaturaData;
        private Context mContext;

        AssignaturaAdapter(Context context, ArrayList<Assignatura> assignaturaData) {
            this.mAssignaturaData = assignaturaData;
            this.mContext = context;
        }

        @Override
        public AssignaturaAdapter.ViewHolder onCreateViewHolder(
                ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(mContext).
                    inflate(R.layout.list_item, parent, false));
        }


        @Override
        public void onBindViewHolder(AssignaturaAdapter.ViewHolder holder,
                                     int position) {
            Assignatura currentAssignatura = mAssignaturaData.get(position);

            holder.bindTo(currentAssignatura);
        }

        @Override
        public int getItemCount() {
            return mAssignaturaData.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

            private TextView mTitleText;
            private TextView mInfoText;
            private ImageView mAssignaturaImage;


            ViewHolder(View itemView) {
                super(itemView);

                // Initialize the views.
                mTitleText = itemView.findViewById(R.id.title);
                //mInfoText = itemView.findViewById(R.id.subTitle);
                //mAssignaturaImage = itemView.findViewById(R.id.assignaturaImage);

                // Set the OnClickListener to the entire view.
                itemView.setOnClickListener(this);
            }

            void bindTo(Assignatura currentAssignatura){
                mTitleText.setText(currentAssignatura.getTitle());
                mInfoText.setText(currentAssignatura.getInfo());
                Glide.with(mContext).load(currentAssignatura.getImageResource()).into(mAssignaturaImage);
            }

            @Override
            public void onClick(View view) {

                Assignatura currentAssignatura = mAssignaturaData.get(getAdapterPosition());

                Intent detailIntent = new Intent(mContext, DetailActivity.class);
                detailIntent.putExtra("title", currentAssignatura.getTitle());
                detailIntent.putExtra("image_resource", currentAssignatura.getImageResource());
                mContext.startActivity(detailIntent);
            }
        }
    }




