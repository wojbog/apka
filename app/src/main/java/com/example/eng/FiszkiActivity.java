package com.example.eng;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class FiszkiActivity extends AppCompatActivity {

    //TODO: Fiszki trzeba zrobić

    String TAG = "LOGFiszkiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: called.");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiszki);
    }
}
