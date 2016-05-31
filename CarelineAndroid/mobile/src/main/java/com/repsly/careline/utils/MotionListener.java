package com.repsly.careline.utils;

import android.hardware.SensorEvent;

/**
 * Created by Alen on 31.5.2016..
 */
public interface MotionListener {
    void onMotionDetected(SensorEvent event, float acceleration);
}
