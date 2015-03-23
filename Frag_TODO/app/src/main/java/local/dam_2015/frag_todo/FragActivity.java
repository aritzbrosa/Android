package local.dam_2015.frag_todo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import local.dam_2015.frag_todo.fragments.InputFragment;
import local.dam_2015.frag_todo.model.todo;


public class FragActivity extends ActionBarActivity implements InputFragment.setItemListener{

    String TODO = "todo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);
    }

    @Override
    public void addTodo(todo todo) {
        //Log.d(TODO);
        InputFragment.setItemListener listFragment;

        try{
            listFragment = (InputFragment.setItemListener) getFragmentManager().findFragmentById(R.id.fragment2);
        } catch(ClassCastException ex) {
            throw new ClassCastException(this.toString() + "must implement setItemListener()");
        }
        listFragment.addTodo(todo);
    }
}
