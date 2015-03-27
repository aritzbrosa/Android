package local.dam_2015.sismo.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import local.dam_2015.sismo.R;
import local.dam_2015.sismo.model.EarthQ;

/**
 * Created by cursomovil on 25/03/15.
 */
public class AdapterMolon extends ArrayAdapter<EarthQ> {

    private int resource;

    public AdapterMolon(Context context, int list_layout, List<EarthQ> listaTerremotos) {
        super(context, list_layout,listaTerremotos);

        this.resource = list_layout;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout layout = (LinearLayout) convertView;

        if(convertView == null){
            layout = new LinearLayout(getContext());

            LayoutInflater li;
            String inflater = Context.LAYOUT_INFLATER_SERVICE;

            li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, layout, true);
        }else{
            layout = (LinearLayout) convertView;
        }

        EarthQ item = getItem(position);

        TextView mag = (TextView) layout.findViewById(R.id.magnitude);
        TextView location = (TextView) layout.findViewById(R.id.place);
        TextView time = (TextView) layout.findViewById(R.id.date);


        SimpleDateFormat sdt = new SimpleDateFormat("yyyy/MM/dd");

        NumberFormat formatter = new DecimalFormat("#0.0");
        setMagColor(item, layout);
        mag.setText(String.valueOf(formatter.format(item.getMagnitude())));
        String a = item.getPlace().substring(item.getPlace().indexOf(",") + 2);
        location.setText(a);
        time.setText(sdt.format(item.getDate()));

        return layout;
    }

    private void setMagColor(EarthQ item, LinearLayout layout){
        Color color;
        int r=47483647,g=125,b=125;

        NumberFormat formatter = new DecimalFormat("#0");
        String num = formatter.format(item.getMagnitude());
        TextView mag = (TextView) layout.findViewById(R.id.magnitude);

        if(num=="1"){
            r=50;
        }else if(num=="2"){
            r=83647;
        }else if(num=="3"){
            r=-2147483648;
        }else if(num=="4"){
            r=125;
        }else if(num=="5"){
            r=150;
        }else if(num=="6"){
            r=175;
        }else if(num=="7"){
            r=200;
        }else if(num=="8"){
            r=225;
        }else if(num=="9"){
            r=255;
        }else if(num=="0"){
            r=2147483647;
        }

        color = new Color();
        //color.getColor
        mag.setBackgroundColor(r);
    }
}
