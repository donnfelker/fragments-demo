package com.donnfelker.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A placeholder fragment containing a simple view.
 */
public class CommunicationFragment extends Fragment {

    RedButtonListener redButtonListener;

    public CommunicationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.communication_fragment, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button interfaceBased = (Button) getView().findViewById(R.id.interface_button);
        interfaceBased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(redButtonListener != null) {
                    redButtonListener.onRedButtonClick();
                } else {
                    Log.d(getClass().getName(), "Hmmm, red button listener was null.");
                }
            }
        });
    }

    public void setRedButtonListener(RedButtonListener listener) {
        this.redButtonListener = listener;
    }
}