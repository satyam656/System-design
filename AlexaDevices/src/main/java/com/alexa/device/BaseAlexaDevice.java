package com.alexa.device;

// base abstract class for all Alexa devices
abstract class BaseAlexaDevice implements AlexaDevice{
    private boolean powerState;
    private String deviceName;
    private String deviceModel;

    public BaseAlexaDevice(String deviceName, String deviceModel) {
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.powerState = false;
    }

    @Override
    public void turnOn() {
        powerState = true;
        System.out.println(deviceName + " turned on.");
    }

    @Override
    public void turnOff() {
        powerState = false;
        System.out.println(deviceName + " turned off");
    }

    @Override
    public boolean isOn() {
        return powerState;
    }

    @Override
    public String getDeviceInfo() {
        return "Devicename: " + deviceName + ", Devicemodel: " + deviceModel;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
}
