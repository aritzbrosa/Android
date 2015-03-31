package local.dam_2015.sismo.fragments;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.app.ListFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import local.dam_2015.sismo.DetailActivity;
import local.dam_2015.sismo.R;

import local.dam_2015.sismo.Tasks.DownloadEQTasks;
import local.dam_2015.sismo.adapters.AdapterMolon;
import local.dam_2015.sismo.database.EarthQuakeDB;
import local.dam_2015.sismo.fragments.dummy.DummyContent;
import local.dam_2015.sismo.model.Coord;
import local.dam_2015.sismo.model.EarthQ;


public class EarthQListFragment extends ListFragment implements DownloadEQTasks.addEQInterface {

    private List<EarthQ> ListaTerremotos;
    public static final String EARTHQUAKE = "EARTHQUAKE";
    private ArrayAdapter<EarthQ> aa;
    private SharedPreferences prefs;
    private EarthQuakeDB db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListaTerremotos = new ArrayList<>();
        db = new EarthQuakeDB(getActivity());

        prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

    }

    @Override
    public void onResume() {
        super.onResume();

        ListaTerremotos.clear();
        ListaTerremotos.addAll(db.getAllByMagnitude(Integer.parseInt(prefs.getString(getString(R.string.PREF_MAGNITUDE), "0"))));

        aa.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);

        aa = new AdapterMolon(getActivity(), R.layout.list_layout, ListaTerremotos);
        setListAdapter(aa);

        return layout;
    }


    @Override
    public void notifyTotal(int total) {
        String msg = getString(R.string.num_eq, total);

        Toast t = Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT);
        t.show();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        EarthQ eq = ListaTerremotos.get(position);

        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(EARTHQUAKE, eq.get_id());

        startActivity(detailIntent);
    }
}
