package model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import component.Clock;
import controller.DashboardController;

public class LineManager {

	private static Clock clock = Clock.getInstance();
	private static BlockingQueue<Client> outputPool = new LinkedBlockingQueue<>();
	private static BlockingQueue<Stat> statsLog = new LinkedBlockingQueue<>();

	private Queue<Client> pool;
	private Map<Integer, QueueLine> lines;
	private int numberOfLines;

	public LineManager(Queue<Client> pool, int numberOfLines) {
		this.pool = pool;
		this.numberOfLines = numberOfLines;
		this.lines = new HashMap<>(numberOfLines);
		for (int i = 0; i < numberOfLines; i++) {
			lines.put(i, new QueueLine(i));
		}
	}

	public void addLine(QueueLine l) {
		lines.put(l.getId(), l);
	}

	public void addEntity(Client e) {
		int lineIndex = getBestCheckoutLine();
		QueueLine line = lines.get(lineIndex);
		line.enqueue(e);
	}

	public static void addOutputEntity(Client e) {
		outputPool.add(e);
	}

	public void processLines() {
		for (int i = 0; i < numberOfLines; i++) {
			QueueLine line = lines.get(i);
			Thread t1 = new Thread(line, "Q" + line.getId());
			t1.start();
		}

		clock.start();

		while (!pool.isEmpty()) {
			Client e = pool.peek();
			if (e.getArrivalTime() == Clock.getCounter()
					&& e.getArrivalTime() + e.getProcessTime() <= Clock.getSimulationTime()) {
				addEntity(pool.poll());
			}

			if (Clock.getCounter() >= Clock.getSimulationTime()) {
				break;
			}
		}
	}

	private int getBestCheckoutLine() {
		int minProcTime = Integer.MAX_VALUE;
		int index = 0;

		Iterator<QueueLine> it = lines.values().iterator();
		while (it.hasNext()) {
			QueueLine line = it.next();
			int tmp = line.getTotalQueueTime();
			if (tmp < minProcTime) {
				minProcTime = tmp;
				index = line.getId();
			}
		}
		return index;
	}

	public void displayStats() {

		computePeakHour();

		DashboardController.addLogEntry("\nPROCESSED CLIENTS WERE:\n");
		Iterator<Client> it = outputPool.iterator();
		while (it.hasNext()) {
			Client c = it.next();
			DashboardController.addLogEntry("@ " + c.toString() + "\n");
		}
		DashboardController.addLogEntry("\n");

		for (QueueLine line : lines.values()) {
			DashboardController.addLogEntry(line.toString() + "\n");
		}

	}

	private void computePeakHour() {
		int[] peakHour = { 0, 0, 0, 0, 0 };

		for (Stat stat : statsLog) {
			int lineId = stat.getLineId();
			if (peakHour[lineId] < stat.getTotalEntities()) {
				peakHour[lineId] = stat.getTime();
			}
		}

		for (int i = 0; i < numberOfLines; i++) {
			lines.get(i).setPeakHour(peakHour[i]);
		}

	}

	public static void reportStat(Stat stats) {
		statsLog.add(stats);
	}

	public List<QueueLine> getLines() {
		return lines.values().stream().collect(Collectors.toList());
	}
}
