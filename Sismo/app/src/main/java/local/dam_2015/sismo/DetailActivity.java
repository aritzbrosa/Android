package local.dam_2015.sismo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import local.dam_2015.sismo.fragments.EarthQListFragment;


public class DetailActivity extends ActionBarActivity {

    private TextView lblloc;
    private TextView lbllat;
    private TextView lbllong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent detailIntent = getIntent();

        lblloc = (TextView) findViewById(R.id.loc);
        lbllat = (TextView) findViewById(R.id.lat);
        lbllong = (TextView) findViewById(R.id.longit);

        String a = detailIntent.getStringExtra(EarthQListFragment.EARTHQUAKE);
        lblloc.setText(detailIntent.getStringExtra(EarthQListFragment.EARTHQUAKE));
        lbllat.setText(detailIntent.getStringExtra(EarthQListFragment.EARTHQUAKE));
        lbllong.setText(detailIntent.getStringExtra(EarthQListFragment.EARTHQUAKE));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
