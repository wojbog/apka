package a.b.TEA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private String
            TAG="LOGHomeFragment";
    View view;
    Button BNkartkowka, bnviewusers, bnZolodek, fiszkiBtn,bnUstawienia,bnDodaj;
    //ImageView imageView4;

    public HomeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewHome);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        bnUstawienia = view.findViewById(R.id.ustawienia);
        bnUstawienia.setOnClickListener(this);

        bnviewusers = view.findViewById(R.id.bn_view_users);
        bnviewusers.setOnClickListener(this);

        BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        bnZolodek = view.findViewById(R.id.fiszkiZolodkiBtn);
        bnZolodek.setOnClickListener(this);

        fiszkiBtn = view.findViewById(R.id.fiszkiBtn);
        fiszkiBtn.setOnClickListener(this);

        bnDodaj = view.findViewById(R.id.add);
        bnDodaj.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.ustawienia:
                startActivity(new Intent(getActivity(), StartoweActivity.class));

                break;

            case R.id.bn_view_users:
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan,new ReadUserFragment("Wybierz Kategorię"))
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.bn_kartkowka:
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorieKartkowka"))
                        .addToBackStack(null)
                        .commit();

                break;

            case R.id.fiszkiBtn:
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorieFiszki"))
                            .addToBackStack(null)
                            .commit();

                break;

            case R.id.fiszkiZolodkiBtn:
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ZolodkiFragment())
                        .addToBackStack(null)
                        .commit();

                break;

            case R.id.add:

                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ReadCategoryFragment("dodajKategorie"))
                        .addToBackStack(null)
                        .commit();

                break;

        }

    }

    private void zrobToast(String coNapisac) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) view.findViewById(R.id.custom_toast_container));
        TextView text = layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void zmienMotyw() {
                final String PREFS_NAME = "MyPrefsFile";
                SharedPreferences settings = getActivity().getSharedPreferences(PREFS_NAME, 0);
                if (settings.getBoolean("my_theme", true)) {

                    getActivity().setTheme(R.style.AppTheme_niebieski);
                    Toast.makeText(getContext(), "Zmieniono motyw!", Toast.LENGTH_LONG).show();
                    settings.edit().putBoolean("my_theme", false).apply();
                    getActivity().recreate();
                }else {

                    getActivity().setTheme(R.style.AppTheme);
                    Toast.makeText(getContext(), "Zmieniono motyw!", Toast.LENGTH_LONG).show();
                    settings.edit().putBoolean("my_theme", true).apply();
                    getActivity().recreate();
                }
    }
}
