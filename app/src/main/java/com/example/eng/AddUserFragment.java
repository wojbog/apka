package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class AddUserFragment extends Fragment {

    private EditText Username, Usersurname;
    private String imie, nazwisko, kategoria, TAG="LOGAddUserFragment";
    private TextView UserCategory;
    View layout;

    public AddUserFragment() {
        //potrzebny pusty publiczny konstruktor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: called.");
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));

        Username = view.findViewById(R.id.nameofimie);
        Usersurname = view.findViewById(R.id.surnameofnazwisko);
        UserCategory = view.findViewById(R.id.categoryofkategoria);
        Button BNsave = view.findViewById(R.id.zapisz);

       // ReadCategoryFragment readCategoryFragment = new ReadCategoryFragment();
       // kategoria=readCategoryFragment.klucz;
        //tu chciałem dodac aby tak kategoria automatycznie się przekazała ale to nie działa
        UserCategory.setText("tak nie działa");
        Username.setText("");
        Username.setHint("name");
        Usersurname.setText("");
        Usersurname.setHint("surname");

        BNsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: called.");

                    imie = Username.getText().toString().trim();
                    nazwisko = Usersurname.getText().toString().trim();
                   kategoria = "koniec zabawy";

                    if ((!imie.equals(""))&&(!nazwisko.equals("")))
                    {
                        User user = new User();
                        user.setName(imie);
                        user.setSurname(nazwisko);
                        user.setCategory(kategoria);

                        MainActivity.baza.myDao().addUser(user);
                        zrobToast("Dodano!");
                        Log.d(TAG, "onClick: Dodano");

                        Username.setText("");
                        Usersurname.setText("");
                       // UserCategory.setText("");
                    }
                    else {
                        zrobToast("Najpierw wpisz słówko!");
                        Log.d(TAG, "onClick: najpierw slowko");
                    }
            }
        });

        return view;

    }

    private void zrobToast(String coNapisac) {
        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0, -600);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
