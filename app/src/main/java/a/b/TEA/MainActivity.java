package a.b.TEA;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;
import androidx.work.Constraints;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
  public static FragmentManager fragmentManager;
  public static MyappDatabase baza,bazaKategorii,bazaZolodkowa,bazaZolodkowaZastepcza;

  String TAG = "LOGMainActivity";
    private long backPressedTime = 0;
//    PeriodicWorkRequest uploadWorkRequest;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setTheme(R.style.AppTheme);
        //TODO: Ustawienia, oceń nas, motywy
        //TODO: jedna baza danych ale dodana kolumna na nastepny dzien
        //TODO: plama od kawy

        final String PREFS_NAME = "MyPrefsFile";
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        /*if (settings.getBoolean("my_theme", true)) {
            setTheme(R.style.AppTheme_niebieski);
        }else {
            setTheme(R.style.AppTheme);
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();
        baza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanych").allowMainThreadQueries().build();
        bazaKategorii = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychKategorii").allowMainThreadQueries().build();
        bazaZolodkowa = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychZolodkowa").allowMainThreadQueries().build();
        bazaZolodkowaZastepcza = Room.databaseBuilder(getApplicationContext(),MyappDatabase.class,"BazaDanychZolodkowaZastepcza").allowMainThreadQueries().build();
//        Constraints constraints = new Constraints.Builder()
//                .setRequiresCharging(true)
//                .build();
//        uploadWorkRequest = new PeriodicWorkRequest.Builder(UploadWorker.class, 15, TimeUnit.MINUTES).setConstraints(constraints).build();


        if (bazaKategorii.myDao().loadUserByKategoria("Data").size()==0) {
            User user = new User();
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat1 = new SimpleDateFormat("dd");
            DateFormat dateFormat2 = new SimpleDateFormat("hh");
            String dzien = dateFormat1.format(date);
            String godzina = dateFormat2.format(date);
            user.setName(godzina);
            user.setSurname(dzien);
            user.setCategory("Data");
            MainActivity.bazaKategorii.myDao().addUser(user);
        }

        Reminder(7200);
//        WorkManager.getInstance(getApplicationContext()).enqueue(uploadWorkRequest);

        //---------reklamy-----------
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        AdView mAdView;
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("2A1965EA634A02D70CBC9CF1070DCF26").addTestDevice("531DB919ED797626DB5AE53A00FFBB9F").build();
        mAdView.loadAd(adRequest);

        //first time?
        final String PREFS_NAMEa = "MyPrefsFile";
        SharedPreferences settingsa = getSharedPreferences(PREFS_NAMEa, 0);
        if (settingsa.getBoolean("my_first_time", true)) {

            startActivity(new Intent(MainActivity.this, StartoweActivity.class));
            Toast.makeText(getApplicationContext(), "Dziękujemy za pobranie aplikacji!", Toast.LENGTH_LONG).show();
            settings.edit().putBoolean("my_first_time", false).commit();
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
            fragmentManager.beginTransaction().replace(R.id.stefan,new HomeFragment()).commit();
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

    public void Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }

    //---------powiadomienia----------
    public class RemindTask extends TimerTask {
        public void run() {
            final int PRIMARY_FOREGROUND_NOTIF_SERVICE_ID = 1001;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                String id = "_channel_01";
                int importance = NotificationManager.IMPORTANCE_LOW;
                NotificationChannel mChannel = new NotificationChannel(id, "Przypominenie o nauce", importance);
                mChannel.enableLights(true);
                Intent intent = new Intent(getApplication(), MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

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
            timer.cancel(); //Wyłączamy taska
        }
    }

//    public class UploadWorker extends Worker {
//        public UploadWorker(
//                @NonNull Context context,
//                @NonNull WorkerParameters params) {
//            super(context, params);
//        }
//
//        @Override
//        public Result doWork() {
//
//            return Result.success();
//        }
//    }
}
