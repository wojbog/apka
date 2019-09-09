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
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private String
            TAG="LOGHomeFragment",
            kategoria;
    View view;
    boolean vi=false;
    Button bnfiszki, BNkartkowka, bnviewusers;

    //TODO: profil z osiągnięciami / statystykami

    public HomeFragment(String kat, boolean visibility) { kategoria = kat; vi=visibility;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        view = inflater.inflate(R.layout.fragment_home, container, false);


        bnviewusers = view.findViewById(R.id.bn_view_users);
        bnviewusers.setOnClickListener(this);

        BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        bnfiszki = view.findViewById(R.id.bn_fiszki);
        bnfiszki.setOnClickListener(this);

        if (vi)
        {
            bnfiszki.setVisibility(View.VISIBLE);
            bnviewusers.setVisibility(View.VISIBLE);
            BNkartkowka.setVisibility(View.VISIBLE);
        }

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
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan,new ReadUserFragment(kategoria, vi))
                        .addToBackStack(null)
                        .commit();
                Log.d(TAG, "onClick: fragment zmieniony na odczytanie slowek");
                break;

            case R.id.bn_kartkowka:
                Log.d(TAG, "onClick: bn_kartkowka klikniety");
                if (!kategoria.equals("Wybierz")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new KartkowkaFragment(kategoria, vi))
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "onClick: fragment zmieniony na kategorie z kartkowki");
                }else zrobToast("Najpierw Wybierz kategorię");
                break;

            case R.id.bn_fiszki:
                Log.d(TAG, "onClick: bn_fiszki clicked.");
                if (!kategoria.equals("Wybierz")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new FiszkiFragment(kategoria, vi))
                            .addToBackStack(null)
                            .commit();
                    Log.d(TAG, "onClick: fragment zmieniony na fiszki");
                }else zrobToast("Najpierw Wybierz kategorię");
                break;

            case R.id.add:
                Log.d(TAG, "onClick: bn_add clicked.");
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ReadCategoryFragment("dodajKategorie", vi))
                        .addToBackStack(null)
                        .commit();
                Log.d(TAG, "onClick: fragment zmieniony na dodaj activity");
                break;

            case R.id.wybierzKategorieBtn:
                Log.d(TAG, "onClick: wybierzKategorieBtn clicked.");
                vi=true;
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorie", true))
                        .addToBackStack(null)
                        .commit();

                Log.d(TAG, "onClick: fragment zmieniony na readCategoryFragment");

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
