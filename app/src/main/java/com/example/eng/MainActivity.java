package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
  public static FragmentManager fragmentManager;
  public static MyappDatabase baza,bazaKategorii;

  String TAG = "LOGMainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: called.");
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        baza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanych").allowMainThreadQueries().build();
        bazaKategorii = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychKategorii").allowMainThreadQueries().build();
        Log.d(TAG, "onCreate: zbudowano bazy");

        if (Build.VERSION.SDK_INT>Build.VERSION_CODES.LOLLIPOP){
            getWindow().setNavigationBarColor(getResources().getColor(R.color.secondaryDarkColor));
            getWindow().setStatusBarColor(getResources().getColor(R.color.secondaryDarkColor));
        }

        if (Build.VERSION.SDK_INT>28)
        {
            getWindow().setNavigationBarDividerColor(getResources().getColor(R.color.secondaryDarkColor));
        }

        if(findViewById(R.id.stefan)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.stefan,new HomeFragment("Wybierz")).commit();
            Log.d(TAG, "onCreate: zmiana na home fragment");
        }

    }
}
