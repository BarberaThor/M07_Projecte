package cat.barbera.m07_projecte;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cat.barbera.m07_projecte.ui.home.Assignatura;
import cat.barbera.m07_projecte.ui.home.AssignaturaAdapter;
import cat.barbera.m07_projecte.ui.home.HomeFragment;

public class SecondActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ArrayList<Assignatura> mAssignaturaData;
    private AssignaturaAdapter mAdapter;

    private List<String> titolList;
    private List<String> autorList;
    private List<String> infoList;
    private List<String> aList;


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

        titolList = new LinkedList<String>();
        autorList = new LinkedList<String>();
        infoList = new LinkedList<String>();
        aList = new LinkedList<String>();

        final TypedArray assignaturaImageResources = getResources().obtainTypedArray(R.array.portades_assignatura);

        for(int i = 0; i<4; i++) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference myRef = database.getReference("Posts");

            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.

                    System.out.println("Test second activity");

                    for ( DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){

                        HashMap map = (HashMap)dataSnapshot1.getValue();
                        titolList.add(map.get("Titol").toString());
                        autorList.add(map.get("Autor").toString());
                        infoList.add(map.get("Contingut").toString());
                        aList.add(map.get("Assignatura").toString());
                    }

                    mAssignaturaData.clear();
                    for(int i=0;i<aList.size();i++) {
                        System.out.println("test second 3");
                        mAssignaturaData.add(new Assignatura(titolList.get(i),infoList.get(i), assignaturaImageResources.getResourceId(i,0), autorList.get(i)));

                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w("", "Failed to read value.", error.toException());
                }
            });
        }





        // Get the resources from the XML file.
        String[] assignaturaList = getResources()
                .getStringArray(R.array.titol_assignatura);
        String[] assignaturaInfo = getResources()
                .getStringArray(R.array.info_assignatura);

        //TypedArray assignaturaImageResources = getResources().obtainTypedArray(R.array.portades_assignatura);



        // Clear the existing data (to avoid duplication).
        //mAssignaturaData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.

       /*
        for(int i=0;i<assignaturaList.length;i++){
            mAssignaturaData.add(new Assignatura(assignaturaList[i],assignaturaInfo[i],
                    assignaturaImageResources.getResourceId(i,0)));
        }


        */
        //assignaturaImageResources.recycle();

        // Notify the adapter of the change.
        //mAdapter.notifyDataSetChanged();
    }

    //public void resetAssignatura(View view) {
    //    initializeData();
    //}
}
