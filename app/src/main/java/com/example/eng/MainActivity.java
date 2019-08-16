package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
  public static FragmentManager fragmentManager;
  public static MyappDatabase baza,bazaKategorii;

  String TAG = "LOGMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        baza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanych").allowMainThreadQueries().build();
        bazaKategorii = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychKategorii").allowMainThreadQueries().build();
        Log.d(TAG, "onCreate: zbudowano bazy");

        if(findViewById(R.id.stefan)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.stefan,new HomeFragment()).commit();
            Log.d(TAG, "onCreate: zmiana na home fragment");
        }

    }
}
