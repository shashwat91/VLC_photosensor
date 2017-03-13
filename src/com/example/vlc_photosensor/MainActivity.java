package com.example.vlc_photosensor;

import java.util.List;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity 
{
    private Button btnStart;
    private TextView HWgetText;
    private static final String TAG = "SensorApi";
    
    //SensorEvent sensorValue;
    SensorManager senMgr;
    Sensor sensor;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "Application started");
        HWgetText = (TextView)findViewById(R.id.textViewName);
        
        List<Sensor> sensorLight;
        senMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorLight = senMgr.getSensorList(Sensor.TYPE_LIGHT);
       
        /*btnStart = (Button) findViewById(R.id.btnStart);
        
        btnStart.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View arg0) 
            {
            	Log.d(TAG, "Initialising sensors");
                start();
                Log.d(TAG, "sensors initialised");
            }
         });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    }

    @Override
    protected void onPause()
    { 
    	super.onPause();
    }
    
    public void start()
    {}
}
