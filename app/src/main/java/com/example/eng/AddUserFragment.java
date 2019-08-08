package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**test
 * A simple {@link Fragment} subclass.
 */
public class AddUserFragment extends Fragment {

    private EditText Username, Usersurname;
    private Button BNsave;
    private String imie, nazwisko;

    public AddUserFragment() {
        // Required empty public constructor
    }
/*test*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_add_user, container, false);

       Username = view.findViewById(R.id.nameofimie);
       Usersurname = view.findViewById(R.id.surnameofnazwisko);
       BNsave = view.findViewById(R.id.zapisz);

       Username.setText("");
       Username.setHint("name");
       Usersurname.setText("");
       Usersurname.setHint("surname");

       BNsave.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

                   imie = Username.getText().toString();
                   nazwisko = Usersurname.getText().toString();

                   User user = new User();
                   user.setName(imie);
                   user.setSurname(nazwisko);

                   MainActivity.baza.myDao().addUser(user);
                   Toast.makeText(getContext(),"Dodany!!!!",Toast.LENGTH_LONG).show();

                   Username.setText("");
                   Usersurname.setText("");

               //ale jaja

           }
       });

       return view;

    }

}
