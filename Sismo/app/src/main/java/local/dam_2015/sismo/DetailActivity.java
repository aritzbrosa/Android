package local.dam_2015.sismo;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import local.dam_2015.sismo.database.EarthQuakeDB;
import local.dam_2015.sismo.fragments.EarthQListFragment;
import local.dam_2015.sismo.fragments.EqMapFragment;
import local.dam_2015.sismo.model.EarthQ;


public class DetailActivity extends ActionBarActivity {

    public static final String MAP = "MAP" ;
    private TextView lblloc;
    private TextView lbllat;
    private TextView lbllong;
    private TextView lblmag;
    private TextView lbldate;
    private TextView lblplace;
    private Button btnMap;
    private String id;
    private EqMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EarthQ eq;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        EarthQuakeDB db = new EarthQuakeDB(this);
        mapFragment = (EqMapFragment) getFragmentManager().findFragmentById(R.id.fragment2);

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        Intent detailIntent = getIntent();

        lblloc = (TextView) findViewById(R.id.loc);
        lbllat = (TextView) findViewById(R.id.lat);
        lbllong = (TextView) findViewById(R.id.longit);
        lblmag = (TextView) findViewById(R.id.mag);
        lbldate = (TextView) findViewById(R.id.date);
        lblplace = (TextView) findViewById(R.id.place);
//        btnMap = (Button) findViewById(R.id.mapa);

        id = detailIntent.getStringExtra(EarthQListFragment.EARTHQUAKE);
        eq = db.selectIdQuery(id);

        lblloc.setText(String.valueOf(eq.getCoordinate().getLgtd()));
        lbllat.setText(String.valueOf(eq.getCoordinate().getLttd()));
        lbllong.setText(String.valueOf(eq.getCoordinate().getDepth()));
        lblmag.setText(String.valueOf(eq.getMagnitude()));
        lbldate.setText(sdt.format(eq.getDate()));
        lblplace.setText(eq.getPlace().substring(eq.getPlace().indexOf(",") + 2));

//        addEventListener();
        showMap(eq);
    }

    private void showMap(EarthQ eq) {
        List<EarthQ> list = new ArrayList<EarthQ>();
        list.add(eq);
        mapFragment.setEQ(list);
    }

    private void addEventListener() {
        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(DetailActivity.this, MapsActivity.class);
                mapIntent.putExtra("id", id);
                startActivity(mapIntent);
            }
        });
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
            Intent prefsIntent = new Intent(this, SettingsActivity.class);
            startActivityForResult(prefsIntent, SismoActivity.PREFS_ACTIVITY);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
