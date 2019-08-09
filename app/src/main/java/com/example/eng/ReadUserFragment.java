package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
String TAG="ReadUserFragment";


    public ReadUserFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        View view =  inflater.inflate(R.layout.fragment_read_user, container, false);
        textView=view.findViewById(R.id.zobacz);


        final List<User> users =MainActivity.baza.myDao().getUsers();

        StringBuilder info = new StringBuilder();

        for(User s:users)
        {
            String name =s.getName();
            String nazwisko = s.getSurname();

            //String Builder
            info.append("\n\n" + "id: ").append(s.id).append(", name: ").append(name).append(", surname: ").append(nazwisko);
        }

        textView.setText(info.toString());

    return view;
    }

}
