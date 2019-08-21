package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

//        LayoutInflater inflater = getLayoutInflater();
//        View layout = inflater.inflate(R.layout.custom_toast_flscr_kartkowka,
//                (ViewGroup) findViewById(R.id.custom_toast_contain));
//        TextView text = layout.findViewById(R.id.text);
//        text.setText("WITAMY W APLIKACJI");
//        layout.setBackgroundResource(R.color.kartkowkaKoniec);
//        Toast toast = new Toast(getApplicationContext());
//        toast.setGravity(Gravity.CENTER, 0, 0);
//        toast.setDuration(Toast.LENGTH_LONG);
//        toast.setView(layout);
//        toast.show();

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
