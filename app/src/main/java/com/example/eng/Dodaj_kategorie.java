package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

public class Dodaj_kategorie extends AppCompatActivity {

    public static FragmentManager fragmentManager;
    String TAG="Dodaj_kategorie";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_kategorie);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.zbiornik)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.zbiornik,new AddCategoryFragment()).commit();
            Log.d(TAG, "onCreate: zmiana na home fragment");
        }
    }
}
