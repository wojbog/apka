package com.example.eng;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class KoniecKartkowkiFragment extends Fragment {

    private String dobrych, zlych, kategoria;

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

        TextView dobrychTVkoniec = view.findViewById(R.id.dobrychTVkoniec);
        TextView zlychTVkoniec = view.findViewById(R.id.zlychTVkoniec);
        Button zakoncz = view.findViewById(R.id.zakonczBtn);
        dobrychTVkoniec.setText(dobrych);
        zlychTVkoniec.setText(zlych);

        zakoncz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left).replace(R.id.stefan, new HomeFragment(kategoria)).disallowAddToBackStack().commit();
            }
        });

        return view;
    }

}
