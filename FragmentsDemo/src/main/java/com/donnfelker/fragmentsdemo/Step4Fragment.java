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
public class Step4Fragment extends Fragment {

    public Step4Fragment() {
        Injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.step_four, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Injector.inject(this);

        getView().findViewById(R.id.lets_go).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
    }

}

