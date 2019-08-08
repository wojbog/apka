package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
  public static FragmentManager fragmentManager;
  public static MyappDatabase baza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        baza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanych").allowMainThreadQueries().build();

        if(findViewById(R.id.stefan)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.stefan,new HomeFragment()).commit();
        }

    }
}
