package local.dam_2015.intent.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;

public class ConnectionReceiver extends BroadcastReceiver {

    private final String RECEIVER = "RECEIVER";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(RECEIVER, "ConnectionRceiver onReceive()");
        Log.d(RECEIVER, "ACTION: " + intent.getAction());

        if (intent.getAction() == Intent.ACTION_AIRPLANE_MODE_CHANGED){
            //  Do smth.
        } else if (intent.getAction() == ConnectivityManager.CONNECTIVITY_ACTION){

        }
    }
}
