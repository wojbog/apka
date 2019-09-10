package com.example.eng;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KartkowkaFragment extends Fragment {

    private Random random = new Random();
    private List<User> users;

    private int
            dobrych=0,
            zlych=0;
    private String
            kateg,
            imie="brak",
            nazwisko="brak",
            TAG = "LOGKartkowkaFragment";
    private int[] ostatnieLosy;
    private int test=0, t;
    private boolean
            odwrotnie=false,
            vi;
    View view;
    Button odw;


    public KartkowkaFragment(String k, boolean visibility) {
        kateg = k;
        vi=visibility;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        users = MainActivity.baza.myDao().loadUserByKategoria(kateg);

        view = inflater.inflate(R.layout.fragment_kartkowka, container, false);


//        AdView mAdView;
//        mAdView = view.findViewById(R.id.adViewKartkowka);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        ostatnieLosy = new int[users.size()];
        for (int i=0; i<users.size(); i++) {ostatnieLosy[i]=0;}
        losujSlowko();
        ustawWyglad();

        Button bn_sprawdz = view.findViewById(R.id.bn_sprawdz);
        bn_sprawdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click");
                sprawdz();
            }
        });

        odw = view.findViewById(R.id.odw);
        odw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (odwrotnie) odwrotnie=false;
                else if (!odwrotnie) odwrotnie=true;
                ustawWyglad();
                odw.setVisibility(View.INVISIBLE);
            }
        });

        return view;
    }

    private void sprawdz()
    {
        Log.d(TAG, "sprawdz: called.");
        EditText tlumaczenieET = view.findViewById(R.id.tlumaczenieET);
        String nazwiskoWpisane = tlumaczenieET.getText().toString().trim();
        boolean ok=false;

        if (odwrotnie) ok = nazwiskoWpisane.matches(imie);
        else if (!odwrotnie) ok = nazwiskoWpisane.matches(nazwisko);

        if (
                nazwiskoWpisane.contains("\\")||
                nazwiskoWpisane.contains("@")||
                nazwiskoWpisane.contains("#")||
                nazwiskoWpisane.contains("$")||
                nazwiskoWpisane.contains("%")||
                nazwiskoWpisane.contains("^")||
                nazwiskoWpisane.contains("&")||
                nazwiskoWpisane.contains("*")||
                nazwiskoWpisane.contains("(")||
                nazwiskoWpisane.contains(")")||
                nazwiskoWpisane.contains("-")||
                nazwiskoWpisane.contains("_")||
                nazwiskoWpisane.contains("=")||
                nazwiskoWpisane.contains("{")||
                nazwiskoWpisane.contains("}")||
                nazwiskoWpisane.contains("[")||
                nazwiskoWpisane.contains("]")||
                nazwiskoWpisane.contains(":")||
                nazwiskoWpisane.contains(";")||
                nazwiskoWpisane.contains("<")||
                nazwiskoWpisane.contains(">")||
                nazwiskoWpisane.contains(",")||
                nazwiskoWpisane.contains(".")||
                nazwiskoWpisane.contains("/")||
                nazwiskoWpisane.contains("|")||
                nazwiskoWpisane.contains("\"")||
                nazwiskoWpisane.contains("+")

        ) zrobToast("Nieodpowiedni znak!");

        else if (nazwiskoWpisane.matches("")) zrobToast("Wpisz tłumaczenie");
        else if(ok)
        {
            if (!odwrotnie) odw.setVisibility(View.INVISIBLE);
            Log.d(TAG, "sprawdz: dobre");
            dobrych++;

            TextView dobrychTV = view.findViewById(R.id.dobrychTV);
            final Animation animationScale;
            animationScale = AnimationUtils.loadAnimation(getContext(),
                    R.anim.scale2);
            dobrychTV.startAnimation(animationScale);

            Log.d(TAG, "sprawdz: pokazano toast");
            restart();
        }else
        {
            if (!odwrotnie) odw.setVisibility(View.INVISIBLE);
            Log.d(TAG, "sprawdz: zle");
            zlych++;

            TextView zlychTV = view.findViewById(R.id.zlychTV);
            final Animation animationScale;
            animationScale = AnimationUtils.loadAnimation(getContext(),
                    R.anim.scale);
            zlychTV.startAnimation(animationScale);
//            pokazToast("red");

            Log.d(TAG, "sprawdz: pokazano toast");
            restart();
        }
    }

    private void restart()
    {
        Log.d(TAG, "restart: called.");
        losujSlowko();
        ustawWyglad();
        Log.d(TAG, "restart: restartowano");
    }

    private void ustawWyglad()
    {
        TextView
                slowkoTV = view.findViewById(R.id.slowkoTV),
                dobrychTV = view.findViewById(R.id.dobrychTV),
                zlychTV = view.findViewById(R.id.zlychTV);
        EditText
                tlumaczenieET = view.findViewById(R.id.tlumaczenieET);

        Log.d(TAG, "ustawWyglad: called.");
        if (!odwrotnie) slowkoTV.setText(imie);
        else if (odwrotnie) slowkoTV.setText(nazwisko);
        dobrychTV.setText(String.format(Locale.getDefault(), "%d", dobrych));
        zlychTV.setText(String.format(Locale.getDefault(), "%d", zlych));
        tlumaczenieET.setHint("Tu wpisz tłumaczenie");
        tlumaczenieET.setText("");
        Log.d(TAG, "ustawWyglad: ustawiono");
    }

    private void losujSlowko()
    {
//-------napelnij stringi-----------
        String[]
                imiona = new String[users.size()],
                nazwiska = new String[users.size()];


            Log.d(TAG, "napelnijTabele: called.");
            int g =0;


            if (users.size()>0)
            {
                for(User s:users)
                {
                    String imie =s.getName();
                    String nazwisko = s.getSurname();
                    imiona[g] = imie;
                    nazwiska[g] = nazwisko;
                    g++;
                }
                Log.d(TAG, "napelnijTabele: napelniono.");
            }else
            {
                zrobToast("Dodaj przynajmniej dwa słówka aby rozpocząć");
            }

//--------losuj slowko---------------
        int los;
        Log.d(TAG, "losujSlowko: called. "+users.size());

        if (users.size()>1) {
            for (int i=0; i<users.size(); i++)
            {
                if (ostatnieLosy[i] == 1)
                {
                    test++;
                    Log.d(TAG, "losujSlowko: wylosowanych: "+test+" wielkość: "+users.size());
                }
            }

            if (test == users.size())
            {
                Log.d(TAG, "losujSlowko: finish");
                pokazToast("koniec");
            }

            else {
                do {
                    los = random.nextInt(users.size());
                }
                while (ostatnieLosy[los] == 1);

                test = 0;
                ostatnieLosy[los] = 1;
                imie = imiona[los];
                nazwisko = nazwiska[los];
                Log.d(TAG, "losujSlowko: wylosowano");
            }
        }else if (!kateg.equals("Wybierz Kategorię"))
        {
            zrobToast("dodaj przynajmniej dwa słówka aby rozpocząć");
            MainActivity.fragmentManager.popBackStack();
            Log.d(TAG, "losujSlowko: za malo slowek");
        }
        else
        {
            zrobToast("Najpierw wybierz kategorię!");
            MainActivity.fragmentManager.popBackStack();
            Log.d(TAG, "losujSlowko: nie wybrano kategorii");
        }
    }

    private void pokazToast(String kolor)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_flscr_kartkowka,
                (ViewGroup) view.findViewById(R.id.custom_toast_contain));
        TextView text = layout.findViewById(R.id.text);

        switch (kolor) {
            case "red": {
                text.setText(R.string.zle);
                layout.setBackgroundResource(R.color.kartkowkaRed);
                TextView text2 = layout.findViewById(R.id.text2);
                text2.setText(String.format("%s - %s", imie, nazwisko));
                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
                break;
            }
            case "green": {
                text.setText(R.string.dobrze);
                layout.setBackgroundResource(R.color.kartkowkaGreen);
                TextView text2 = layout.findViewById(R.id.text2);
                text2.setText(String.format("%s - %s", imie, nazwisko));
                Toast toast = new Toast(getContext());
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setView(layout);
                toast.show();
                break;
            }
            case "koniec": {
                MainActivity
                    .fragmentManager
                    .beginTransaction()
                    .replace(
                        R.id.stefan, new KoniecKartkowkiFragment(
                            String.format(Locale.getDefault(), "Dobrych:\n%d", dobrych),
                            String.format(Locale.getDefault(), "złych:\n%d", zlych),
                            kateg, vi
                        )
                    )
                    .addToBackStack(null)
                    .commit();
                break;
            }
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
