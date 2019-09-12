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

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;


public class AddUserFragment extends Fragment {

    private EditText Username, Usersurname;
    private String imie, nazwisko, kategoria, TAG="LOGAddUserFragment";
    private View layout;
    InterstitialAd mInterstitialAd;
    int ileDoReklam =1;

    public AddUserFragment(String katego) {
        kategoria = katego;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) getActivity().findViewById(R.id.custom_toast_container));

        Username = view.findViewById(R.id.nameofimie);
        Usersurname = view.findViewById(R.id.surnameofnazwisko);
        TextView categoryname = view.findViewById(R.id.zobacz_category);
        Button BNsave = view.findViewById(R.id.zapisz);

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
//        mAdView = view.findViewById(R.id.adViewAddUser);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        categoryname.setText(kategoria);
        Username.setText("");
        Username.setHint("Słówko");
        Usersurname.setText("");
        Usersurname.setHint("Tłumaczenie");

        BNsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    imie = Username.getText().toString().trim();
                    nazwisko = Usersurname.getText().toString().trim();

                    if (
                            nazwisko.contains("\\")||
                            nazwisko.contains("@")||
                            nazwisko.contains("#")||
                            nazwisko.contains("$")||
                            nazwisko.contains("%")||
                            nazwisko.contains("^")||
                            nazwisko.contains("*")||
                            nazwisko.contains("(")||
                            nazwisko.contains(")")||
                            nazwisko.contains("-")||
                            nazwisko.contains("_")||
                            nazwisko.contains("=")||
                            nazwisko.contains("{")||
                            nazwisko.contains("}")||
                            nazwisko.contains("[")||
                            nazwisko.contains("]")||
                            nazwisko.contains(":")||
                            nazwisko.contains(";")||
                            nazwisko.contains("<")||
                            nazwisko.contains(">")||
                            nazwisko.contains(",")||
                            nazwisko.contains(".")||
                            nazwisko.contains("/")||
                            nazwisko.contains("|")||
                            nazwisko.contains("\"")||
                            nazwisko.contains("+")||

                            imie.contains("\\")||
                            imie.contains("@")||
                            imie.contains("#")||
                            imie.contains("$")||
                            imie.contains("%")||
                            imie.contains("^")||
                            imie.contains("*")||
                            imie.contains("(")||
                            imie.contains(")")||
                            imie.contains("-")||
                            imie.contains("_")||
                            imie.contains("=")||
                            imie.contains("{")||
                            imie.contains("}")||
                            imie.contains("[")||
                            imie.contains("]")||
                            imie.contains(":")||
                            imie.contains(";")||
                            imie.contains("<")||
                            imie.contains(">")||
                            imie.contains(",")||
                            imie.contains(".")||
                            imie.contains("/")||
                            imie.contains("|")||
                            imie.contains("\"")||
                            imie.contains("+")
                    ) zrobToast("Nieodpowiedni znak!");
                    else if ((!imie.equals(""))&&(!nazwisko.equals("")))
                    {
                        if (mInterstitialAd.isLoaded()) {
                            if (ileDoReklam % 2 == 0) {
                                mInterstitialAd.show();
                            }
                        }
                        ileDoReklam++;
                        User user = new User();
                        user.setName(imie);
                        user.setSurname(nazwisko);
                        user.setCategory(kategoria);

                        MainActivity.baza.myDao().addUser(user);
                        zrobToast("Dodano!");

                        Username.setText("");
                        Usersurname.setText("");
                    }
                    else {
                        zrobToast("Najpierw wpisz słówko!");
                    }
            }
        });

        return view;

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
