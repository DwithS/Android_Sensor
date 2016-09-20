package com.example.hh.android_sensor;

import android.app.Activity;
import android.hardware.SensorEvent;
import android.os.Bundle;

public class MainActivity extends Activity{

    private SensorActivity mySensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySensor = new SensorActivity(this);
    }
    protected void onResume(){
        super.onResume();
        mySensor.register();
    }

    @Override
    protected void onPause(){
        super.onPause();
        mySensor.unregister();
    }
}


