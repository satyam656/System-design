package com.alexa;

import com.alexa.device.*;

public class AlexaDemo {
    public static void main(String[] args) {
        AudioOnlyDevice audioOnlyDevice = new AudioOnlyDevice("AudionOnly1", "Model1");
        AudioOnlyDeviceWithBattery audioOnlyDeviceWithBattery = new AudioOnlyDeviceWithBattery("AudioOnly2", "Model2");

        ScreenOnlyDevice screenOnlyDevice = new ScreenOnlyDevice("ScreenOnly1", "Model3");
        ScreenOnlyDeviceWithBattery screenOnlyDeviceWithBattery = new ScreenOnlyDeviceWithBattery("ScreenOnly2", "Model4");

        AudioScreenDevice audioScreenDevice = new AudioScreenDevice("AudioScreen1", "Model5");
        AudioScreenDeviceWithBattery audioScreenDeviceWithBattery = new AudioScreenDeviceWithBattery("AudioScreen2", "Model6");

        System.out.println("Testing non-battery audio device");
        audioOnlyDevice.turnOn();
        audioOnlyDevice.playAudio("I'm all yours");
        audioOnlyDevice.show();

        System.out.println("Testing Battery Audio device");
        audioOnlyDeviceWithBattery.turnOn();
        audioOnlyDeviceWithBattery.startCharging();
        audioOnlyDeviceWithBattery.playAudio("I love you forever.");
        audioOnlyDeviceWithBattery.show();

        System.out.println("Testing Screen only device.");
        screenOnlyDevice.turnOn();
        screenOnlyDevice.displayContent("Titanic");
        screenOnlyDevice.show();

        System.out.println("Testing Screen only device with battery");
        screenOnlyDeviceWithBattery.turnOn();
        screenOnlyDeviceWithBattery.startCharging();
        screenOnlyDeviceWithBattery.displayContent("Titanic Again");
        screenOnlyDeviceWithBattery.show();

        System.out.println("Testing Audio Screen device.");
        audioScreenDevice.turnOn();
        audioScreenDevice.displayContent("Titanic");
        audioScreenDevice.show();

        System.out.println("Testing Audio Screen device with battery");
        audioScreenDeviceWithBattery.turnOn();
        audioScreenDeviceWithBattery.startCharging();
        audioScreenDeviceWithBattery.displayContent("Titanic Again");
        audioScreenDeviceWithBattery.show();
    }
}