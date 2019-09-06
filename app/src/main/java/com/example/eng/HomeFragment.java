package com.example.eng;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private String
            TAG="LOGHomeFragment",
            kategoria;

//    TODO:
//        -usuwanie kategorii to od razu z wszystkimi slowkami
//        -floating Action btn w kategoriach (Próbowałem i się nie udało)
//        -zrobić logo
//        -zrobić widget
//        -dogadać się co do nazwy apki
//        -w miarę możliwości animacje i gesty

    public HomeFragment(String kat) { kategoria = kat; }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Button bnviewusers = view.findViewById(R.id.bn_view_users);
        bnviewusers.setOnClickListener(this);

        Button BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        Button bnfiszki = view.findViewById(R.id.bn_fiszki);
        bnfiszki.setOnClickListener(this);

        Button bnDodaj = view.findViewById(R.id.add);
        bnDodaj.setOnClickListener(this);

        Button wybierzKategorieBtn = view.findViewById(R.id.wybierzKategorieBtn);
        wybierzKategorieBtn.setOnClickListener(this);
        wybierzKategorieBtn.setText(kategoria);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.bn_view_users:
                Log.d(TAG, "onClick: bn_view_users clicked.");
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                        .replace(R.id.stefan,new ReadUserFragment())
                        .addToBackStack(null)
                        .commit();
                Log.d(TAG, "onClick: fragment zmieniony na odczytanie slowek");
                break;

            case R.id.bn_kartkowka:
                Log.d(TAG, "onClick: bn_kartkowka klikniety");
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                        .replace(R.id.stefan, new KartkowkaFragment(kategoria))
                        .addToBackStack(null)
                        .commit();
                Log.d(TAG, "onClick: fragment zmieniony na kategorie z kartkowki");
                break;

            case R.id.bn_fiszki:
                Log.d(TAG, "onClick: bn_fiszki clicked.");

//                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan, new FiszkiFragment(kategoria)).addToBackStack(null).commit();
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                        .replace(R.id.stefan, new FiszkiFragment(kategoria))
                        .addToBackStack(null)
                        .commit();

                Log.d(TAG, "onClick: fragment zmieniony na fiszki");
                break;

            case R.id.add:
                Log.d(TAG, "onClick: bn_add clicked.");
                Intent xam = new Intent(getActivity().getApplication(), DodajActivity.class);
                view.getContext().startActivity(xam);
                Log.d(TAG, "onClick: fragment zmieniony na dodaj activity");
                break;

            case R.id.wybierzKategorieBtn:
                Log.d(TAG, "onClick: wybierzKategorieBtn clicked.");
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_out_right, android.R.anim.slide_in_left)
                        .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorie"))
                        .addToBackStack(null)
                        .commit();
                Log.d(TAG, "onClick: fragment zmieniony na readCategoryFragment");


        }

    }
}
