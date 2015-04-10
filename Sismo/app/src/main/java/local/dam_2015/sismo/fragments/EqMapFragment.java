package local.dam_2015.sismo.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import local.dam_2015.sismo.R;
import local.dam_2015.sismo.model.EarthQ;


public class EqMapFragment extends MapFragment implements GoogleMap.OnMapLoadedCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap map;
    private List<EarthQ> eqs;
    private LatLng point;


    public EqMapFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View layout = super.onCreateView(inflater,container,savedInstanceState);

        map = getMap();
        map.setOnMapLoadedCallback(this);
        map.setOnInfoWindowClickListener(this);

        return layout;
    }

    @Override
    public void onMapLoaded() {
        GoogleMap map = getMap();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if(eqs.size()>1){
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
        }else if(eqs.size()==1){
            EarthQ eq = eqs.get(0);
            point = new LatLng(eq.getCoordinate().getLgtd(),eq.getCoordinate().getLttd());
            CameraPosition camPos = new CameraPosition.Builder().target(point)
                    .zoom(3)
                    .tilt(89)
                    .bearing(18)
                    .build();

            CameraUpdate camUpd = CameraUpdateFactory.newCameraPosition(camPos);

            map.animateCamera(camUpd);
            map.addMarker(new MarkerOptions().position(point)
                    .title(eq.getPlace())
                    .snippet("Click to browse website"));
        }else{
            Log.d("EARTHQUAKE","No hay terremotos que mostrar");
        }


    }

    @Override
    public void onResume() {
        super.onResume();
    }


    public void setEQ(List<EarthQ> list) {
        this.eqs = list;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String url = eqs.get(0).getURL();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }
}
