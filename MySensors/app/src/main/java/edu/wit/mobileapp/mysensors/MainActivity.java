package edu.wit.mobileapp.mysensors;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mgr;
    private Sensor light;
    private TextView text;
    private StringBuilder msg = new StringBuilder(2048);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mgr = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        light = mgr.getDefaultSensor(Sensor.TYPE_LIGHT);
        text = findViewById(R.id.text);
    }

    @Override
    protected void onResume(){
        mgr.registerListener(this, light, SensorManager.SENSOR_DELAY_NORMAL);
        super.onResume();
    }

    @Override
    protected void onPause(){
       mgr.unregisterListener(this,light);
        super.onPause();
    }

    @Override
    public void onSensorChanged(SensorEvent event){
        msg.append("Got a sensor event: " + event.values[0] + " SI lux units\n");
        text.setText(msg);
        text.invalidate();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){
        msg.insert(0, sensor.getName() + " accuracy changed: " + accuracy +
                (accuracy ==1?" (LOW":(accuracy==2?" (MED":" (HIGH)")) + "\n");
        text.setText(msg);
        text.invalidate();
    }

}
