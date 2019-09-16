package a.b.TEA;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private String
            TAG="LOGHomeFragment",
            kategoria;
    View view;
    boolean vi=false;
    Button bnfiszki, BNkartkowka, bnviewusers, bnZolodek;

    public HomeFragment(String kat, boolean visibility) { kategoria = kat; vi=visibility;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewHome);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        bnviewusers = view.findViewById(R.id.bn_view_users);
        bnviewusers.setOnClickListener(this);

        BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        bnfiszki = view.findViewById(R.id.bn_fiszki);
        bnfiszki.setOnClickListener(this);

        bnZolodek = view.findViewById(R.id.bn_zolodek);
        bnZolodek.setOnClickListener(this);

        if (vi)
        {
            bnfiszki.setVisibility(View.VISIBLE);
            BNkartkowka.setVisibility(View.VISIBLE);
        }else
        {
            bnfiszki.setVisibility(View.GONE);
            BNkartkowka.setVisibility(View.GONE);
        }

        Button bnDodaj = view.findViewById(R.id.add);
        bnDodaj.setOnClickListener(this);

        Button wybierzKategorieBtn = view.findViewById(R.id.wybierzKategorieBtn);
        wybierzKategorieBtn.setOnClickListener(this);
        wybierzKategorieBtn.setText(kategoria);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.bn_view_users:
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan,new ReadUserFragment(kategoria, vi))
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.bn_kartkowka:
                if (!kategoria.equals("Wybierz Kategorię")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new KartkowkaFragment(kategoria, vi))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Najpierw Wybierz kategorię");
                break;

            case R.id.bn_fiszki:
                if (!kategoria.equals("Wybierz Kategorię")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new FiszkiFragment(kategoria, vi))
                            .addToBackStack(null)
                            .commit();
                }else zrobToast("Najpierw Wybierz kategorię");
                break;


            case R.id.bn_zolodek:
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ZolodkiFragment())
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.add:
                if (kategoria.equals("Wybierz Kategorię")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment("dodajKategorie", vi))
                            .addToBackStack(null)
                            .commit();
                } else {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new AddUserFragment(kategoria))
                            .addToBackStack(null)
                            .commit();
                }

                break;

            case R.id.wybierzKategorieBtn:
                vi=true;
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorie", true))
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
}
