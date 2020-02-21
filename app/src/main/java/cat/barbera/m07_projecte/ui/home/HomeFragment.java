package cat.barbera.m07_projecte.ui.home;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

import cat.barbera.m07_projecte.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    private RecyclerView mRecyclerView;
    private ArrayList<Assignatura> mAssignaturaData;
    private AssignaturaAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //final TextView textView = root.findViewById(R.id.text_home);

        //RECYCLERVIEW WITH CARDVIEWS

        // Initialize the RecyclerView.
        mRecyclerView = root.findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Initialize the ArrayList that will contain the data.
        mAssignaturaData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new AssignaturaAdapter(getActivity(), mAssignaturaData);
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

        return root;
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

}