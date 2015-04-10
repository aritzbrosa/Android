package local.dam_2015.sismo;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import local.dam_2015.sismo.fragments.EarthQListFragment;

/**
 * Created by cursomovil on 10/04/15.
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener{
    private Fragment fragment;
    private Activity activity;
    private Class<T> fragmentClass;
    private int fragmentContainer;

    public TabListener(Activity activity, int frameContainer, Class<T> earthQListFragmentClass) {
        this.activity = activity;
        this.fragmentContainer = frameContainer;
        this.fragmentClass = earthQListFragmentClass;
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(fragment == null){
            String fragmentName = fragmentClass.getName();
            fragment = Fragment.instantiate(activity,fragmentName);
            ft.add(fragmentContainer, fragment,fragmentName);
        }else{
            ft.attach(fragment);
        }
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(fragment != null){
            ft.detach(fragment);
        }
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(fragment != null){
            ft.attach(fragment);
        }
    }
}
