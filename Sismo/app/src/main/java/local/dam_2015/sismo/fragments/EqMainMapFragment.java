package local.dam_2015.sismo.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import local.dam_2015.sismo.R;
import local.dam_2015.sismo.abstracts.AbstractMapFragment;
import local.dam_2015.sismo.model.EarthQ;


public class EqMainMapFragment extends AbstractMapFragment {

    private LatLng point;

    public EqMainMapFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getEQData();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    public void setEQ(List<EarthQ> list) {
        this.eqs = list;
    }

    @Override
    public void getEQData() {
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
        eqs=db.getAllByMagnitude(Integer.parseInt(prefs.getString(getString(R.string.PREF_MAGNITUDE), "0")));
    }

    @Override
    protected void showMap() {

        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if(eqs.size()>0){
            for(EarthQ eq: eqs){
                point = new LatLng(eq.getCoordinate().getLgtd(),eq.getCoordinate().getLttd());

                MarkerOptions marker = new MarkerOptions().position(point)
                        .title(eq.getPlace())
                        .snippet(eq.getURL());

                map.addMarker(marker);

                builder.include(marker.getPosition());

            }
            LatLngBounds bounds = builder.build();
            CameraUpdate camUpd = CameraUpdateFactory.newLatLngBounds(bounds,0);

            map.moveCamera(camUpd);
        }else{
            Log.d("EARTHQUAKE","No hay terremotos que mostrar");
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        //Implementar un for para mostrar todos los markers.
        /*String url = eqs.get(0).getURL();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);*/
    }
}
