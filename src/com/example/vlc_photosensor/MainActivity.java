package com.example.vlc_photosensor;

import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private Button btnStart;
    private TextView HWgetText;
    private static final String TAG = "MApi";
    private Context mcontext;
    private static final int PERIOD=1;
	private Handler handler;
	private boolean initialising = true;
	//SensorEvent sensorValue;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    private List<Sensor> sensorLight;
    public float sensorValue=0;
    
	private final Runnable processSensors = new Runnable() 
	{
		@Override
		public void run()
		{
			if(initialising)
			{
				initSensor();
				initialising = false;
			}
			
			System.out.println("Periodic data:"+sensorValue);
	        handler.postDelayed(this, PERIOD);
	    }
	};
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mcontext = getApplicationContext();  
        Log.d(TAG, "Application started");
        HWgetText = (TextView)findViewById(R.id.textViewName);
        btnStart = (Button) findViewById(R.id.btnStart);
        handler = new Handler();
        
        btnStart.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View arg0) 
            {
            	//t1.run();
            	processSensors.run();
            }
         });
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    	if(mSensor != null)
    	{
    		handler.post(processSensors);
    	}
    	
    }

    @Override
    protected void onPause()
    { 
    	if(mSensor != null)
    	{
    		handler.removeCallbacks(processSensors);
    		mSensorManager.unregisterListener(this);
    		initialising = true;
    	}
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
        	//mSensorManager.registerListener(this, mSensor, Sensor.SENSOR_DELAY_FASTEST);
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
		sensorValue = event.values[0];
	}
	
}
