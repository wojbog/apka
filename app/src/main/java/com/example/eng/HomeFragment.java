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

private Button BnAddUser,Bnviewusers,BNdelete, BNkartkowka, Bnfiszki,BnDodaj,bn_zobacz;
private String TAG="LOGHomeFragment";

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        BnAddUser = view.findViewById(R.id.bn_add_user);
        BnAddUser.setOnClickListener(this);

        Bnviewusers = view.findViewById(R.id.bn_view_users);
        Bnviewusers.setOnClickListener(this);

        BNdelete= view.findViewById(R.id.bn_delete);
        BNdelete.setOnClickListener(this);

        BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        Bnfiszki = view.findViewById(R.id.bn_fiszki);
        Bnfiszki.setOnClickListener(this);

        BnDodaj= view.findViewById(R.id.add);
        BnDodaj.setOnClickListener(this);

        bn_zobacz= view.findViewById(R.id.zobacz_category);
        bn_zobacz.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.bn_add_user:
                Log.d(TAG, "onClick: bn_add_user clicked.");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new AddUserFragment()).addToBackStack(null).commit();
                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.bn_view_users:
                Log.d(TAG, "onClick: bn_view_users clicked.");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new ReadUserFragment()).addToBackStack(null).commit();
                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.bn_delete:
                Log.d(TAG, "onClick: bn_delete clicked.");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new DeleteFragment()).addToBackStack(null).commit();
                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.bn_kartkowka:
                Log.d(TAG, "onClick: bn_kartkowka klikniety");
                Intent intent = new Intent(getActivity().getApplication(), KartkowkaActivity.class);
                view.getContext().startActivity(intent);
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
                Intent xam = new Intent(getActivity().getApplication(), Dodaj.class);
                view.getContext().startActivity(xam);
                Log.d(TAG, "onClick: fragment zmieniony");
                break;

            case R.id.zobacz_category:
                Log.d(TAG, "onClick: bn_zobacz_category clicked.");
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new ReadCategoryFragment()).addToBackStack(null).commit();
                Log.d(TAG, "onClick: fragment zmieniony");
                break;
        }

    }
}
