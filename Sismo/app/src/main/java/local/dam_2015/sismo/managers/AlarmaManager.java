package local.dam_2015.sismo.managers;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import local.dam_2015.sismo.SettingsActivity;
import local.dam_2015.sismo.services.DownloadEQService;

/**
 * Created by cursomovil on 1/04/15.
 */
public class AlarmaManager {

    public static void setAlarm(Context context, int interval){
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        int alarmType = AlarmManager.RTC;

        long LengthOfWait = interval * 1000;

        Intent intentToFire = new Intent(context, DownloadEQService.class);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, intentToFire, 0);

        alarm.setInexactRepeating(alarmType, LengthOfWait, LengthOfWait, alarmIntent);

    }


    public static void cancelAlarm(Context context) {

        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        Intent intentToFire = new Intent(context, DownloadEQService.class);
        PendingIntent alarmIntent = PendingIntent.getService(context, 0, intentToFire, 0);

        alarm.cancel(alarmIntent);
    }

    public static void updateAlarm(Context context, int interval) {
        setAlarm(context, interval);
    }
}
