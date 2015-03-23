package local.dam_2015.frag_todo;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import local.dam_2015.frag_todo.fragments.TodoListFragment;
import local.dam_2015.frag_todo.model.todo;


public class DetailActivity extends ActionBarActivity {

    private todo todo;
    private TextView lblTask;
    private TextView lblCReated;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent detailIntent = getIntent();
        todo = detailIntent.getParcelableExtra(TodoListFragment.TODOS_ITEM);

        lblTask = (TextView) findViewById(R.id.lbltask);
        lblCReated = (TextView) findViewById(R.id.lblCreated);

        populateView();
    }

    private void populateView(){
        lblTask.setText(todo.getTask());
        lblCReated.setText(todo.getCreatedFormatted());
    }
}
