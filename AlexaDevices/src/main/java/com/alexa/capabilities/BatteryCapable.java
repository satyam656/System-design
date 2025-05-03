package com.alexa.capabilities;


// interface for battery capability

public interface BatteryCapable {
    int getBatteryPercentage();
    boolean isCharging();
    void startCharging();
    void stopCharging();
}
