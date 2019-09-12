package a.b.TEA;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eng.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

public class AddCategoryFragment extends Fragment {


    private EditText napiszKategorieET;
    private String kategoria, TAG = "LOGAddCategoryFragment", skont;
    private static String su = "meldojthgsbxgslwojrfidyvsnrownxossaa";
    private static String ka = "hdshjaiasaslokasjdjasadkjjdiayucxzpw";
    View layout;

    InterstitialAd mInterstitialAd;
    boolean vi;

    public AddCategoryFragment(String s, boolean visibility) {
        skont=s;
        vi=visibility;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_category, container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));

        mInterstitialAd = new InterstitialAd(getActivity().getApplicationContext());
        mInterstitialAd.setAdUnitId(getResources().getString(R.string.intersistial_ad_unit_id));
        mInterstitialAd.loadAd(new AdRequest.Builder().addTestDevice("531DB919ED797626DB5AE53A00FFBB9F").build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewAddCategory);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        napiszKategorieET =view.findViewById(R.id.napisz_kategorieET);
        Button przycisk = view.findViewById(R.id.button_add_category);
        napiszKategorieET. setText("");
        napiszKategorieET.setHint("Podaj kategorie");
        przycisk.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

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
                        kc(";")||
                        kc("'")||
                        kc("\"")||
                        kc("<")||
                        kc(">")||
                        kc("?")||
                        kc("/")
                ) {
                    zrobToast("Nieodpowiedni znak!");
                }
                else if (!kategoria.equals(""))
                {
                    if (mInterstitialAd.isLoaded()) {
                        mInterstitialAd.show();
                    }
                    User user = new User();
                    user.setName(kategoria);
                    user.setSurname(su);
                    user.setCategory(ka);

                    MainActivity.bazaKategorii.myDao().addUser(user);
                    zrobToast("Dodano!");

                    napiszKategorieET.setText("");

                    MainActivity.fragmentManager.beginTransaction()
                            .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                                    android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                            .replace(R.id.stefan, new ReadCategoryFragment(skont, vi))
                            .addToBackStack(null)
                            .commit();
                }
                else {
                    zrobToast("Najpierw wpisz słówko");
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
