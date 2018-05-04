package com.venmo.android.pin.pindemo.demo;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.venmo.android.pin.PinFragment;
import com.venmo.android.pin.PinListener;

public class PinLauncher extends Activity implements PinListener {

    private PinFragment pinFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_pin_launcher);

        findViewById(R.id.sdk_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pinFragment = PinFragment.newInstanceForCreation(PinLauncher.this);
                pinFragment.show(PinLauncher.this);
            }
        });
    }

    @Override
    public void onCancelled() {
        if (pinFragment != null) {
            pinFragment.dismiss();
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }

    @Override
    public void onValidated() {
        Toast.makeText(this, "Validated Passcode!", Toast.LENGTH_SHORT).show();
        onCancelled();
    }

    @Override
    public void onPinCreated() {
        Toast.makeText(this, "Created Passcode!", Toast.LENGTH_SHORT).show();
        onCancelled();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
