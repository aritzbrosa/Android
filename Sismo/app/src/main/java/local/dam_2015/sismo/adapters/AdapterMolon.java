package local.dam_2015.sismo.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

        mag.setText(String.valueOf(item.getMagnitude()));
        location.setText(item.getPlace());
        time.setText(sdt.format(item.getDate()));

        return layout;
    }
}
