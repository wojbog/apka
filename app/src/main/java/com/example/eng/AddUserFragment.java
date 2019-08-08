package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {

    private EditText Username, Usersurname;
    private Button BNsave;

    public AddUserFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_add_user, container, false);

       Username = view.findViewById(R.id.nameofimie);
       Usersurname = view.findViewById(R.id.surnameofnazwisko);
       BNsave = view.findViewById(R.id.zapisz);

       BNsave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String imie = Username.getText().toString();
               String nazwisko = Usersurname.getText().toString();

               User user = new User();
               user.setName(imie);
               user.setSurname(nazwisko);

               MainActivity.baza.myDao().addUser(user);
               Toast.makeText(getContext(),"Dodany!!!!",Toast.LENGTH_LONG).show();

               Username.setText("");
               Usersurname.setText("");

           }
       });

       return view;

    }

}
