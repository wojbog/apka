package a.b.TEA;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;


public class FiszkiWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, int[] appWidgetIds) {

        User user = MainActivity.baza.myDao().loadLos();
        String imie = user.getName();
        String nazwisko = user.getSurname();
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.fiszki_widget);

        views.setTextViewText(R.id.kartaWTV, imie+"\n-=-=-=-=-=-=-=-\n"+nazwisko);

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

