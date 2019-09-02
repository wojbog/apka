package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class DodajActivity extends AppCompatActivity {
    public static FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        FloatingActionButton fab = findViewById(R.id.fab);
        fragmentManager = getSupportFragmentManager();
        if(findViewById(R.id.kontener)!=null)
        {
            if(savedInstanceState!=null)
            {
                return;
            }
            fragmentManager.beginTransaction().add(R.id.kontener,new ReadCategoryFragment("kategoria")).commit();
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DodajActivity.this, Dodaj_kategorie.class);
                startActivity(intent);
            }
        });
    }
}
