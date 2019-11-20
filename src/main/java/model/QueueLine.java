package model;

import java.util.LinkedList;
import java.util.Queue;

import component.Clock;
import controller.DashboardController;

public class QueueLine implements Runnable {
	private Stat stats;
	private Queue<Client> queue;
	private boolean firstPass;

	public QueueLine(int id) {
		stats = new Stat();
		stats.setLineId(id);
		firstPass = true;
		queue = new LinkedList<>();
	}

	public void enqueue(Client e) {
		DashboardController.addLogEntry("Joined: " + e.toString() + "\n");
		queue.add(e);
		stats.setTotalQueueTime(stats.getTotalQueueTime() + e.getProcessTime());
		stats.setTotalEntities(1 + stats.getTotalEntities());
		DashboardController.editClients(stats.getLineId(), 1);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(5);
				int time = Clock.getCounter();

				processClients(time);
				if (stats.getProcessedEntities() != 0) {
					stats.computeAvgProcTime();
					stats.computeAvgWaitTime();
				}

				if (Clock.getCounter() >= Clock.getSimulationTime()) {
					break;// end this loop
				}
				Thread.sleep(1005);
			} catch (InterruptedException ex) {
				ex.printStackTrace();
				Thread.currentThread().interrupt();
			}
		}
	}

	public void processClients(int time) {
		stats.setTime(time);
		Client e = queue.peek();
		if (e != null) {

			// test to see if arrivalTime happened some time ago
			if (firstPass && e.getArrivalTime() <= time) {
				stats.setTotalEmptyTime(stats.getTotalEmptyTime() + time - e.getArrivalTime());
				e.setStartTime(time);
				firstPass = false;
			}

			// test to see if the entity has been processed
			if (time == (e.getStartTime() + e.getProcessTime())) {
				e.setFinishTime(time);
				e.setWaitingTime(e.getFinishTime() - e.getArrivalTime());
				e.setServiceLine(stats.getLineId());

				LineManager.addOutputEntity(queue.poll());

				if (queue.isEmpty()) {
					stats.setTotalEmptyTime(stats.getTotalEmptyTime() + 1);
				}

				DashboardController.addLogEntry("Processed: " + e.toString() + "\n");

				stats.setTotalWaitTime(stats.getTotalWaitTime() + e.getWaitingTime());
				stats.setTotalProcessTime(stats.getTotalProcessTime() + e.getProcessTime());
				stats.setProcessedEntities(stats.getProcessedEntities() + 1);

				DashboardController.editClients(stats.getLineId(), -1);

				firstPass = true;
			} else {
				// update queue line statistics
				stats.setTotalQueueTime(stats.getTotalQueueTime() - 1);
			}

		} else {
			stats.setTotalEmptyTime(stats.getTotalEmptyTime() + 1);
		}

		LineManager.reportStat(stats.duplicate());
	}

	@Override
	public String toString() {
		return "LINE: " + stats.toString();
	}

	public int getId() {
		return stats.getLineId();
	}

	public int getProcessedEntities() {
		return stats.getProcessedEntities();
	}

	public int getTotalEntities() {
		return stats.getTotalEntities();
	}

	public int getTotalQueueTime() {
		return stats.getTotalQueueTime();
	}

	public int getTotalEmptyTime() {
		return stats.getTotalEmptyTime();
	}

	public double getAvgProcTime() {
		if (stats.getProcessedEntities() != 0) {
			stats.computeAvgProcTime();
		}
		return stats.getAvgProcTime();
	}

	public double getAvgWaitTime() {
		if (stats.getProcessedEntities() != 0) {
			stats.computeAvgWaitTime();
		}
		return stats.getAvgWaitTime();
	}

	public Stat getStat() {
		return stats;
	}

	public int getPeakHour() {
		return stats.getPeakHour();
	}

	public void setPeakHour(int peakHour) {
		stats.setPeakHour(peakHour);
	}

}
