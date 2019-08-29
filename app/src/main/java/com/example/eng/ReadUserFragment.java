package com.example.eng;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    private RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    List<User> andrzej;

    String kate;
    View view;



    TextView textView;
String TAG="ReadUserFragment";


//    public ReadUserFragment(String k) {
//        kate = k;
//    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        view =  inflater.inflate(R.layout.fragment_read_user, container, false);


        final List<User> users =MainActivity.baza.myDao().getUsers();
        andrzej=users;


        recyclerView = view.findViewById(R.id.rower);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new Adapterek(andrzej);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);


       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                try
                {
                   int id= viewHolder.getAdapterPosition();
                    User user= andrzej.get(id);
                   andrzej.remove(id);
                    mAdapter.notifyItemRemoved(id);
                    //user = MainActivity.baza.myDao().loadUserById(id);
                   MainActivity.baza.myDao().deleteUsers(user);

//                    Toast.makeText(getContext() ,"Usunięto",Toast.LENGTH_LONG).show();
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.custom_toast,
                            (ViewGroup) view.findViewById(R.id.custom_toast_container));
                    TextView text = layout.findViewById(R.id.text);
                    text.setText("Usunięto");
                    Toast toast = new Toast(getContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(layout);
                    toast.show();

                    //TxUserId.setText("");
                    //Log.d(TAG, "onClick: Deleted.");
                }
                catch(Exception e)
                {
                    Toast.makeText(getContext() ,"Nie ma takiego id!",Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onClick: Exception Delete");
                }
            }
        }).attachToRecyclerView(recyclerView);

    return view;
    }

}
