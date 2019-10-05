# Liner HeartManager

![](https://img.shields.io/badge/Version-1.0-blue)
![](https://img.shields.io/badge/Issues-0-green)

##Usage
```java
LinerHeartRateManager linerHeartRateManager = new LinerHeartRateManager(this); linerHeartRateManager.setLinerHeartRateListener(new LinerHeartRateListener() {
                @Override
                public void onStartMeasure() {

                }

                @Override
                public void onPauseMeasure() {

                }

                @Override
                public void onResumeMeasure() {

                }

                @Override
                public void onStopMeasure() {

                }

                @Override
                public void onSensorContactFailed() {

                }

                @Override
                public void onMeasureTimeStamp(int hour, int minute, boolean is24H) {

                }

                @Override
                public void onMeasurePulseChanged(int current, int min, int max) {

                }
            });
    linerHeartRateManager.start();
```
