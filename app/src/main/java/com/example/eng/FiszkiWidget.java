package com.example.eng;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import java.util.List;
import java.util.Random;

public class FiszkiWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int[] appWidgetIds) {

        List<User> users = MainActivity.baza.myDao().getUsers();

        String[]
                imiona = new String[users.size()],
                nazwiska = new String[users.size()];
        int g =0;

        for(User s:users)
        {
            String imie =s.getName();
            String nazwisko = s.getSurname();
            imiona[g] = imie;
            nazwiska[g] = nazwisko;
            g++;
        }
        Random random = new Random();
        int los = random.nextInt(users.size());

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fiszki_widget);
        views.setTextViewText(R.id.kartaWTV, imiona[los]+"\n------\n"+nazwiska[los]);

        Intent intent = new Intent(context, FiszkiWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.kartaWTV, pendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId, appWidgetIds);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

