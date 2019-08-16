package com.example.eng;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReadCategoryFragment extends Fragment implements AdapterReadCategoryFragment.Click {



    String TAG = "LOGReadCategoryFragment";
    private int mColumnCount = 1;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReadCategoryFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called.");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        View view = inflater.inflate(R.layout.fragment_pozycja_list, container, false);

        ArrayList<Listakategorii> felix = new ArrayList<>();
        final List<User> elo = MainActivity.bazaKategorii.myDao().loadAllCategory();
          for (User s:elo)
          {
              String cos = s.getName();
              felix.add(new Listakategorii(cos));
          }
        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            recyclerView.setAdapter(new AdapterReadCategoryFragment(felix,this));
        }
        return view;
    }
    @Override
    public void onClickKlikniecie(int position) {
        //na razie tylko toast
        Toast.makeText(getContext(),"klik pozycja: "+position,Toast.LENGTH_SHORT).show();

    }


}
