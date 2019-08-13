package com.example.eng;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;
import java.util.Random;

public class KartkowkaActivity extends AppCompatActivity {

    Random random = new Random();
    final List<User> users = MainActivity.baza.myDao().getUsers();

    private int
            dobrych=0,
            zlych=0,
            lastLos=234432;
    private String[]
            imiona = new String[users.size()],
            nazwiska = new String[users.size()];
    private String
            imie="brak",
            nazwisko="brak",
            nazwiskoWpisane="",
            TAG = "katrkowka";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartkowka);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        napelnijStringi();
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

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    void sprawdz()
    {
        EditText tlumaczenieET = findViewById(R.id.tlumaczenieET);
        nazwiskoWpisane = tlumaczenieET.getText().toString();
        boolean ok = nazwiskoWpisane.matches(nazwisko);
        if (nazwiskoWpisane.matches("")) Toast.makeText(getApplicationContext(), "Wpisz tłumaczenie", Toast.LENGTH_SHORT).show();
        else if(ok)
        {
            Log.d(TAG, "sprawdz: dobre");
            dobrych++;
            Toast.makeText(this, "BOBRZE", Toast.LENGTH_SHORT).show();

            restart();
        }else
        {
            Log.d(TAG, "sprawdz: zle");
            zlych++;
            Toast.makeText(this, "ŹLE!!", Toast.LENGTH_SHORT).show();
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
        slowkoTV.setText(imie);
        dobrychTV.setText(String.format(Locale.getDefault(), "%d", dobrych));
        zlychTV.setText(String.format(Locale.getDefault(), "%d", zlych));
        tlumaczenieET.setHint("Tu wpisz tłumaczenie");
        tlumaczenieET.setText("");
        Log.d(TAG, "ustawWyglad: ustawiono");
    }

    void losujSlowko()
    {
        Log.d(TAG, "losujSlowko: called.");
        if (users.size()>0) {
            int los = random.nextInt(users.size());
            while (lastLos == los) {los = random.nextInt(users.size());}
            lastLos = los;
            imie = imiona[los];
            nazwisko = nazwiska[los];
            Log.d(TAG, "losujSlowko: wylosowano");
        }else
        {
            Toast.makeText(getApplicationContext(), "dodaj przynajmniej jedno słówko", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "losujSlowko: za malo slowek");
        }
    }

    private void napelnijStringi()
    {
        Log.d(TAG, "napelnijStringi: called.");
        int i=0;

        if (users.size()>0)
        {
            for(User s:users)
            {
                String imie =s.getName();
                String nazwisko = s.getSurname();
                imiona[i] = imie;
                nazwiska[i] = nazwisko;
                i++;
            }
            Log.d(TAG, "napelnijStringi: napelniono.");
        }else
        {
            Toast.makeText(getApplicationContext(), "Dodaj przynajmniej jedno słówko", Toast.LENGTH_SHORT).show();
        }
    }

    void napelnijStringiKategoriom(String kateg)
    {
        final List<User> usersByCategory = MainActivity.baza.myDao().loadUserByKategoria(kateg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        tu będą kategorie
        menu.add(1, 1, 1, "test");
        menu.add(1, 1, 1, "test2");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case 1:
                break;
            case 2:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
