package com.example.eng;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddCategoryFragment extends Fragment {


    private EditText elo;
        private Button przycisk;
        private String linia,TAG = "AddCategoryyFargment";
        private static String su = "meldojthgsbxgslwojrfidyvsnrownxossaa";
        private static String ka = "hdshjaiasaslokasjdjasadkjjdiayucxzpw";

    public AddCategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        elo=view.findViewById(R.id.napisz_kategorie);
        przycisk =view.findViewById(R.id.button_add_category);
        elo.setText("");
        elo.setHint("Podaj kategorie");
        przycisk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: called.");

                linia = elo.getText().toString().trim();

                if (!linia.equals(""))
                {
                    User user = new User();
                    user.setName(linia);
                    user.setSurname(su);
                    user.setCategory(ka);

                    MainActivity.bazaKategorii.myDao().addUser(user);
//                        Toast.makeText(getContext(), "Dodano!!", Toast.LENGTH_SHORT).show();
                    Snackbar.make(view, "DODANO!!", Snackbar.LENGTH_SHORT)
                            .setAction("Action", null).show();

                    elo.setText("");
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
