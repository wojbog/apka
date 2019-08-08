package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadUserFragment extends Fragment {

TextView textView;


    public ReadUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_read_user, container, false);
        textView=view.findViewById(R.id.zobacz);




        final List<User> users =MainActivity.baza.myDao().getUsers();

        String info ="";

        for(User s:users)
        {
            String name =s.getName();
            String nazwisko = s.getSurname();

            info = info+"\n\n"+"id: "+s.id+"po polsku: "+name+"po angilsku: "+nazwisko;
        }

        textView.setText(info);

    return view;
    }

}
