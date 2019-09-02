package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class KoniecKartkowkiFragment extends Fragment {

    String dobrych, zlych, kategoria;
    TextView dobrychTVkoniec, zlychTVkoniec;
    Button zakoncz;

    public KoniecKartkowkiFragment(String dobryh, String zlyh, String kateg) {
        dobrych = dobryh;
        zlych = zlyh;
        kategoria = kateg;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koniec_kartkowki, container, false);

        dobrychTVkoniec = view.findViewById(R.id.dobrychTVkoniec);
        zlychTVkoniec = view.findViewById(R.id.zlychTVkoniec);
        zakoncz = view.findViewById(R.id.zakonczBtn);
        dobrychTVkoniec.setText(dobrych);
        zlychTVkoniec.setText(zlych);

        zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan, new HomeFragment(kategoria)).disallowAddToBackStack().commit();
            }
        });

        return view;
    }

}
