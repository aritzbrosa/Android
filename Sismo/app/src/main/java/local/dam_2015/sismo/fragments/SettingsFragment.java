package local.dam_2015.sismo.fragments;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import local.dam_2015.sismo.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SettingsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends PreferenceFragment
        implements SharedPreferences.OnSharedPreferenceChangeListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        prefs.registerOnSharedPreferenceChangeListener(this);

        addPreferencesFromResource(R.xml.userpreferences);
    }



    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //TODO: Checa las preferencias compartidas y sus claves
        //y cambia la interfaz de usuario o comportamiento adecuadamente.

        //String newValue = "No cambios";

        Log.d("EARTHQUAKE", "PrEfErEnces: " + sharedPreferences + "Y el Key: " + key);

        /*if(key.equals(getString(R.string.PREF_UPDATE_INTERNAL))){
            // Start/Stop auto refresh
        }else if(key.equals(getString(R.string.PREF_SWITCH))){
            // Change auto refresh interval
        }else if(key.equals(getString(R.string.PREF_MAGNITUDE))){
            // Update earthquake listView
            double minMag = Double.parseDouble(sharedPreferences.getString(key, "0"));
        }*/
    }
}
