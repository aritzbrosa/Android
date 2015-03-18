package local.dam_2015.calculadora;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class Calculadora extends ActionBarActivity {

    private ArrayList<String> hist;
    private ArrayAdapter<String> aa;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private Button btn5;
    private Button btn6;
    private Button btn7;
    private Button btn8;
    private Button btn9;
    private Button btn0;
    private Button btndot;
    private Button btndiv;
    private Button btnmulti;
    private Button btnadd;
    private Button btnsubs;
    private Button btneq;
    private TextView display;
    private ListView historico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        hist = new ArrayList<String>();

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);
        btn3 = (Button) findViewById(R.id.button3);
        btn4 = (Button) findViewById(R.id.button4);
        btn5 = (Button) findViewById(R.id.button5);
        btn6 = (Button) findViewById(R.id.button6);
        btn7 = (Button) findViewById(R.id.button7);
        btn8 = (Button) findViewById(R.id.button8);
        btn9 = (Button) findViewById(R.id.button9);
        btn0 = (Button) findViewById(R.id.button0);
        btndot = (Button) findViewById(R.id.buttondot);
        btndiv = (Button) findViewById(R.id.buttondiv);
        btnmulti = (Button) findViewById(R.id.buttonmulti);
        btnadd = (Button) findViewById(R.id.buttonadd);
        btnsubs = (Button) findViewById(R.id.buttonsubs);
        btneq = (Button) findViewById(R.id.buttoneq);

        display = (TextView) findViewById(R.id.display);
        historico = (ListView) findViewById(R.id.historico);

        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hist);

    }1


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calculadora, menu);
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
