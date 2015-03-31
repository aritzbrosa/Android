package local.dam_2015.sismo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import local.dam_2015.sismo.Tasks.DownloadEQTasks;
import local.dam_2015.sismo.services.DownloadEQService;


public class SismoActivity extends ActionBarActivity implements DownloadEQTasks.addEQInterface{

    public static final int PREFS_ACTIVITY = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sismo);
        DownloadEQService service = new DownloadEQService();
        service.setAlarm();
        downloadEQs();
    }

    private void downloadEQs() {
        //DownloadEQTasks task = new DownloadEQTasks(this,this);
        //task.execute(getString(R.string.eq_url));

        Intent download = new Intent(this, DownloadEQService.class);
        startService(download);
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
