package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class AddUserFragment extends Fragment {

    private EditText Username, Usersurname, UserCategory;
    private Button BNsave;
    private String imie, nazwisko, kategoria, TAG="AddUserFragment";

    public AddUserFragment() {
        //potrzebny pusty publiczny konstruktor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);

        Username = view.findViewById(R.id.nameofimie);
        Usersurname = view.findViewById(R.id.surnameofnazwisko);
        UserCategory = view.findViewById(R.id.categoryOfKategoria);
        BNsave = view.findViewById(R.id.zapisz);

        Username.setText("");
        Username.setHint("name");
        Usersurname.setText("");
        Usersurname.setHint("surname");
        UserCategory.setText("");
        UserCategory.setHint("Category");

        BNsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: called.");

                    imie = Username.getText().toString().trim();
                    nazwisko = Usersurname.getText().toString().trim();
                    kategoria = UserCategory.getText().toString().trim();

                    if ((!imie.equals(""))&&(!nazwisko.equals("")))
                    {
                        User user = new User();
                        user.setName(imie);
                        user.setSurname(nazwisko);
                        user.setCategory(kategoria);

                        MainActivity.baza.myDao().addUser(user);
//                        Toast.makeText(getContext(), "Dodano!!", Toast.LENGTH_SHORT).show();
                        Snackbar.make(view, "DODANO!!", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();

                        Username.setText("");
                        Usersurname.setText("");
                        UserCategory.setText("");
                    }
                    else {
                        Snackbar.make(view, "Najpierw wpisz słówko", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
            }
        });

        return view;

    }

}
