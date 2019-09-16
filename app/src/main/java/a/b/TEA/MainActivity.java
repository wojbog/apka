package a.b.TEA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import android.os.Build;
import android.os.Bundle;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {
  public static FragmentManager fragmentManager;
  public static MyappDatabase baza,bazaKategorii;

  String TAG = "LOGMainActivity";
    private long backPressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //TODO: Ustawienia, oceń nas, motywy
        //TODO: zrobić czy ma być słówko na tłumaczenie czy odwrotnie
        //TODO: powiadomienia
        //TODO: Żołądki poprawić

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        baza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanych").allowMainThreadQueries().build();
        bazaKategorii = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychKategorii").allowMainThreadQueries().build();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ED43A8DEDBA2148151ACA37D39F3416F").addTestDevice("531DB919ED797626DB5AE53A00FFBB9F").build();
        mAdView.loadAd(adRequest);


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
            fragmentManager.beginTransaction().replace(R.id.stefan,new HomeFragment("Wybierz Kategorię", false)).commit();
        }

    }
//
//    @Override
//    public void onBackPressed() {
//        long t = System.currentTimeMillis();
//        if (t - backPressedTime > 2000) {
//            backPressedTime = t;
//            Toast.makeText(this, "Naciśnij jeszcze raz aby wyjść",
//                    Toast.LENGTH_SHORT).show();
//        } else {
//            super.onBackPressed();
//        }
//    }
}
