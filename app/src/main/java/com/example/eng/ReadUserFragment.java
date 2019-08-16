package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    TextView textView;
String TAG="ReadUserFragment";


    //public ReadUserFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        View view =  inflater.inflate(R.layout.fragment_read_user, container, false);

        /*textView=view.findViewById(R.id.zobacz);


        final List<User> users =MainActivity.baza.myDao().getUsers();

        StringBuilder info = new StringBuilder();

        for(User s:users)
        {
            String name =s.getName();
            String nazwisko = s.getSurname();

            //String Builder
            info.append("\n\n" + "id: ").append(s.id).append(", name: ").append(name).append(", surname: ").append(nazwisko);
        }

        textView.setText(info.toString());*/
        ArrayList<ExampleItem> andrzej = new ArrayList<>();
        final List<User> users =MainActivity.baza.myDao().getUsers();
        for(User s:users)
        {
            String name =s.getName();
            String nazwisko = s.getSurname();
            int li = s.id;
            String liczba=Integer.toString(li);
            andrzej.add(new ExampleItem(name,nazwisko,liczba));


        }
//andrzej.add(new ExampleItem("Line 1","Line 1a","last"));
        recyclerView = view.findViewById(R.id.rower);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new Adapterek(andrzej);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);
    return view;
    }

}
