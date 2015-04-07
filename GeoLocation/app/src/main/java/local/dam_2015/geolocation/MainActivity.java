package local.dam_2015.geolocation;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import local.dam_2015.geolocation.listeners.LocationListener;


public class MainActivity extends Activity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private TextView lbllat;
    private TextView lbllong;
    private TextView lblalt;
    private TextView lblspeed;
    private GoogleApiClient mGAPIClient;
    private boolean conectado=false;
    private LocationRequest mLocationRequest;

    private String provider;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lbllat = (TextView) findViewById(R.id.lbllat);
        lbllong = (TextView) findViewById(R.id.lbllong);
        lblalt = (TextView) findViewById(R.id.lblalt);
        lblspeed = (TextView) findViewById(R.id.lblspeed);

        configure();
        connect();
        //obtainProvider();
    }

    private void connect() {
        mGAPIClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }

    private void configure() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    public void addLocation(Location location) {
        lbllat.setText(String.valueOf(location.getLatitude()));
        lbllong.setText(String.valueOf(location.getLongitude()));
        lblalt.setText(String.valueOf(location.getAltitude()));
        lblspeed.setText(String.valueOf(location.getSpeed()));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(conectado){
            LocationServices.FusedLocationApi.requestLocationUpdates(mGAPIClient,mLocationRequest,this);
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        conectado = true;
    }

    @Override
    public void onConnectionSuspended(int i) {
        conectado = false;
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        conectado = false;
    }
}
