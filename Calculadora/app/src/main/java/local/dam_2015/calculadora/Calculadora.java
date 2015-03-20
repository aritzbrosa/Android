package local.dam_2015.calculadora;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private Button btndelete;
    private TextView display;
    private ListView historico;
    private View.OnClickListener numListener;
    private View.OnClickListener opListener;
    private View.OnClickListener delListener;

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
        btndelete = (Button) (findViewById(R.id.delete));

        display = (TextView) findViewById(R.id.display);
        historico = (ListView) findViewById(R.id.historico);

        aa = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, hist);

        historico.setAdapter(aa);

        addEventListeners();

    }

    private void addEventListeners()
    {
        numListener = new View.OnClickListener()
        {

            public void onClick(View v)
            {
                Button b = (Button) v;
                if(display.getText()=="*"||display.getText()=="+"||display.getText()=="-"||display.getText()=="/"||display.getText()=="=")
                {
                    display.setText(" ");
                }
                display.setText(display.getText() + b.getText().toString());
            }

        };

        opListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Button b = (Button) v;
                display.setText(b.getText().toString());
            }
        };

        delListener = new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //String str = "o";
                Button b = (Button) v;
                display.setText(" ");
            }
        };

        btn0.setOnClickListener(numListener);
        btn1.setOnClickListener(numListener);
        btn2.setOnClickListener(numListener);
        btn3.setOnClickListener(numListener);
        btn4.setOnClickListener(numListener);
        btn5.setOnClickListener(numListener);
        btn6.setOnClickListener(numListener);
        btn7.setOnClickListener(numListener);
        btn8.setOnClickListener(numListener);
        btn9.setOnClickListener(numListener);
        btndot.setOnClickListener(numListener);

        btnadd.setOnClickListener(opListener);
        btnsubs.setOnClickListener(opListener);
        btnmulti.setOnClickListener(opListener);
        btndiv.setOnClickListener(opListener);
        btneq.setOnClickListener(opListener);

        btndelete.setOnClickListener(delListener);
    }


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
