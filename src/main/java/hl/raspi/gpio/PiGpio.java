package hl.raspi.gpio;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import com.pi4j.Pi4J;
import com.pi4j.context.Context;
import com.pi4j.io.gpio.digital.DigitalOutput;
import com.pi4j.io.gpio.digital.DigitalOutputConfigBuilder;
import com.pi4j.io.gpio.digital.DigitalState;

public class PiGpio{
	
	public static DigitalOutput createGpioOutput(Context aPiContext, int aPinNum)
	{	
		DigitalOutputConfigBuilder ledConfig = DigitalOutput.newConfigBuilder(aPiContext)
                .address(aPinNum);
		
		return aPiContext.create(ledConfig);
	}
	
	public static void printState(DigitalOutput aOutput)
	{
		DigitalState state = aOutput.state();
		System.out.println(state.getName());
		System.out.println(state.getValue());
	}
	
	public static void main(String args[]) throws InterruptedException
	{
		
		Context pi4j = Pi4J.newAutoContext();
		
		DigitalOutput led_red 		= createGpioOutput(pi4j, 16);
		DigitalOutput led_yellow 	= createGpioOutput(pi4j, 20);
		DigitalOutput led_green 	= createGpioOutput(pi4j, 21);
		

		led_red.off();
		led_yellow.off();
		led_green.off();
		
		led_red.on();
		led_green.on();
		Future<?> f1 = led_yellow.blinkAsync(500, TimeUnit.MILLISECONDS);
		
		printState(led_red);
		printState(led_yellow);
		printState(led_green);
		
		Thread.sleep(5000);
		
		f1.cancel(true);
		led_red.off();
		led_yellow.off();
		led_green.off();
		
		pi4j.shutdown();
		
	}
}
