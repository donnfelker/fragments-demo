package com.donnfelker.fragmentsdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.donnfelker.fragmentsdemo.events.NextStepEvent;
import com.donnfelker.fragmentsdemo.events.PrevStepEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class RegistrationWizard extends ActionBarActivity {

    private static final String CURRENT_STEP = "current_step";
    @Inject Bus bus;
    private int currentStep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_wizard);

        Injector.inject(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new Step1Fragment(), Step1Fragment.class.getName())
                    .commit();
        } else {
            currentStep = savedInstanceState.getInt(CURRENT_STEP, 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.registration_wizard, menu);
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_STEP, currentStep);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Subscribe
    public void onNextStepEvent(NextStepEvent event) {
        currentStep++;
        loadStep();
    }

    @Subscribe
    public void onPrevStepEvent(PrevStepEvent event) {
        currentStep--;
        loadStep();
    }

    private void loadStep() {
        switch(currentStep) {
            case 0:
                loadFirstStep();
                break;
            case 1:
                loadSecondStep();
                break;
            case 2:
                loadThirdStep();
                break;
            case 3:
                goToFourthStep();
                break;
        }
    }

    private void goToFourthStep() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Step4Fragment(), Step4Fragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

    private void loadThirdStep() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Step3Fragment(), Step3Fragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

    private void loadSecondStep() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Step2Fragment(), Step2Fragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

    private void loadFirstStep() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new Step1Fragment(), Step1Fragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }


}
