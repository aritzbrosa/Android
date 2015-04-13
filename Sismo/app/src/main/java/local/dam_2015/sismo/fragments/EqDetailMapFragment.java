package local.dam_2015.sismo.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

import local.dam_2015.sismo.abstracts.AbstractMapFragment;
import local.dam_2015.sismo.model.EarthQ;


public class EqDetailMapFragment extends AbstractMapFragment {

    private LatLng point;
    private String id;


    public EqDetailMapFragment() {
        // Required empty public constructor
    }

    public void setEQ(List<EarthQ> list) {
        this.eqs = list;
    }

    @Override
    public void getEQData() {
        id = getActivity().getIntent().getStringExtra(EarthQListFragment.EARTHQUAKE);

        EarthQ eq = db.selectIdQuery(id);
        eqs = new ArrayList<EarthQ>();
        eqs.add(eq);
    }

    @Override
    protected void showMap() {
        map = getMap();
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        if(eqs.size()==1){
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
            Log.d("EARTHQUAKE","Solo se debe mostrar un marker en este modo.");
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        String url = eqs.get(0).getURL();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }


}
