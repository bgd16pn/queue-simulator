package controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import component.Clock;
import component.SwingClock;
import model.SimulationManager;
import view.DashboardView;
import view.SetupView;

public class DashboardController {

	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final DashboardView dashboardView = new DashboardView(screenSize);

	private static int[] clientVisibility = { 0, 0, 0, 0, 0 };

	private static final Runnable progress = () -> {
		while (true) {
			DashboardController.updateProgressBarValue(100 * Clock.getCounter() / Clock.getSimulationTime());
			DashboardController.updateLog();
			DashboardController.updateQueues();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			}
			if (Clock.getCounter() > Clock.getSimulationTime()) {
				break;
			}
		}
	};

	private DashboardController() {
	}

	private static void updateQueues() {
		for (int i = 0; i < SimulationManager.getNumberOfLines(); i++) {
			dashboardView.setLineLabelVisible(i, true);
			if (clientVisibility[i] > 0) {
				for (int j = 0; j < clientVisibility[i]; j++) {
					dashboardView.setButtonIconVisible(i, j, true);
				}
			} else {
				for (int j = 0; j < 12; j++) {
					dashboardView.setButtonIconVisible(i, j, false);
				}
			}
		}
	}

	public static void start(SetupView setupView) {

		initializeButtonListeners(setupView);

		dashboardView.setVisible(true);

		Runnable runnable = () -> {
			startSimulation(setupView);
			dashboardView.setCancelButtonVisible(false);
			dashboardView.setRerunButtonVisible(true);
		};
		new Thread(runnable).start();

	}

	private static void initializeButtonListeners(SetupView setupView) {
		dashboardView.addCancelButtonActionListener(e -> {
			SimulationManager.cancel();
			System.exit(1);
		});

		dashboardView.addRerunButtonActionListener(e -> {
			Clock.resetTimer();
			dashboardView.setLoggerTextArea("");
			dashboardView.setCancelButtonVisible(false);
			dashboardView.setRerunButtonVisible(true);
			dashboardView.setVisible(false);
			dashboardView.setLineLabelsVisible(false);
			dashboardView.setButtonIconsVisible(false);
			for (int i = 0; i < 5; i++) {
				clientVisibility[i] = 0;
			}
			setupView.setVisible(true);
		});

	}

	private static void startSimulation(SetupView setupView) {
		System.out.println("Simulation started...");

		int minServiceTime = Integer.parseInt(setupView.getMinServiceText());
		int maxServiceTime = Integer.parseInt(setupView.getMaxServiceText());
		int minArrivalTime = Integer.parseInt(setupView.getMinArrivalText());
		int maxArrivalTime = Integer.parseInt(setupView.getMaxArrivalText());
		int simulationInterval = Integer.parseInt(setupView.getSimulationTimeText());
		int numberOfQueues = setupView.getQueuesNumberSelectedIndex();

		SimulationManager.setMaxArrivalTime(maxArrivalTime);
		SimulationManager.setMinArrivalTime(minArrivalTime);
		SimulationManager.setMaxServiceTime(maxServiceTime);
		SimulationManager.setMinServiceTime(minServiceTime);
		SimulationManager.setNumberOfLines(numberOfQueues);
		SimulationManager.setSimulationTime(simulationInterval);

		new Thread(progress).start();

		SimulationManager simManager = new SimulationManager();
		simManager.start();

		Runnable runnable = () -> {
			new SwingClock(2).start();
			ResultsController.start(simManager);
		};
		new Thread(runnable).start();
	}

	public static void updateProgressBarValue(int value) {
		dashboardView.setProgressBarValue(value);
	}

	public static void addLogEntry(String str) {
		dashboardView.appendLoggerTextArea(str);
	}

	private static void updateLog() {
		dashboardView.updateLog();
	}

	public static void editClients(int lineId, int offset) {
		clientVisibility[lineId] += offset;
	}

}
