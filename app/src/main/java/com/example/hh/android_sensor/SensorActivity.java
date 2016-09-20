package com.example.hh.android_sensor;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by HH on 2016-09-19.
 */
public class SensorActivity implements SensorEventListener {

    TextView textGyroX, textGyroY, textGyroZ;
    TextView textAcclX, textAcclY, textAcclZ;
    TextView textMagX, textMagY, textMagZ;

    int accelValueX, accelValueY, accelValueZ;
    int gyroX, gyroY, gyroZ;
    int magX, magY, magZ;

    SensorManager mSensorManager;
    ActivityManager myAct;
    Sensor gyroscope;
    Sensor accSensor;
    Sensor magSensor;

    public SensorActivity(Context context){
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        //myAct = context.getSystemService(Context.ACTIVITY_SERVICE);
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }
    @Override
    /*protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textGyroX = (TextView) findViewById(R.id.GyroXText);
        textGyroY = (TextView) findViewById(R.id.GyroYText);
        textGyroZ = (TextView) findViewById(R.id.GyroZText);

        textAcclX = (TextView) findViewById(R.id.AccelXText);
        textAcclY = (TextView) findViewById(R.id.AccelYText);
        textAcclZ = (TextView) findViewById(R.id.AccelZText);

        textMagX = (TextView) findViewById(R.id.MagXText);
        textMagY = (TextView) findViewById(R.id.MagYText);
        textMagZ = (TextView) findViewById(R.id.MagZText);
    }*/

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    public void onSensorChanged(SensorEvent event){
        Sensor sensor = event.sensor;

        if(sensor.getType()==Sensor.TYPE_GYROSCOPE){
            gyroX = Math.round(event.values[0]*1000);
            gyroY = Math.round(event.values[1]*1000);
            gyroZ = Math.round(event.values[2]*1000);
            textGyroX.setText( "Gyro X = " + gyroX );
            textGyroY.setText( "Gyro Y = " + gyroY );
            textGyroZ.setText( "Gyro Z = " + gyroZ);
        }
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            accelValueX = Math.round(event.values[0]*1000);
            accelValueY = Math.round(event.values[1]*1000);
            accelValueZ = Math.round(event.values[2]*1000);
            textAcclX.setText( "Aceel X = " + accelValueX);
            textAcclY.setText( "Accel Y = " + accelValueY);
            textAcclZ.setText( "Accel Z = " + accelValueZ);
        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            magX = Math.round(event.values[0]*1000);
            magY = Math.round(event.values[1]*1000);
            magZ = Math.round(event.values[2]*1000);
            textMagX.setText( "Mag X = " + magX);
            textMagY.setText( "Mag Y = " + magY);
            textMagZ.setText( "Mag Z = " + magZ);
        }
    }

    // 주기 설명
    // SENSOR_DELAY_UI 갱신에 필요한 정도 주기
    // SENSOR_DELAY_NORMAL 화면 방향 전환 등의 일상적인  주기
    // SENSOR_DELAY_GAME 게임에 적합한 주기
    // SENSOR_DELAY_FASTEST 최대한의 빠른 주기



    public void register(){
        mSensorManager.registerListener(this, gyroscope,SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, magSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }
    public void unregister(){
        mSensorManager.unregisterListener(this);
    }

}
