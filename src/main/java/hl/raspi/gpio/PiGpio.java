package hl.raspi.gpio;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;

public class PiGpio{
	
	public static DigitalOutput createGpioOutput(int aPinNum)
	{
		Context pi4j = Pi4J.newAutoContext();
		
		DigitalOutputConfigBuilder ledConfig = DigitalOutput.newConfigBuilder(pi4j)
                .address(aPinNum);
		
		return pi4j.create(ledConfig);
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		
		DigitalOutput led_red 		= createGpioOutput(16);
		DigitalOutput led_yellow 	= createGpioOutput(20);
		DigitalOutput led_green 	= createGpioOutput(21);
		

		led_red.off();
		led_yellow.off();
		led_green.off();
		
		led_red.on();
		Future f1 = led_yellow.blinkAsync(500, TimeUnit.MILLISECONDS);
		led_green.on();
		
		Thread.sleep(5000);
		
		f1.cancel(true);
		led_red.off();
		led_yellow.off();
		led_green.off();
	}
}
