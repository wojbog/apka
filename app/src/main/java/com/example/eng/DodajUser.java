package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;

public class DodajUser extends AppCompatActivity {
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj_user);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.kontener_adduser)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            Log.d("Dodaj user","i teraz fragment");
            fragmentManager.beginTransaction().add(R.id.kontener_adduser,new AddUserFragment()).commit();
            Log.d("AddUserFragment","zrobione!");
        }
    }
}
