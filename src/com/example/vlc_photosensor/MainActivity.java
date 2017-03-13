package com.example.vlc_photosensor;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private Button btnStart;
    private TextView HWgetText;
    private static final String TAG = "MApi";
    private Context mcontext;
    
    //SensorEvent sensorValue;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private List<Sensor> sensorLight;
    
    Thread t1 = new Thread(new Runnable() {
        public void run() 
        {
        	Log.d(TAG, "Initialising sensors");
        	if(initSensor() == 0)
        		Log.d(TAG, "sensors initialised");
        	else
        		Log.d(TAG, "sensors didn't initialised properly");
        	
        	System.out.println(sensorLight);
        	System.out.println(mSensor);
        }
    });
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mcontext = getApplicationContext();  
        Log.d(TAG, "Application started");
        HWgetText = (TextView)findViewById(R.id.textViewName);
        btnStart = (Button) findViewById(R.id.btnStart);
        
        btnStart.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View arg0) 
            {
            	t1.run();
            }
         });
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    	if(mSensor != null)
    	mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause()
    { 
    	if(mSensor != null)
    	mSensorManager.unregisterListener(this);
    	super.onPause();
    }
    
    public int initSensor()
    {
    	mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = mSensorManager.getSensorList(Sensor.TYPE_LIGHT);
        if (sensorLight != null)
    	{
        	mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        	mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_FASTEST);
    		return 0;
    	}
        else
        	return 1;
    }

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy)
	{
		Log.e(TAG,"Sensor's accuracy changed, it might cause problems");
	}

	@Override
	public void onSensorChanged(SensorEvent event)
	{
		System.out.println("current value::" +  event.values[0]);
	}
}
