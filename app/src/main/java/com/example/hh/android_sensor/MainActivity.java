package com.example.hh.android_sensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {


    TextView textGyroX, textGyroY, textGyroZ;
    TextView textAcclX, textAcclY, textAcclZ;

    int accelValueX, accelValueY, accelValueZ;
    int gyroX, gyroY, gyroZ;

    SensorManager mSensorManager;
    Sensor gyroscope;
    Sensor accSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        gyroscope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        accSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        textGyroX = (TextView) findViewById(R.id.GyroXText);
        textGyroY = (TextView) findViewById(R.id.GyroYText);
        textGyroZ = (TextView) findViewById(R.id.GyroZText);

        textAcclX = (TextView) findViewById(R.id.AccelXText);
        textAcclY = (TextView) findViewById(R.id.AccelYText);
        textAcclZ = (TextView) findViewById(R.id.AccelZText);

    }

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
    }

    // 주기 설명
    // SENSOR_DELAY_UI 갱신에 필요한 정도 주기
    // SENSOR_DELAY_NORMAL 화면 방향 전환 등의 일상적인  주기
    // SENSOR_DELAY_GAME 게임에 적합한 주기
    // SENSOR_DELAY_FASTEST 최대한의 빠른 주기


    //리스너 등록
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, gyroscope,SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(this, accSensor,SensorManager.SENSOR_DELAY_FASTEST);
    }

    //리스너 해제
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
