package local.dam_2015.sismo.abstracts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.Marker;

import java.util.ArrayList;
import java.util.List;

import local.dam_2015.sismo.R;
import local.dam_2015.sismo.database.EarthQuakeDB;
import local.dam_2015.sismo.model.EarthQ;

/**
 * Created by cursomovil on 13/04/15.
 */
public abstract class AbstractMapFragment extends MapFragment implements GoogleMap.OnInfoWindowClickListener, GoogleMap.OnMapLoadedCallback {

    protected Activity context;
    protected EarthQuakeDB db;
    protected List<EarthQ> eqs;
    protected GoogleMap map;
    protected SharedPreferences prefs;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();
        getDBConnection();
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        eqs = new ArrayList<>();
    }

    public void getDBConnection(){
        db = new EarthQuakeDB(context);
    }

    protected abstract void getEQData();
    protected abstract void showMap();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layout = super.onCreateView(inflater,container,savedInstanceState);

        map = getMap();
        map.setOnMapLoadedCallback(this);
        map.setOnInfoWindowClickListener(this);

        return layout;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String url = eqs.get(0).getURL();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    @Override
    public void onMapLoaded() {
        getEQData();
        showMap();
    }
}
