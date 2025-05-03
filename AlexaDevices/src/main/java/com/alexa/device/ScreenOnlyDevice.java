package com.alexa.device;

import com.alexa.capabilities.ScreenCapable;

public class ScreenOnlyDevice extends BaseAlexaDevice implements ScreenCapable {
    private int brightnessLevel;

    public ScreenOnlyDevice(String deviceName, String deviceModel) {
        super(deviceName, deviceModel);
        this.brightnessLevel = 70; // default brightness
    }

    @Override
    public void displayContent(String content) {
        if(isOn()) {
            System.out.println("Displaying on screem: " + content);
        } else {
            System.out.println("Cannot play content, as device is off.");
        }
    }

    @Override
    public void adjustBrightness(int level) {
        if(level >= 0 && level <= 100) {
            this.brightnessLevel = level;
            System.out.println("Brightness adjusted to : " + brightnessLevel);
        } else {
            System.out.println("Invalid brightness level.");
        }
    }

    public void show() {
        System.out.println("Battery Not available");
    }

}
