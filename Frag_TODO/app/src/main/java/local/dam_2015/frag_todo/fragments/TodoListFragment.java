package local.dam_2015.frag_todo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

import local.dam_2015.frag_todo.DetailActivity;
import local.dam_2015.frag_todo.R;
import local.dam_2015.frag_todo.adapters.todo_adapter;
import local.dam_2015.frag_todo.model.todo;

/**
 * A fragment representing a list of Items.
 * <p/>
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnFragmentInteractionListener}
 * interface.
 */
public class TodoListFragment extends ListFragment implements InputFragment.setItemListener {

    private final String TODOS_KEY = "todos_key";
    public static final String TODOS_ITEM = "todos_item";
    private ArrayList<todo> todos;
    private ArrayAdapter<todo> aa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = super.onCreateView(inflater, container, savedInstanceState);

        todos = new ArrayList<>();
        aa = new todo_adapter(getActivity(), R.layout.todo_list_item, todos);

        if(savedInstanceState!=null){
            ArrayList<todo> tmp = savedInstanceState.getParcelableArrayList(TODOS_KEY);

            todos.addAll(tmp);
        }

        setListAdapter(aa);

        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(TODOS_KEY, todos);

        outState.putParcelableArrayList(TODOS_KEY, todos);
        super.onSaveInstanceState(outState);
    }



    @Override
    public void addTodo(todo todo) {
        todos.add(0, todo);
        aa.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        todo todo = todos.get(position);

        Intent detailIntent = new Intent(getActivity(), DetailActivity.class);
        detailIntent.putExtra(TODOS_ITEM, todo);

        startActivity(detailIntent);
    }
}
