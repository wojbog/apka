package com.example.eng;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCategoryFragment extends Fragment {


    private EditText napiszKategorieET;
    private String kategoria, TAG = "LOGAddCategoryFragment", skont;
    private static String su = "meldojthgsbxgslwojrfidyvsnrownxossaa";
    private static String ka = "hdshjaiasaslokasjdjasadkjjdiayucxzpw";
    View layout;

    public AddCategoryFragment(String s) {
        skont=s;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));

        napiszKategorieET =view.findViewById(R.id.napisz_kategorieET);
        Button przycisk = view.findViewById(R.id.button_add_category);
        napiszKategorieET. setText("");
        napiszKategorieET.setHint("Podaj kategorie");
        przycisk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: called.");

                kategoria = napiszKategorieET.getText().toString().trim();


                if (
                        kc("|")||
                        kc("\\")||
                        kc("!")||
                        kc("@")||
                        kc("#")||
                        kc("$")||
                        kc("%")||
                        kc("^")||
                        kc("&")||
                        kc("*")||
                        kc("(")||
                        kc(")")||
                        kc("_")||
                        kc("-")||
                        kc("=")||
                        kc("+")||
                        kc("{")||
                        kc("[")||
                        kc("}")||
                        kc("]")||
                        kc(":")||
                        kc(";")||
                        kc("'")||
                        kc("\"")||
                        kc("<")||
                        kc(",")||
                        kc(".")||
                        kc(">")||
                        kc("?")||
                        kc("/")
                ) {
                    zrobToast("Nieodpowiedni znak!");
                }
                else if (!kategoria.equals(""))
                {
                    User user = new User();
                    user.setName(kategoria);
                    user.setSurname(su);
                    user.setCategory(ka);

                    MainActivity.bazaKategorii.myDao().addUser(user);
                    zrobToast("Dodano!");

                    napiszKategorieET.setText("");
                    Log.d(TAG, "onClick: Dodano");

                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment(skont))
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    zrobToast("Najpierw wpisz słówko");
                    Log.d(TAG, "onClick: najpierw slowko");
                }
            }
        });

        return view;
    }

    private boolean kc(String a)
    {
        return kategoria.contains(a);
    }

    private void zrobToast(String coNapisac) {
        TextView text = layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
