package com.donnfelker.fragmentsdemo;


import android.app.Application;

/**
 * Default Application for the app.
 */
public class FragmentsDemoApplication extends Application {

    private static FragmentsDemoApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;

        Injector.init(new DemoModule(), this);
    }

}
