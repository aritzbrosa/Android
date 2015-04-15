package local.dam_2015.sismo.services;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.os.IBinder;
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
import local.dam_2015.sismo.SismoActivity;
import local.dam_2015.sismo.database.EarthQuakeDB;
import local.dam_2015.sismo.fragments.EarthQListFragment;
import local.dam_2015.sismo.model.Coord;
import local.dam_2015.sismo.model.EarthQ;

public class DownloadEQService extends Service {
    private EarthQuakeDB earthQDB;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        earthQDB = new EarthQuakeDB(this);
    }

    private int updateEarthQs(String eqFeed) {
        int count=0;

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
                count = earthquakes.length();
                for (int i = earthquakes.length()-1; i >= 0; i--) {
                    processEarthQuakeTask(earthquakes.getJSONObject(i));
                }
                sendNotifications(count);
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
        return count;
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

            earthQDB.insertEarthQuake(EQ);

            Log.d(EarthQListFragment.EARTHQUAKE, EQ.toString());

            //publishProgress(EQ);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);

        Thread t = new Thread(new Runnable(){
            @Override
            public void run() {
                updateEarthQs(getString(R.string.eq_url));
                Log.d("EARTHQUAKE", "Procesando Alarma +-+-+-+-+-+- GRSdfgSERgSDFGSDFGsdg·$·$·$·$");
            }
        });

        t.start();

        return Service.START_STICKY;
    }

    private void sendNotifications(int count) {

        Intent intentToFire = new Intent(this, SismoActivity.class);
        PendingIntent activityIntent = PendingIntent.getActivity(this,0,intentToFire,0);

        Notification.Builder builder = new Notification.Builder(DownloadEQService.this);

        builder.setSmallIcon(R.drawable.ic_launcher)
                .setContentTitle(getString(R.string.app_name))
                .setContentText(getString(R.string.count_earthquakes, count))
                .setWhen(System.currentTimeMillis())
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(activityIntent);

        Notification notification = builder.getNotification();

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        int NOTIFICATION_REF = 1;
        notificationManager.notify(NOTIFICATION_REF, notification);
    }


}
