package com.donnfelker.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donnfelker.fragmentsdemo.events.NextStepEvent;
import com.donnfelker.fragmentsdemo.events.PrevStepEvent;
import com.squareup.otto.Bus;

import javax.inject.Inject;


/**
 * A placeholder fragment containing a simple view.
 */
public class Step2Fragment extends Fragment {

    @Inject Bus bus;
    private TextView addressOne;

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

        addressOne = (TextView) getView().findViewById(R.id.address_one);

        if(getArguments() != null && getArguments().containsKey(Constants.Extras.KEY_ADDRESS_ONE)) {
            addressOne.setText(getArguments().getString(Constants.Extras.KEY_ADDRESS_ONE));
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.next, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.next:
                final NextStepEvent next = new NextStepEvent(addressOne.getText());
                bus.post(next);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;

    }
}

