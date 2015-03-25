package local.dam_2015.sismo.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

import local.dam_2015.sismo.R;

import local.dam_2015.sismo.Tasks.DownloadEQTasks;
import local.dam_2015.sismo.adapters.AdapterMolon;
import local.dam_2015.sismo.fragments.dummy.DummyContent;
import local.dam_2015.sismo.model.Coord;
import local.dam_2015.sismo.model.EarthQ;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class EarthQListFragment extends ListFragment implements DownloadEQTasks.addEQInterface {

    private ArrayList<EarthQ> ListaTerremotos;
    public static final String EARTHQUAKE = "EARTHQUAKE";
    private ArrayAdapter<EarthQ> aa;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListaTerremotos = new ArrayList<>();

        DownloadEQTasks task = new DownloadEQTasks(this);
        task.execute(getString(R.string.eq_url));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);

        aa = new AdapterMolon(getActivity(), R.layout.list_layout, ListaTerremotos);
        setListAdapter(aa);

        return layout;
    }


    @Override
    public void addEQ(EarthQ earthquake) {
        ListaTerremotos.add(0,earthquake);
        aa.notifyDataSetChanged();
    }
}
