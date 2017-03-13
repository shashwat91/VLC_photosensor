package com.example.vlc_photosensor;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity 
{

    private Button btnStart;
    private TextView HWgetText;
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        HWgetText = (TextView)findViewById(R.id.textViewName);
        btnStart = (Button) findViewById(R.id.btnStart);
        
        btnStart.setOnClickListener(new View.OnClickListener() 
        {
            @Override
            public void onClick(View arg0) 
            {
                start();
            }
         });
        displayValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) 
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
   public void start()
   {
	  
   }
   
   public void displayValues()
   {
	   try 
	   {
		   HWgetText.setText(String.valueOf(134));
		   Thread.sleep(1000);
		   HWgetText.setText(String.valueOf(133));
		   Thread.sleep(1000);
		   HWgetText.setText(String.valueOf(132));
		   Thread.sleep(1000);
	   } 
	   catch (InterruptedException e) 
	   {
		e.printStackTrace();
	   }
   }
}
