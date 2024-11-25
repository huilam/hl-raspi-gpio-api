package hl.raspi.gpio;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;

public class PiGpio{
	
	private static final int PIN_LED = 22;
	
	public static void main(String args[])
	{
		Context pi4j = Pi4J.newAutoContext();
		
		DigitalOutputConfigBuilder ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                .address(PIN_LED);
		
		DigitalOutput led = pi4j.create(ledConfig);
		
		led.on();
	}
}