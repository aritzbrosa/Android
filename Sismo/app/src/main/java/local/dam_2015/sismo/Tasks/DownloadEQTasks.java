package local.dam_2015.sismo.Tasks;

import android.os.AsyncTask;
import android.util.Log;

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

import local.dam_2015.sismo.R;
import local.dam_2015.sismo.fragments.EarthQListFragment;
import local.dam_2015.sismo.model.Coord;
import local.dam_2015.sismo.model.EarthQ;

/**
 * Created by cursomovil on 25/03/15.
 */
public class DownloadEQTasks extends AsyncTask<String,EarthQ, Integer> {

    private addEQInterface target;

    public interface addEQInterface{
        public void addEQ(EarthQ earthquake);
    }

    public  DownloadEQTasks(addEQInterface target){
        this.target = target;
    }

    @Override
    protected Integer doInBackground(String... urls) {
        if(urls.length > 0){
            updateEarthQs(urls[0]);
        }
        return null;
    }

    private void updateEarthQs(String eqFeed) {

        JSONObject json;
        try{
            URL url = new URL(eqFeed);

            URLConnection connection = url.openConnection();
            HttpURLConnection httpConnection = (HttpURLConnection) connection;

            int responseCode = httpConnection.getResponseCode();
            if(responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader streamReader = new BufferedReader(
                        new InputStreamReader(
                                httpConnection.getInputStream(), "UTF-8"));
                StringBuilder responseStrBuilder = new StringBuilder();

                String inputStr;
                while ((inputStr = streamReader.readLine()) != null)
                    responseStrBuilder.append(inputStr);

                json = new JSONObject(responseStrBuilder.toString());
                JSONArray earthquakes = json.getJSONArray("features");

                for (int i = earthquakes.length()-1; i >= 0; i--) {
                    processEarthQuakeTask(earthquakes.getJSONObject(i));
                }
            }

        }catch(MalformedURLException ex){
            ex.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(EarthQ... EQ) {
        super.onProgressUpdate(EQ);

        target.addEQ(EQ[0]);
    }

    private void processEarthQuakeTask(JSONObject jsonObject) {
        try {
            String id = jsonObject.getString("id");

            JSONArray JsonCoords = jsonObject.getJSONObject("geometry").getJSONArray("coordinates");

            Coord coords = new Coord(JsonCoords.getDouble(0), JsonCoords.getDouble(1), JsonCoords.getDouble(2));

            JSONObject properties = jsonObject.getJSONObject("properties");

            EarthQ EQ = new EarthQ();
            EQ.set_id(jsonObject.getString("id"));
            EQ.setPlace(properties.getString("place"));
            EQ.setMagnitude(properties.getDouble("mag"));
            EQ.setTime(properties.getLong("time"));
            EQ.setURL(properties.getString("url"));
            EQ.setCoordinate(coords);

            Log.d(EarthQListFragment.EARTHQUAKE, EQ.toString());

            publishProgress(EQ);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
