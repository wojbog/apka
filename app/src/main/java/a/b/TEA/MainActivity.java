package a.b.TEA;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
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
        //TODO: Żołądki poprawić

        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        baza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanych").allowMainThreadQueries().build();
        bazaKategorii = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychKategorii").allowMainThreadQueries().build();

        //---------reklamy-----------
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("ED43A8DEDBA2148151ACA37D39F3416F").addTestDevice("531DB919ED797626DB5AE53A00FFBB9F").build();
        mAdView.loadAd(adRequest);
        final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1001;

        //---------powiadomienia-----------
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            String id = "_channel_01";
            int importance = NotificationManager.IMPORTANCE_LOW;
            NotificationChannel mChannel = new NotificationChannel(id, "notification", importance);
            mChannel.enableLights(true);
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            Notification notification = new Notification.Builder(getApplicationContext(), id)
                    .setSmallIcon(R.drawable.logopng)
                    .setContentTitle("Hej ty! Nie zapomniałeś uczyć się słówek?")
                    .setContentText("Zobacz nową metodę uczenia się słówek i przetraw ich więcej codziennie")
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .build();

            NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel);
                mNotificationManager.notify(PRIMARY_FOREGROUND_NOTIF_SERVICE_ID, notification);
            }
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
