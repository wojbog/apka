package com.example.eng;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ReadCategoryFragment extends Fragment implements AdapterReadCategoryFragment.Click {
        public String nazwa[];
        public String klucz, skont;


    private RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    String TAG = "LOGReadCategoryFragment";
    boolean vi;
    private int mColumnCount = 1;
    View layout, view;




    public ReadCategoryFragment(String skond, boolean visibility) {
        skont = skond;
        vi = visibility;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        view = inflater.inflate(R.layout.fragment_pozycja_list, container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));
        final ArrayList<Listakategorii> felix = new ArrayList<>();
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

        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new AddCategoryFragment(skont, vi))
                        .addToBackStack(null)
                        .commit();
            }
        });

//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (mColumnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
//            }
//            recyclerView.setAdapter(new AdapterReadCategoryFragment(felix,this));
//        }


        recyclerView = view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        mAdapter = new AdapterReadCategoryFragment(felix, this);
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
                    User user= elo.get(id);
                    List<User> adam =MainActivity.baza.myDao().loadUserByKategoria(user.getName());
                    MainActivity.baza.myDao().deleteAllUsers(adam);
                    felix.remove(id);
                    mAdapter.notifyItemRemoved(id);
                    //user = MainActivity.baza.myDao().loadUserById(id);
                    MainActivity.bazaKategorii.myDao().deleteUsers(user);

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
    @Override
    public void onClickKlikniecie(int position) {
        klucz=nazwa[position];

        if (skont.equals("dodajKategorie")) {
            MainActivity.fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.stefan, new AddUserFragment(klucz))
                    .addToBackStack(null)
                    .commit();
        }
        if (skont.equals("wybierzKategorie")) {
            MainActivity.fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.stefan, new HomeFragment(klucz, vi))
                    .addToBackStack(null)
                    .commit();
        }
    }

}
