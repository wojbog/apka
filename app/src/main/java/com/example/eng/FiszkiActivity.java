package com.example.eng;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FiszkiActivity extends AppCompatActivity {

    //TODO: Animacja odwracania

    boolean odwrocone=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiszki);

        final TextView kartaTV = findViewById(R.id.kartaTV);

        kartaTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!odwrocone) {
                    kartaTV.setText(R.string.jakies_tlumaczenie);
                    kartaTV.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fiszkishapereversed));
                    odwrocone=true;
                }else if (odwrocone)
                {
                    kartaTV.setText(R.string.jakie_slowko);
                    kartaTV.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.fiszkishape));
                    odwrocone=false;
                }
            }
        });


    }

}
