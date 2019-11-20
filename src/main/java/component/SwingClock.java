package component;

import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.Timer;

public class SwingClock {

	private static int simulationTime = 5;
	private static AtomicInteger counter = new AtomicInteger(0);
	private static Timer timer = new Timer(1000, e -> System.out.println("Counter = " + counter.incrementAndGet()));

	public SwingClock(int simulationTime) {
		setSimulationTime(simulationTime);
		counter.set(0);
	}

	public void start() {
		timer.start();
		while (true) {
			if (counter.get() >= simulationTime) {
				timer.stop();
				System.out.println("Exiting timer...");
				break;
			}
		}
	}
	
	public static int getCounter() {
		return counter.get();
	}

	public static int getSimulationTime() {
		return simulationTime;
	}

	public static void setSimulationTime(int simulationTime) {
		SwingClock.simulationTime = simulationTime;
	}
}
