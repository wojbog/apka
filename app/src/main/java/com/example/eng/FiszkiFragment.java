package com.example.eng;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;


public class FiszkiFragment extends Fragment {

    private String
            kategoria,
            TAG = "FiszkiFragment",
            imie,
            nazwisko;
    private View view;
    private boolean odwrocone=false;
    private List<User> users;
    private TextView kartaTV;
    private Button button;
    private int[] ostatnieLosy;
    private int test=0;
    private Random random = new Random();


    public FiszkiFragment(String kate) {
        kategoria = kate;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_fiszki, container, false);
        users = MainActivity.baza.myDao().loadUserByKategoria(kategoria);
        kartaTV = view.findViewById(R.id.kartaTV);
        button = view.findViewById(R.id.kolejneBtn);

        ostatnieLosy = new int[users.size()];
        for (int i=0; i<users.size(); i++) {ostatnieLosy[i]=0;}

        losujSlowko();
        kartaTV.setText(imie);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                losujSlowko();
                if (!odwrocone) kartaTV.setText(imie); else if (odwrocone) kartaTV.setText(nazwisko);
            }
        });

        kartaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: called.");
                if (!odwrocone) {
                    kartaTV.setText(nazwisko);
                    kartaTV.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiszkishapereversed));
                    odwrocone=true;
                }else if (odwrocone)
                {
                    kartaTV.setText(imie);
                    kartaTV.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.fiszkishape));
                    odwrocone=false;
                }
            }
        });


        return view;
    }

    void losujSlowko()
    {
        String[]
                imiona = new String[users.size()],
                nazwiska = new String[users.size()];


        Log.d(TAG, "napelnijTabele: called.");
        int g =0;


        if (users.size()>0)
        {
            for(User s:users)
            {
                String imie =s.getName();
                String nazwisko = s.getSurname();
                imiona[g] = imie;
                nazwiska[g] = nazwisko;
                g++;
            }
            Log.d(TAG, "napelnijTabele: napelniono.");
        }else
        {
            zrobToast("Dodaj przynajmniej dwa słówka aby rozpocząć");
        }

        int los;
        Log.d(TAG, "losujSlowko: called. "+users.size());

        if (users.size()>1) {
            for (int i=0; i<users.size(); i++)
            {
                if (ostatnieLosy[i] == 1)
                {
                    test++;
                    Log.d(TAG, "losujSlowko: wylosowanych: "+test+" wielkość: "+users.size());
                }
            }

            if (test == users.size())
            {
                Log.d(TAG, "losujSlowko: finish");
                test=0;
                ostatnieLosy = new int[users.size()];
                for (int i=0; i<users.size(); i++) {ostatnieLosy[i]=0;}
                losujSlowko();
            }

            else {
                do {
                    los = random.nextInt(users.size());
                }
                while (ostatnieLosy[los] == 1);

                test = 0;
                ostatnieLosy[los] = 1;
                imie = imiona[los];
                nazwisko = nazwiska[los];
                Log.d(TAG, "losujSlowko: wylosowano");
            }
        }else if (!kategoria.equals("Wybierz"))
        {
            zrobToast("dodaj przynajmniej dwa słówka aby rozpocząć");
            MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan, new HomeFragment(kategoria)).addToBackStack(null).commit();
            Log.d(TAG, "losujSlowko: za malo slowek");
        }
        else
        {
            zrobToast("Najpierw wybierz kategorię!");
            MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan, new HomeFragment(kategoria)).addToBackStack(null).commit();
        }
    }

    private void zrobToast(String coNapisac) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) view.findViewById(R.id.custom_toast_container));
        TextView text = layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
