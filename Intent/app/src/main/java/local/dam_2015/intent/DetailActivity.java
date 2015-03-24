package local.dam_2015.intent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity {

    public static final String MSG = "msg";
    private Button btnSend;
    private Button btnCancel;
    private EditText textEdit;
    private TextView viewText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btnSend = (Button) findViewById(R.id.send_detail);
        btnCancel = (Button) findViewById(R.id.close);
        viewText = (TextView) findViewById(R.id.PhotoTitle);
        textEdit = (EditText) findViewById(R.id.TextDescription);

        takeIntent();

        addEventListeners();
    }

    private void takeIntent() {
        Intent intento = getIntent();

        String code = intento.getStringExtra(PhotoActivity.TEXT);
        viewText.setText(code);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

    private void addEventListeners(){
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String descr = textEdit.getText().toString();;

                if(descr.length() > 0){
                    Intent echoIntent = new Intent();
                    echoIntent.putExtra(MSG, descr);

                    setResult(RESULT_OK, echoIntent);

                    finish();
                } else {
                    Toast toast = Toast.makeText(DetailActivity.this, getResources().getString(R.string.no_text),Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL,0,0 );
                    toast.show();
                }

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }


}
