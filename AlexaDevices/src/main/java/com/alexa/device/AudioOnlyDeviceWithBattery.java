package com.alexa.device;

import com.alexa.capabilities.AudioCapable;
import com.alexa.capabilities.BatteryCapable;

public class AudioOnlyDeviceWithBattery extends AudioOnlyDevice implements BatteryCapable {

    private int batteryPercentage;
    private boolean chargingStatus;

    public AudioOnlyDeviceWithBattery(String deviceName, String deviceModel) {
        super(deviceName, deviceModel);
        this.batteryPercentage = 100;
        this.chargingStatus = false;
    }

    @Override
    public int getBatteryPercentage() {
        return batteryPercentage;
    }

    @Override
    public boolean isCharging() {
        return chargingStatus;
    }

    @Override
    public void startCharging() {
        chargingStatus = true;
        System.out.println(getDeviceName() + " is charging now.");
    }

    @Override
    public void stopCharging() {
        chargingStatus = false;
        System.out.println(getDeviceName() + " is stopped charging");
    }

    @Override
    public void show() {
        if(isCharging()) {
            System.out.println("Charging: " + getBatteryPercentage() + "%");
        } else {
            System.out.println("Battery: " + getBatteryPercentage() + "%");
        }
    }
}
