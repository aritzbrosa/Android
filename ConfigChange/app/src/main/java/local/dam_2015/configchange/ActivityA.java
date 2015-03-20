package local.dam_2015.configchange;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class ActivityA extends ActionBarActivity {

    private final String DATA = "data";

    private String op = null;
    private Double result = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        Log.d("CHANGE","ActivityA onCreate()");

        if(savedInstanceState != null){
            String data = savedInstanceState.getString(DATA);
            Log.d("CHANGE","ActivityA onCreate() saved data");
        }

        Button buttonOpenB = (Button) findViewById(R.id.buttonOpenB);
        Button buttonCloseA = (Button) findViewById(R.id.buttonClose);

        buttonOpenB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CHANGE","ActivityA onClick()");

                Intent openB = new Intent(ActivityA.this, ActivityB.class);
                startActivity(openB);

            }
        });

        buttonCloseA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("CHANGE","ActivityA close");

                finish();
            }
        });
    }

    private void restoreState(Bundle s){

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.d("CHANGE","ActivityA onSaveInstanceState");

        outState.putString(DATA, "datos");

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d("CHANGE","ActivityA onRestoreInstanceState()");

        this.op = savedInstanceState.getString("op");
        this.result = savedInstanceState.getDouble("result");

        Log.d("CHANGE","ActivityA onRestoreInstanceState() saved data");

    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d("CHANGE", "ActivityA onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d("CHANGE","ActivityA onResume()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        Log.d("CHANGE","ActivityA onStart()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d("CHANGE","ActivityA onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("CHANGE","ActivityA onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("CHANGE","ActivityA onDestroy()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
