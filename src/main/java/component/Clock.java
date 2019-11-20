package component;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Clock {

	private static Clock clock = null;
	private static boolean isAlive = false;
	private static AtomicInteger counter = new AtomicInteger(0);
	private static int simulationTime = 10;
	private static TimerTask timedTask = new TimerTask() {
		@Override
		public void run() {
			if (counter.incrementAndGet() >= simulationTime) {
				timer.cancel();
				setAlive(false);
			}
		}
	};
	private static Timer timer = new Timer();

	private Clock() {
		setSimulationTime(Clock.simulationTime);
	}

	public static Clock getInstance() {
		if (clock == null) {
			return new Clock();
		}
		return clock;
	}

	public static int getCounter() {
		return Clock.counter.get();
	}

	public static void setSimulationTime(int simulationTime) {
		Clock.simulationTime = simulationTime;
	}

	public static int getSimulationTime() {
		return Clock.simulationTime;
	}

	public void start() {
		setAlive(true);
		timer.scheduleAtFixedRate(timedTask, 0, 1000);
	}

	public static void resetTimer() {
		timer.purge();
		timer = new Timer();
		counter.set(0);
		timedTask = new TimerTask() {
			@Override
			public void run() {
				if (counter.incrementAndGet() >= simulationTime) {
					timer.cancel();
					setAlive(false);
				}
			}
		};
	}

	public static boolean isAlive() {
		return isAlive;
	}

	public static void setAlive(boolean isAlive) {
		Clock.isAlive = isAlive;
	}

}
