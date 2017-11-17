package com.venmo.android.pin;

public interface PinListener {
    void onCancelled();
    void onValidated();
    void onPinCreated();
}

