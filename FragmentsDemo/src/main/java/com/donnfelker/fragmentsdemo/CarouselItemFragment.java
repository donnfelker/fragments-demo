package com.donnfelker.fragmentsdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CarouselItemFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_carousel_item, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final TextView sectionName = (TextView) getView().findViewById(R.id.section_name);

        if(getArguments() != null && getArguments().containsKey(Constants.Extras.KEY_SECTION_NAME)) {
            final String value = getArguments().getString(Constants.Extras.KEY_SECTION_NAME);
            sectionName.setText(value);
        }


    }
}