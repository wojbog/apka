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

private Button BnAddUser,Bnviewusers,BNdelete, BNkartkowka;
private String TAG="HomeFragment";

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {
            case R.id.bn_add_user:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new AddUserFragment()).addToBackStack(null).commit();
        break;

            case R.id.bn_view_users:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new ReadUserFragment()).addToBackStack(null).commit();
            break;

            case R.id.bn_delete:
                MainActivity.fragmentManager.beginTransaction().replace(R.id.stefan,new DeleteFragment()).addToBackStack(null).commit();
                break;

            case R.id.bn_kartkowka:
                Log.d(TAG, "onClick: bn_kartkowka klikniety");
                Intent intent = new Intent(getActivity().getApplication(), KartkowkaActivity.class);
                Log.d(TAG, "onClick: wyznaczono intent");
                view.getContext().startActivity(intent);
                Log.d(TAG, "onClick: wystartowano intent");
                break;
        }

    }
}
