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
        //String a = item.getPlace();
        location.setText(a);
        time.setText(sdt.format(item.getDate()));

        return layout;
    }

    private void setMagColor(EarthQ item, LinearLayout layout){
        Color color;
        int r=47483647,g=Integer.MAX_VALUE,b=Integer.MAX_VALUE;

        NumberFormat formatter = new DecimalFormat("#0");
        String num = formatter.format(item.getMagnitude());
        TextView mag = (TextView) layout.findViewById(R.id.magnitude);

        if(num.equals("1")){
            r=0;
        }else if(num.equals("2")){
            r=Integer.MAX_VALUE*2/10;
        }else if(num.equals("3")){
            r=Integer.MAX_VALUE*3/10;
        }else if(num.equals("4")){
            r=Integer.MAX_VALUE*4/10;
        }else if(num.equals("5")){
            r=Integer.MAX_VALUE*5/10;
        }else if(num.equals("6")){
            r=Integer.MAX_VALUE*6/10;
        }else if(num.equals("7")){
            r=Integer.MAX_VALUE*7/10;
        }else if(num.equals("8")){
            r=Integer.MAX_VALUE*8/10;
        }else if(num.equals("9")){
            r=Integer.MAX_VALUE*9/10;
        }else if(num.equals("0")){
            r=Integer.MAX_VALUE;
        }


        color = new Color();
        //color.getColor
        mag.setBackgroundColor(color.rgb(r,g,b));
    }
}
