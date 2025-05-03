package com.alexa.device;

public interface AlexaDevice {
    void turnOff();
    void turnOn();
    boolean isOn();
    String getDeviceInfo();
}
