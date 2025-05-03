package com.alexa.device;

import com.alexa.capabilities.AudioCapable;
import com.alexa.capabilities.ScreenCapable;

public class AudioScreenDevice extends BaseAlexaDevice implements AudioCapable, ScreenCapable {
    private int brightnessLevel;
    private int volumeLevel;

    public AudioScreenDevice(String deviceName, String deviceModel) {
        super(deviceName, deviceModel);
        this.brightnessLevel = 70; //default
        this.volumeLevel = 50;
    }

    @Override
    public void playAudio(String audioContent) {
        if(isOn()) {
            System.out.println("Playing audio: " + audioContent);
        } else {
            System.out.println("Audio can't be played as device is off.");
        }
    }

    @Override
    public void adjustVolume(int level) {
        if(level >= 0 && level <= 100) {
            this.volumeLevel = level;
            System.out.println("Volume adjusted to: " + level);
        } else {
            System.out.println("Invalid volume level.");
        }
    }

    @Override
    public void displayContent(String content) {
        if(isOn()) {
            System.out.println("Displaying content: " + content);
        } else {
            System.out.println("Device is off, cannot display content.");
        }
    }

    @Override
    public void adjustBrightness(int level) {
        if(level >= 0 && level <= 100) {
            this.brightnessLevel = level;
            System.out.println("Brightness adjusted to: " + level);
        } else {
            System.out.println("Invalid brightness level.");
        }
    }

    public void show() {
        System.out.println("Battery not available.");
    }
}
