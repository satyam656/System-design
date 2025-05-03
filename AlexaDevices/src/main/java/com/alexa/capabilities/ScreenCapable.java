package com.alexa.capabilities;

// interface for screen capability in alexa devices

public interface ScreenCapable {
    void displayContent(String content);
    void adjustBrightness(int level);
}
