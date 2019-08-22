package com.example.eng;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ReadCategoryFragment extends Fragment implements AdapterReadCategoryFragment.Click {
        public String nazwa[];
        public String klucz, skont;


    String TAG = "LOGReadCategoryFragment";
    private int mColumnCount = 1;
    View layout;



    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ReadCategoryFragment(String skond) {
        skont = skond;
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
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));

        ArrayList<Listakategorii> felix = new ArrayList<>();
        final List<User> elo = MainActivity.bazaKategorii.myDao().loadAllCategory();
        nazwa=new String[elo.size()];
        int i=0;
          for (User s:elo)
          {
              String cos = s.getName();
              nazwa[i]=cos;
              felix.add(new Listakategorii(cos));
              i++;
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
        klucz=nazwa[position];
        zrobToast("Klik pozycja: "+position+klucz);
//        Intent intents = new Intent(getActivity().getApplication(), DodajUser.class);
//        startActivity(intents);
//          DodajActivity.fragmentManager.beginTransaction().add(R.id.kontener,new ReadUserFragment(klucz)).commit();
//          DodajActivity.fragmentManager.beginTransaction().add(R.id.kontener,new ReadUserFragment()).commit();

        if (skont.equals("kategoria")) {
            DodajActivity.fragmentManager.beginTransaction().replace(R.id.kontener, new AddUserFragment(klucz)).addToBackStack(null).commit();
        }
        if (skont.equals("kartkowka")) {
            MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan, new KartkowkaFragment(klucz)).addToBackStack(null).commit();
        }
    }

    private void zrobToast(String coNapisac) {
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }


}
