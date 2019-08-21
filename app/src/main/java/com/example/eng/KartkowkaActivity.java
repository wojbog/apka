package com.example.eng;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KartkowkaActivity extends AppCompatActivity {

    //TODO:

    Random random = new Random();
    final List<User> users = MainActivity.baza.myDao().getUsers();

    private int
            dobrych=0,
            zlych=0;
    private String[]
            imiona = new String[users.size()],
            nazwiska = new String[users.size()];
    private String
            imie="brak",
            nazwisko="brak",
            TAG = "LOGKartkowkaActivity";
    private int[] ostatnieLosy;
    private int test=0;
    private boolean odwrotnie=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartkowka);

        napelnijTabele();
        losujSlowko();
        ustawWyglad();

        Button bn_sprawdz = findViewById(R.id.bn_sprawdz);
        bn_sprawdz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: click");
                sprawdz();
            }
        });

        final Button odw = findViewById(R.id.odw);
        odw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (odwrotnie) odwrotnie=false;
                else if (!odwrotnie) odwrotnie=true;
                ustawWyglad();
                odw.setVisibility(View.INVISIBLE);
            }
        });

    }

    void sprawdz()
    {
        Log.d(TAG, "sprawdz: called.");
        EditText tlumaczenieET = findViewById(R.id.tlumaczenieET);
        String nazwiskoWpisane = tlumaczenieET.getText().toString().trim();
        boolean ok=false;
        if (odwrotnie) ok = nazwiskoWpisane.matches(imie);
        else if (!odwrotnie) ok = nazwiskoWpisane.matches(nazwisko);

        if (nazwiskoWpisane.matches("")) zrobToast("Wpisz tłumaczenie");
        else if(ok)
        {
            Log.d(TAG, "sprawdz: dobre");
            dobrych++;
            pokazToast("green");

            Log.d(TAG, "sprawdz: pokazano toast");
            restart();
        }else
        {
            Log.d(TAG, "sprawdz: zle");
            zlych++;
            pokazToast("red");

            Log.d(TAG, "sprawdz: pokazano toast");
            restart();
        }
    }

    void restart()
    {
        Log.d(TAG, "restart: called.");
        losujSlowko();
        ustawWyglad();
        Log.d(TAG, "restart: restartowano");
    }

    void ustawWyglad()
    {
        TextView
                slowkoTV = findViewById(R.id.slowkoTV),
                dobrychTV = findViewById(R.id.dobrychTV),
                zlychTV = findViewById(R.id.zlychTV);
        EditText
                tlumaczenieET = findViewById(R.id.tlumaczenieET);

        Log.d(TAG, "ustawWyglad: called.");
        if (!odwrotnie) slowkoTV.setText(imie);
        else if (odwrotnie) slowkoTV.setText(nazwisko);
        dobrychTV.setText(String.format(Locale.getDefault(), "%d", dobrych));
        zlychTV.setText(String.format(Locale.getDefault(), "%d", zlych));
        tlumaczenieET.setHint("Tu wpisz tłumaczenie");
        tlumaczenieET.setText("");
        Log.d(TAG, "ustawWyglad: ustawiono");
    }

    void losujSlowko()
    {
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
//                if (odwrotnie)
//                {
//                imie = nazwiska[los];
//                nazwisko = imiona[los];
//                }else if (!odwrotnie)
//                {
                    imie = imiona[los];
                    nazwisko = nazwiska[los];
//                }
                Log.d(TAG, "losujSlowko: wylosowano");
            }
        }else
        {
            zrobToast("dodaj przynajmniej dwa słówka aby rozpocząć");
            finish();
            Log.d(TAG, "losujSlowko: za malo slowek");
        }
    }

    private void napelnijTabele()
    {
        Log.d(TAG, "napelnijTabele: called.");
        int g =0;

        ostatnieLosy = new int[users.size()];
        for (int i=0; i<users.size(); i++) {ostatnieLosy[i]=0;}

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
            Toast.makeText(getApplicationContext(), "DodajActivity przynajmniej jedno słówko", Toast.LENGTH_SHORT).show();
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        menu.add(1, 1, 1, "zmień kolejność");
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        //noinspection SimplifiableIfStatement
//        switch (id) {
//            case 1:
//                if (odwrotnie) odwrotnie=false;
//                else if (!odwrotnie) odwrotnie=true;
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    void pokazToast(String kolor)
    {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_flscr_kartkowka,
                (ViewGroup) findViewById(R.id.custom_toast_contain));
        TextView text = layout.findViewById(R.id.text);

        if (kolor.equals("red"))
        {
            text.setText(R.string.zle);
            layout.setBackgroundResource(R.color.kartkowkaRed);
            TextView text2 = layout.findViewById(R.id.text2);
            text2.setText(String.format("%s - %s", imie, nazwisko));
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }else
            if (kolor.equals("green"))
        {
            text.setText(R.string.dobrze);
            layout.setBackgroundResource(R.color.kartkowkaGreen);
            TextView text2 = layout.findViewById(R.id.text2);
            text2.setText(String.format("%s - %s", imie, nazwisko));
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }else
            if (kolor.equals("koniec"))
        {
            text.setText(R.string.koniec);
            layout.setBackgroundResource(R.color.kartkowkaKoniec);
            TextView text2 = layout.findViewById(R.id.text2);
            text2.setText(String.format("Dobrych: %s  \nZłych: %s", dobrych, zlych));
            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();
            finish();
        }

    }

    private void zrobToast(String coNapisac) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast,
                (ViewGroup) findViewById(R.id.custom_toast_container));
        TextView text = layout.findViewById(R.id.text);
        text.setText(coNapisac);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, -450);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

}
