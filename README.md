# Liner HeartManager

![](https://img.shields.io/badge/Version-1.0-blue)
![](https://img.shields.io/badge/Issues-0-green)

## Usage
```java
LinerHeartRateManager linerHeartRateManager = new LinerHeartRateManager(this); 

linerHeartRateManager.setLinerHeartRateListener(new LinerHeartRateListener() {
                @Override
                public void onStartMeasure() {
                  // Called when measure started
                }

                @Override
                public void onPauseMeasure() {
                  // Called when measure paused
                }

                @Override
                public void onResumeMeasure() {
                  // Called when measure resumed
                }

                @Override
                public void onStopMeasure() {
                  // Called when measure stopped
                }

                @Override
                public void onSensorContactFailed() {
                  // Called when sensor no contact with body
                }

                @Override
                public void onMeasureTimeStamp(int hour, int minute, boolean is24H) {
                  // Called when sensor get first correct data
                }

                @Override
                public void onMeasurePulseChanged(int current, int min, int max) {
                  // Called when pulse values changed
                }
            });
    linerHeartRateManager.start();
```

Function available
- start();  // Start measure
- stop();  // Stop measure
- pause(); // Pause current measure
- resume(); // Resume currect measure
- switch(); // Switch measure state
- setLinerHeartRateListener(); // Set listener
