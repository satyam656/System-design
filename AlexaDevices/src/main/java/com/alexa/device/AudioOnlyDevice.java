package com.alexa.device;

import com.alexa.capabilities.AudioCapable;

public class AudioOnlyDevice extends BaseAlexaDevice implements AudioCapable {
    private int volumeLevel;

    public AudioOnlyDevice(String deviceName, String deviceModel) {
        super(deviceName, deviceModel);
        this.volumeLevel = 50;
    }

    @Override
    public void playAudio(String audioContent) {
        if(isOn()) {
            System.out.println("Playing audio: " + audioContent);
        }
        else {
            System.out.println("Device is off. Cannot play audio.");
        }
    }

    @Override
    public void adjustVolume(int level) {
        if(level >= 0 && level <= 100) {
            this.volumeLevel = level;
            System.out.println("Volume adjusted to " + level);
        } else {
            System.out.println("Invalid volume level");
        }
    }

    public void show() {
        System.out.println("Battery not available");
    }
}
