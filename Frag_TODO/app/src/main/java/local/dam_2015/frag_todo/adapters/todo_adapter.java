package local.dam_2015.frag_todo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import local.dam_2015.frag_todo.R;
import local.dam_2015.frag_todo.model.todo;

/**
 * Created by cursomovil on 23/03/15.
 */
public class todo_adapter extends ArrayAdapter<todo> {

    private int resource;

    public todo_adapter(Context context, int resource, List<todo> objects) {
        super(context, resource, objects);

        this.resource = resource;
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

        todo item = getItem(position);

        TextView task = (TextView) layout.findViewById(R.id.taskid);
        TextView created = (TextView) layout.findViewById(R.id.timestamp);

        SimpleDateFormat sdt = new SimpleDateFormat("yyyy/MM/dd");

        task.setText(item.getTask());
        created.setText(sdt.format(item.getCreated()));

        return layout;
    }
}
