package local.dam_2015.frag_todo;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InputFragment extends Fragment {

    public interface setItemListener{
        public void addTodo(String todo);
    }

    private Button btnAdd;
    private TextView text;
    private setItemListener act;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            this.act = (setItemListener) activity;
        }
        catch(ClassCastException ex){
            throw new ClassCastException(activity.toString() + "must implement setItemListener interface.")
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_input, container, false);

        btnAdd = (Button) layout.findViewById(R.id.button);
        text = (TextView) layout.findViewById(R.id.todo);

        addEventListeners();

        return layout;
    }

    public void addEventListeners(){
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todo = text.getText().toString();

                act.addTodo(todo);
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }
}
