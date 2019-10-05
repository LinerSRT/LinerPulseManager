package com.kwart.heartratetest.HeartRate;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import java.util.Calendar;

public class LinerHeartRateManager implements SensorEventListener {
    private LinerHeartRateListener linerHeartRateListener;
    private SensorManager sensorManager;
    private Sensor heartRateSensor;
    private Context context;
    
    private boolean measureRunning= false;
    private boolean measurePaused = false;
    private boolean measureTimeSaved = false;
    
    private int minPulse, maxPulse;
    
    public void setLinerHeartRateListener(LinerHeartRateListener linerHeartRateListener){
        this.linerHeartRateListener = linerHeartRateListener;
    }
    
    public LinerHeartRateManager(Context context){
        this.context = context;
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        assert sensorManager != null;
        heartRateSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_RATE);
    }
    
    public void start(){
        if(!measureRunning){
            minPulse = 0;
            maxPulse = 0;
            measureRunning = true;
            measureTimeSaved = false;
            sensorManager.registerListener(this, heartRateSensor, SensorManager.SENSOR_DELAY_NORMAL);
            linerHeartRateListener.onStartMeasure();
        }
    }
    
    public void stop(){
        if(measureRunning){
            measureTimeSaved = false;
            measureRunning = false;
            sensorManager.unregisterListener(this);
            linerHeartRateListener.onStopMeasure();
        }
    }

    public void pause(){
        if(measureRunning) {
            this.measurePaused = true;
            linerHeartRateListener.onPauseMeasure();
        }
    }

    public void resume(){
        if(measurePaused) {
            this.measurePaused = false;
            linerHeartRateListener.onResumeMeasure();
        }
    }

    public void switchMeasure(){
        if(measureRunning){
            stop();
        } else {
            start();
        }
    }
    
    private void saveMeasureTime() {
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        boolean is24time_format = android.text.format.DateFormat.is24HourFormat(context);
        linerHeartRateListener.onMeasureTimeStamp(hour, minute, is24time_format);
        measureTimeSaved = true;
    }
    
    private void processSensorData(SensorEvent sensorEvent){
        if(SensorManager.SENSOR_STATUS_NO_CONTACT == sensorEvent.accuracy){
            linerHeartRateListener.onSensorContactFailed();
        } else {
            if(sensorEvent.sensor == heartRateSensor){
                int sensorValue = (int) sensorEvent.values[0];
                if(sensorValue != 0){
                    if(!measureTimeSaved){
                        saveMeasureTime();
                    }
                    
                    if(minPulse == 0){
                        minPulse = sensorValue;
                    } else if(sensorValue < minPulse){
                        minPulse = sensorValue;
                    }
                    if(maxPulse == 0){
                        maxPulse = sensorValue;
                    } else if (sensorValue > maxPulse){
                        maxPulse = sensorValue;
                    }
                    linerHeartRateListener.onMeasurePulseChanged(sensorValue, minPulse, maxPulse);
                }
            }
        }        
    }
    
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if(!measurePaused){
            processSensorData(sensorEvent);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    
    
}
