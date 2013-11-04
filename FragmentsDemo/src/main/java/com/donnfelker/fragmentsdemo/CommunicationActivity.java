package com.donnfelker.fragmentsdemo;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class CommunicationActivity extends ActionBarActivity implements RedButtonListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.communication_activity);

        if (savedInstanceState == null) {
            CommunicationFragment cf = new CommunicationFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, cf)
                    .commit();

            cf.setRedButtonListener(this);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.communication, menu);
        return true;
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


    @Override
    public void onRedButtonClick() {
        Toast.makeText(this, R.string.red_button_clicked, Toast.LENGTH_SHORT).show();
    }
}
