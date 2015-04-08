package local.dam_2015.sismo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import local.dam_2015.sismo.fragments.SettingsFragment;
import local.dam_2015.sismo.managers.AlarmaManager;

/**
 * Created by cursomovil on 26/03/15.
 */
public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager()
                .beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        Log.d("EARTHQUAKE", "PrEfErEnces: " + sharedPreferences + "Y el Key: " + key);

        if(key.equals(getString(R.string.PREF_UPDATE_INTERVAL))){
            // Start/Stop auto refresh
            AlarmaManager.updateAlarm(this, Integer.parseInt(prefs.getString(key, "0")));
        }else if(key.equals(getString(R.string.PREF_SWITCH))){
            // Change auto refresh interval
            if(sharedPreferences.getBoolean(key,true)){
                AlarmaManager.setAlarm(this, R.string.PREF_UPDATE_INTERVAL);
            }else{
                AlarmaManager.cancelAlarm(this);
            }
        }else if(key.equals(getString(R.string.PREF_MAGNITUDE))){
            // Update earthquake listView
            double minMag = Double.parseDouble(sharedPreferences.getString(key, "0"));
        }
    }

    public void setAlarm(boolean isSet) {


    }
}
