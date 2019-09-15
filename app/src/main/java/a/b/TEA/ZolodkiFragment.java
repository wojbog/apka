package a.b.TEA;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class ZolodkiFragment extends Fragment {

    View view;
    String kategoria;

    //todo: zrobić żołądki

    public ZolodkiFragment(String katego) {
        kategoria = katego;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zolodki, container, false);
        List<User> users = MainActivity.baza.myDao().getUsers();


        return view;
    }

}
