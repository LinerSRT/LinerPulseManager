package com.kwart.heartratetest.HeartRate;

public interface LinerHeartRateListener {
    void onStartMeasure();
    void onPauseMeasure();
    void onResumeMeasure();
    void onStopMeasure();

    void onSensorContactFailed();

    void onMeasureTimeStamp(int hour, int minute, boolean is24H);
    void onMeasurePulseChanged(int current, int min, int max);
}
