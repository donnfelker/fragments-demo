package com.donnfelker.fragmentsdemo;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.donnfelker.fragmentsdemo.events.NextStepEvent;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

public class RegistrationWizard extends ActionBarActivity {

    private static final String CURRENT_STEP = "current_step";
    @Inject Bus bus;
    private int currentStep;
    private CharSequence firstName;
    private CharSequence lastName;
    private CharSequence address;

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

        switch (currentStep) {
            case 0:
                this.firstName = event.getFirstNameText();
                this.lastName = event.getLastNameText();
                break;
            case 1:
                this.address = event.getAddress();
                break;
        }

        currentStep++;
        loadStep();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
                .addToBackStack(null)
                .commit();
    }

    private void loadSecondStep() {

        final Step2Fragment step2 = new Step2Fragment();

        if(address != null && address.length() > 0) {
            final Bundle bundle = new Bundle();
            bundle.putString(Constants.Extras.KEY_ADDRESS_ONE, address.toString());
            step2.setArguments(bundle);
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, step2, Step2Fragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .addToBackStack(null)
                .commit();
    }

    private void loadFirstStep() {

        final Step1Fragment step1 = new Step1Fragment();

        if(firstName != null && firstName.length() > 0 && lastName != null && lastName.length() > 0) {
            final Bundle bundle = new Bundle();
            bundle.putString(Constants.Extras.KEY_FIRST_NAME, firstName.toString());
            bundle.putString(Constants.Extras.KEY_LAST_NAME, lastName.toString());
            step1.setArguments(bundle);
        }

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.container, step1, Step1Fragment.class.getName())
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                .commit();
    }

}
