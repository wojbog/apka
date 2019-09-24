package a.b.TEA;

import android.content.Intent;
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
            TAG="LOGHomeFragment",
            kategoria;
    View view;
    Button BNkartkowka, bnviewusers, bnZolodek, fiszkiBtn;
    ImageView imageView4;
    List<User> users1,users2,users3,users4,users5;

    public HomeFragment(String kat) { kategoria = kat;}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewHome);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        imageView4 = view.findViewById(R.id.imageView4);
        imageView4.setOnClickListener(this);

        bnviewusers = view.findViewById(R.id.bn_view_users);
        bnviewusers.setOnClickListener(this);

        BNkartkowka = view.findViewById(R.id.bn_kartkowka);
        BNkartkowka.setOnClickListener(this);

        bnZolodek = view.findViewById(R.id.fiszkiZolodkiBtn);
        bnZolodek.setOnClickListener(this);

        fiszkiBtn = view.findViewById(R.id.fiszkiBtn);
        fiszkiBtn.setOnClickListener(this);

        Button bnDodaj = view.findViewById(R.id.add);
        bnDodaj.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.imageView4:
                startActivity(new Intent(getActivity(), StartoweActivity.class));
                break;

            case R.id.bn_view_users:
                MainActivity.fragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                        .replace(R.id.stefan,new ReadUserFragment(kategoria))
                        .addToBackStack(null)
                        .commit();
                break;

            case R.id.bn_kartkowka:
                if (!kategoria.equals("Wybierz Kategorię")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new KartkowkaFragment(kategoria))
                            .addToBackStack(null)
                            .commit();
                }else {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorieKartkowka"))
                            .addToBackStack(null)
                            .commit();
                }
                break;

            case R.id.fiszkiBtn:
                if (!kategoria.equals("Wybierz Kategorię")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new FiszkiFragment(kategoria))
                            .addToBackStack(null)
                            .commit();
                }else {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment("wybierzKategorieFiszki"))
                            .addToBackStack(null)
                            .commit();
                }
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
                if (kategoria.equals("Wybierz Kategorię")) {
                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment("dodajKategorie"))
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
