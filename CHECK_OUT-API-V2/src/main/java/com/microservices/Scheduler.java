/**
 * 
 */
package com.microservices;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

/**
 * @author Satish Reddy
 *
 */
@Component
public class Scheduler {
	private final AtomicInteger checkOutGauge;
	  private final Counter checkOutCounter;

	  public Scheduler(MeterRegistry meterRegistry) {
	    
		  checkOutGauge = meterRegistry.gauge("custom_gauge", new AtomicInteger(0));
		  checkOutCounter = meterRegistry.counter("custom_counter");
	  }

	  @Scheduled(fixedRateString = "1000", initialDelayString = "0")
	  public void schedulingTask() {
		  checkOutGauge.set(Scheduler.getRandomNumberInRange(0, 100));

		  checkOutCounter.increment();
	  }

	  private static int getRandomNumberInRange(int min, int max) {
	    if (min >= max) {
	      throw new IllegalArgumentException("max must be greater than min");
	    }

	    Random r = new Random();
	    return r.nextInt((max - min) + 1) + min;
	  }
}
