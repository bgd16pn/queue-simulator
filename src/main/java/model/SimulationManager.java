package model;

import java.util.LinkedList;
import java.util.Queue;

import component.Clock;
import component.RandomClientGenerator;
import controller.DashboardController;

public class SimulationManager {

	private static int simulationTime = 0;
	private static int numberOfLines = 5;
	private static int minArrTime = 0;
	private static int maxArrTime = 0;
	private static int minProcTime = 0;
	private static int maxProcTime = 0;

	private Queue<Client> pool;
	private LineManager lineManager;

	public SimulationManager() {
		pool = new LinkedList<>();
		lineManager = new LineManager(pool, numberOfLines);
	}

	public void start() {
		RandomClientGenerator.generateEntities(pool, minProcTime, maxProcTime, minArrTime, maxArrTime, simulationTime);

		DashboardController.addLogEntry("CLIENTS ARE:\n");
		int i = 0;
		for (Client e : pool) {
			DashboardController.addLogEntry("#" + i++ + ": " + e.toString() + "\n");
		}
		DashboardController.addLogEntry("\n");

		Clock.setSimulationTime(simulationTime);

		lineManager.processLines();
	}

	public static void cancel() {
		System.out.println("Cancelling simulation...");
	}

	public static int getSimulationTime() {
		return simulationTime;
	}

	public static int getNumberOfLines() {
		return numberOfLines;
	}

	public static int getMinArrivalTime() {
		return minArrTime;
	}

	public static int getMaxArrivalTime() {
		return maxArrTime;
	}

	public static int getMinServiceTime() {
		return minProcTime;
	}

	public static int getMaxServiceTime() {
		return maxProcTime;
	}

	public static void setSimulationTime(int simulationTime) {
		SimulationManager.simulationTime = simulationTime;
	}

	public static void setNumberOfLines(int numberOfLines) {
		SimulationManager.numberOfLines = numberOfLines;
	}

	public static void setMinArrivalTime(int minArrTime) {
		SimulationManager.minArrTime = minArrTime;
	}

	public static void setMaxArrivalTime(int maxArrTime) {
		SimulationManager.maxArrTime = maxArrTime;
	}

	public static void setMinServiceTime(int minProcTime) {
		SimulationManager.minProcTime = minProcTime;
	}

	public static void setMaxServiceTime(int maxProcTime) {
		SimulationManager.maxProcTime = maxProcTime;
	}

	public LineManager getLineManager() {
		return lineManager;
	}
}
