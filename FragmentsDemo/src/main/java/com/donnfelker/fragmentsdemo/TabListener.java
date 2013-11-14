package com.donnfelker.fragmentsdemo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;


public class TabListener<T extends Fragment> implements ActionBar.TabListener{
    private Fragment mFragment;
    private final FragmentActivity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    public TabListener(FragmentActivity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        // previous Fragment management
        Fragment prevFragment;
        FragmentManager fm = mActivity.getSupportFragmentManager();
        prevFragment = fm.findFragmentByTag(mTag);
        if (prevFragment != null) {
            mFragment = prevFragment;
        } // \previous Fragment management

        // Check if the fragment is already initialized
        if (mFragment == null) {
            // If not, instantiate and add it to the activity
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        } else {
            // If it exists, simply attach it in order to show it
            ft.attach(mFragment);
        }

    }

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if (mFragment != null) {
            // Detach the fragment, because another one is being attached
            ft.detach(mFragment);
        }
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // User selected the already selected tab. Usually do nothing.
    }
}