package com.example.blink;

import org.sintef.jarduino.*;

import android.content.Context;

public class BlinkArduino extends UsbArduino {

	public BlinkArduino(Context appContext) {
		super(appContext);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void loop() {
		digitalWrite(DigitalPin.PIN_13, DigitalState.HIGH);   // turn the LED on (HIGH is the voltage level)
		  delay(1000);               // wait for a second
		  digitalWrite(DigitalPin.PIN_13, DigitalState.LOW);    // turn the LED off by making the voltage LOW
		  delay(1000);               // wait for a second
	}

	@Override
	protected void setup() {
		pinMode(DigitalPin.PIN_13, PinMode.OUTPUT);
	}

}
