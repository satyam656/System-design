package com.alexa.device;

import com.alexa.capabilities.BatteryCapable;

public class ScreenOnlyDeviceWithBattery extends ScreenOnlyDevice implements BatteryCapable {
    private int batteryPercentage;
    private boolean chargingStatus;

    public ScreenOnlyDeviceWithBattery(String deviceName, String deviceModel) {
        super(deviceName, deviceModel);
        this.batteryPercentage = 100; //default
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
        System.out.println(getDeviceName() + " is now charging.");
    }

    @Override
    public void stopCharging() {
        chargingStatus = false;
        System.out.println(getDeviceName() + " is no longer charging.");
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
