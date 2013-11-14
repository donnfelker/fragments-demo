package com.donnfelker.fragmentsdemo;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class TabDemoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_demo);


        addTabs();

    }

    private void addTabs() {

        final ActionBar actionbar = getSupportActionBar();
        actionbar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        final ActionBar.Tab tab1 =
                actionbar
                    .newTab()
                    .setText(R.string.tab1)
                    .setTabListener(new TabListener<Tab1>(this, "tab1", Tab1.class));
        actionbar.addTab(tab1);

        final ActionBar.Tab tab2 =
                actionbar
                        .newTab()
                        .setText(R.string.tab2)
                        .setTabListener(new TabListener<Tab2>(this, "tab2", Tab2.class));
        actionbar.addTab(tab2);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tab_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public static class Tab1 extends Fragment {

        public Tab1() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_one, container, false);
            return rootView;
        }
    }

    public static class Tab2 extends Fragment {

        public Tab2() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_tab_two, container, false);
            return rootView;
        }
    }

}
