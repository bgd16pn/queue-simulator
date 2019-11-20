package component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.swing.SwingWorker;
import javax.swing.Timer;

public class SwingWorkerClock extends SwingWorker<List<Integer>, Integer> {

	private static int simulationTime = 5;
	private static AtomicInteger counter = new AtomicInteger(0);
	private static List<Integer> timeIntervals = new ArrayList<>();
	private Timer timer = new Timer(1000, e -> {
		int time = counter.incrementAndGet();
		System.out.println("Counter = " + time);
		publish(time);
		timeIntervals.add(time);
	});

	private static SwingWorkerClock swingWorkerClock = null;

	private SwingWorkerClock() {
	}

	@Override
	protected List<Integer> doInBackground() throws Exception {
//		System.out.println("SwingWorkerClock[0;5]");
		timer.start();
		while (true) {
			if (counter.get() >= simulationTime) {
				timer.stop();
				System.out.println("Exiting...");
				break;
			}
		}
		return timeIntervals;
	}

	@Override
	protected void process(List<Integer> chunks) {
		System.out.println("TimeTicks = " + timeIntervals);
	}

	public static int getCounter() {
		return counter.get();
	}

	public static int getSimulationTime() {
		return simulationTime;
	}

	public static void setSimulationTime(int simulationTime) {
		SwingWorkerClock.simulationTime = simulationTime;
	}

	public static SwingWorkerClock getInstance() {
		if (swingWorkerClock != null) {
			return swingWorkerClock;
		}
		return new SwingWorkerClock();
	}

}
