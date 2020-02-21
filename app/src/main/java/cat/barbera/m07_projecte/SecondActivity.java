package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;

import cat.barbera.m07_projecte.ui.home.Assignatura;
import cat.barbera.m07_projecte.ui.home.AssignaturaAdapter;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Assignatura> mAssignaturaData;
    private AssignaturaAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize the ArrayList that will contain the data.
        mAssignaturaData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        //mAdapter = new AssignaturaAdapter(this, mAssignaturaData);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT |
                ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {

                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(mAssignaturaData, from, to);
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                 int direction) {
                mAssignaturaData.remove(viewHolder.getAdapterPosition());
                mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }

        });
        helper.attachToRecyclerView(mRecyclerView);
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] assignaturaList = getResources()
                .getStringArray(R.array.titol_assignatura);
        String[] assignaturaInfo = getResources()
                .getStringArray(R.array.info_assignatura);

        TypedArray assignaturaImageResources =
                getResources().obtainTypedArray(R.array.portades_assignatura);
        // Clear the existing data (to avoid duplication).
        mAssignaturaData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<assignaturaList.length;i++){
            mAssignaturaData.add(new Assignatura(assignaturaList[i],assignaturaInfo[i],
                    assignaturaImageResources.getResourceId(i,0)));
        }

        assignaturaImageResources.recycle();

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();
    }

    //public void resetAssignatura(View view) {
    //    initializeData();
    //}
}
