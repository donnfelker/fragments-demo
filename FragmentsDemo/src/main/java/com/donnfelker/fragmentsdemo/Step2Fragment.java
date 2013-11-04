package com.donnfelker.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.donnfelker.fragmentsdemo.events.NextStepEvent;
import com.donnfelker.fragmentsdemo.events.PrevStepEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;


/**
 * A placeholder fragment containing a simple view.
 */
public class Step2Fragment extends Fragment {

    @Inject Bus bus;

    public Step2Fragment() {
        Injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_two, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        Injector.inject(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.prev_next, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.prev:
                bus.post(new PrevStepEvent());
                break;
            case R.id.next:
                bus.post(new NextStepEvent());
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }
}

