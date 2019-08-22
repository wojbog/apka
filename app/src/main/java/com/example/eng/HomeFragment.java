package com.example.eng;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private String TAG="LOGHomeFragment";

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        Button bnviewusers = view.findViewById(R.id.bn_view_users);
        bnviewusers.setOnClickListener(this);

        Button BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        Button bnfiszki = view.findViewById(R.id.bn_fiszki);
        bnfiszki.setOnClickListener(this);

        Button bnDodaj = view.findViewById(R.id.add);
        bnDodaj.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.bn_view_users:
                Log.d(TAG, "onClick: bn_view_users clicked.");
//                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new ReadUserFragment("")).addToBackStack(null).commit();
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new ReadUserFragment()).addToBackStack(null).commit();
                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.bn_kartkowka:
                Log.d(TAG, "onClick: bn_kartkowka klikniety");
//                Intent intent = new Intent(getActivity().getApplication(), artkowkaActivity.class);
//                view.getContext().startActivity(intent);

                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new ReadCategoryFragment("kartkowka")).addToBackStack(null).commit();

                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.bn_fiszki:
                Log.d(TAG, "onClick: bn_fiszki clicked.");
                Intent intents = new Intent(getActivity().getApplication(), FiszkiActivity.class);
                view.getContext().startActivity(intents);
                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.add:
                Log.d(TAG, "onClick: bn_add clicked.");
                Intent xam = new Intent(getActivity().getApplication(), DodajActivity.class);
                view.getContext().startActivity(xam);
                Log.d(TAG, "onClick: fragment zmieniony");
                break;




        }

    }
}
