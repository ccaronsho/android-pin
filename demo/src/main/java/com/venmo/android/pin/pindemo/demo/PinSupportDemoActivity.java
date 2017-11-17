package com.venmo.android.pin.pindemo.demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.venmo.android.pin.PinListener;
import com.venmo.android.pin.PinSupportFragment;
import com.venmo.android.pin.util.PinHelper;

public class PinSupportDemoActivity extends FragmentActivity implements PinListener {

    private Fragment toShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_demo);
        toShow = PinHelper.hasDefaultPinSaved(this) ?
                PinSupportFragment.newInstanceForVerification() :
                PinSupportFragment.newInstanceForCreation();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.root, toShow, toShow.getClass().getSimpleName())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pin_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            PinHelper.resetDefaultSavedPin(this);
            recreate();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCancelled() {
        Toast.makeText(this, "Cancelled!", Toast.LENGTH_SHORT).show();
        if (toShow != null) {
//            getSupportFragmentManager().beginTransaction().remove(toShow).commit();
            finish();
        }
    }

    @Override
    public void onValidated() {
        Toast.makeText(this, "Validated Passcode!", Toast.LENGTH_SHORT).show();
        recreate();
    }

    @Override
    public void onPinCreated() {
        Toast.makeText(this, "Created Passcode!", Toast.LENGTH_SHORT).show();
        recreate();
    }

}
