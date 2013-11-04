package com.donnfelker.fragmentsdemo;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                FragmentsDemoApplication.class,
                CommunicationActivity.class,
                CommunicationFragment.class
        }
)
public class DemoModule {

    @Provides
    @Singleton
    public Bus providesBus() {
        return new Bus();
    }
}
