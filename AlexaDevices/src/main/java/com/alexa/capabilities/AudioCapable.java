package com.alexa.capabilities;

// interface for devices with audio capability

public interface AudioCapable {
    void playAudio(String audioContent);
    void adjustVolume(int level);
}
