package local.dam_2015.sismo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import local.dam_2015.sismo.Tasks.DownloadEQTasks;
import local.dam_2015.sismo.fragments.SettingsFragment;
import local.dam_2015.sismo.managers.AlarmaManager;
import local.dam_2015.sismo.services.DownloadEQService;


public class SismoActivity extends ActionBarActivity implements DownloadEQTasks.addEQInterface{

    public static final int PREFS_ACTIVITY = 1 ;
    private final String EARTHQUAKE_PREFS = "EARTHQUAKE_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sismo);

        checkToSetAlarm();
    }

    private void checkToSetAlarm() {
        //DownloadEQTasks task = new DownloadEQTasks(this,this);
        //task.execute(getString(R.string.eq_url));

        //Intent download = new Intent(this, DownloadEQService.class);
        //startService(download);
        String KEY = "LAUNCHED_BEFORE";

        SharedPreferences prefs = getSharedPreferences(EARTHQUAKE_PREFS, Activity.MODE_PRIVATE);
        if(!prefs.getBoolean(KEY, false)){
            AlarmaManager.setAlarm(this,R.integer.default_interval * 60);      // El valor en mins.
            prefs.edit().putBoolean(KEY,true).apply();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sismo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent prefsIntent = new Intent(this, SettingsActivity.class);
            startActivityForResult(prefsIntent, PREFS_ACTIVITY);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void notifyTotal(int total) {

    }
}
