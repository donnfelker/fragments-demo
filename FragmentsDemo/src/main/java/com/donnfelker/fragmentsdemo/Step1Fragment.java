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
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * A placeholder fragment containing a simple view.
 */
public class Step1Fragment extends Fragment {

    @Inject Bus bus;
    private TextView firstName;
    private TextView lastName;

    public Step1Fragment() {
        Injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_one, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        Injector.inject(this);

        firstName = (TextView) getView().findViewById(R.id.first_name);
        lastName = (TextView) getView().findViewById(R.id.last_name);

        if(getArguments() != null) {
            if(getArguments().containsKey(Constants.Extras.KEY_FIRST_NAME)) {
                firstName.setText(getArguments().getString(Constants.Extras.KEY_FIRST_NAME));
            }
            if(getArguments().containsKey(Constants.Extras.KEY_LAST_NAME)) {
                lastName.setText(getArguments().getString(Constants.Extras.KEY_LAST_NAME));
            }
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
                if(valid()) {
                    final NextStepEvent next = new NextStepEvent(firstName.getText(), lastName.getText());
                    bus.post(next);
                } else {
                    if(firstName.getText().length() == 0) {
                        firstName.setError(getString(R.string.error_first_name_required));
                    }
                    if(lastName.getText().length() == 0) {
                        lastName.setError(getString(R.string.error_last_name_required));
                    }
                }
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private boolean valid() {
        return (firstName.getText().length() > 0 && lastName.getText().length() > 0);
    }
}
