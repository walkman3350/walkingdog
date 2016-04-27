package com.example.walkerliu.walkingdog;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener {
    private SharedPreferences spObjectDog;
    private SensorManager sensorManager;
    //private Sensor mStepCounterSensor;
    private static final String walkingDog = "WALKINGDOG";
    boolean running;
    private int initStep;
    private int sensorValues = -1;
    TextView xWay;
    TextView yWay;
    TextView zWay;
    TextView countStep;
    Dog dog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xWay=(TextView)findViewById(R.id.textViewX);
        yWay=(TextView)findViewById(R.id.textViewY);
        zWay=(TextView)findViewById(R.id.textViewZ);
        countStep=(TextView)findViewById(R.id.textViewStep);
        //sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        //sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        //mStepCounterSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        spObjectDog = getSharedPreferences(walkingDog,0);

        initStep = spObjectDog.getInt("lastStep",0);
        if(initStep != 0){
            xWay.setText("init Step not 0");
        }else {
            xWay.setText("init Step is  0");
        }
    }

    @Override
    protected void  onResume() {
        super.onResume();
        running = true;
        Sensor sensorStep = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        if (sensorStep!=null) {
            sensorManager.registerListener(this, sensorStep, SensorManager.SENSOR_DELAY_FASTEST);
        } else {
            countStep.setText("sensor error!!");
        }

    }

    @Override
    protected void onPause(){
        super.onPause();
        running = false;
        SharedPreferences.Editor editor = spObjectDog.edit();
        editor.putInt("lastStep",sensorValues);
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void button_start(View view) {
        Toast toast = Toast.makeText(MainActivity.this, "dello", Toast.LENGTH_LONG);
        toast.show();
        initStep = sensorValues;
        countStep.setText("0");

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        //if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
        Sensor sensor = event.sensor;
        float[] values = event.values;
        int answerValue;

        if (values.length > 0){
            sensorValues = (int) values[0];
        }
        if(sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            //float x = event.values[0];
            //float y = event.values[1];
            //float z = event.values[2];

            //xWay.setText("X: " + x);
            //yWay.setText("Y: " + y);
            //zWay.setText("Z: " + z);
            answerValue = sensorValues -initStep;
            countStep.setText(": " + answerValue);
        }

        //}
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}

