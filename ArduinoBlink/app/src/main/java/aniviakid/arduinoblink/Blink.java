package aniviakid.arduinoblink;

import android.content.Context;

import org.sintef.jarduino.DigitalPin;
import org.sintef.jarduino.DigitalState;
import org.sintef.jarduino.PinMode;
import org.sintef.jarduino.UsbArduino;

/**
 * Created by ASUS on 2019/1/15.
 */

public class Blink extends UsbArduino {
    public Blink(Context appContext) {
        super(appContext);
    }

    @Override
    protected void setup() {
// initialize digital pin LED_BUILTIN as an output.
        pinMode(DigitalPin.PIN_13, PinMode.OUTPUT);
    }

    @Override
    protected void loop() {
        digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);   // turn the LED on (HIGH is the voltage level)
        delay(1000);                       // wait for a second
        digitalWrite(DigitalPin.PIN_13,DigitalState.LOW);    // turn the LED off by making the voltage LOW
        delay(1000);
    }
}
