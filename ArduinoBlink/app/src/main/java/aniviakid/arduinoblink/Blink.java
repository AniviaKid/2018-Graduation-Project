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

    int thread = 0; //占空比
    int period = 500; //周期
    int i = 0;
    int flag = 1; //flag为1由暗至亮，flag为-1由亮至暗

    @Override
    protected void setup() {
// initialize digital pin LED_BUILTIN as an output.
        pinMode(DigitalPin.PIN_13, PinMode.OUTPUT);
    }

    @Override
    protected void loop() {
       /* digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);   // turn the LED on (HIGH is the voltage level)
        delay(1000);                       // wait for a second
        digitalWrite(DigitalPin.PIN_13,DigitalState.LOW);    // turn the LED off by making the voltage LOW
        delay(1000);*/
        if (i < thread) { //高电平
            digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);
            i++;
        }
        else if (i >= thread && i < period) { //低电平
            digitalWrite(DigitalPin.PIN_13, DigitalState.LOW);
            i++;
        }
        else if (i == period) { //一个周期执行完改变占空比
            i = 0;
            if (flag == 1) {
                thread++;
                if (thread == period) flag *= -1;
            }
            else {
                thread--;
                if (thread == 0) flag *= -1;
            }
        }
    }
}
