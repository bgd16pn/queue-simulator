package controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import model.LineManager;
import model.QueueLine;
import model.SimulationManager;
import model.Stat;
import view.ResultsView;

public class ResultsController {

	private static final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private static final ResultsView resultsView = new ResultsView(screenSize);

	private static List<QueueLine> lines;
	private static LineManager lineManager;

	private ResultsController() {
	}

	public static void start(SimulationManager simManager) {

		lineManager = simManager.getLineManager();
		lines = lineManager.getLines();

		initializeResultEnvironment();
		initializeButtonListeners();

		analyzeSimulation();
		resultsView.setVisible(true);
	}

	private static void initializeButtonListeners() {
		resultsView.addQueueSelectorActionListener(e -> {
			int lineIndex = resultsView.getQueueSelectorSelectedIndex();
			Stat stat = lines.get(lineIndex).getStat();
			setTextFields(stat, lines.get(lineIndex).getPeakHour());
		});

		resultsView.addMinSimTimeActionListener(e -> {
			int min = resultsView.getMinSimTimeSelectedIndex();
			int max = resultsView.getMaxSimTimeSelectedIndex();

			if (min > max) {
				resultsView.setMaxSimTimeSelectedIndex(min);
				resultsView.setMinSimTimeSelectedIndex(max);
				int tmp = min;
				min = max;
				max = tmp;
			} else {
				resultsView.setMinSimTimeSelectedIndex(min);
			}

			resultsView.setMinTimeSlider(min + 1);
			resultsView.setMaxTimeSlider(max + 1);
		});

		resultsView.addMaxSimTimeActionListener(e -> {
			int min = resultsView.getMinSimTimeSelectedIndex();
			int max = resultsView.getMaxSimTimeSelectedIndex();

			if (min > max) {
				resultsView.setMaxSimTimeSelectedIndex(min);
				resultsView.setMinSimTimeSelectedIndex(max);
				int tmp = min;
				min = max;
				max = tmp;
			} else {
				resultsView.setMaxSimTimeSelectedIndex(max);
			}

			resultsView.setMinTimeSlider(min + 1);
			resultsView.setMaxTimeSlider(max + 1);
		});

		resultsView.addSimulationTimeSliderChangeListener(e -> {
			resultsView.setCurrentSimTimeText(String.valueOf(resultsView.getCurrentSimTime()));
		});

	}

	private static void initializeResultEnvironment() {
		resultsView.setMinTimeSlider(1);
		resultsView.setMaxTimeSlider(SimulationManager.getSimulationTime());
		resultsView.setCurrentSimTime(SimulationManager.getSimulationTime());
		resultsView.setCurrentSimTimeText(String.valueOf(SimulationManager.getSimulationTime()));
		resultsView.setTimeSliderTickSize(1);

		String[] numberOfQueuesTicks = new String[SimulationManager.getNumberOfLines()];
		for (int i = 0; i < numberOfQueuesTicks.length; i++) {
			numberOfQueuesTicks[i] = String.valueOf(i + 1);
		}
		resultsView.setQueueSelectorItems(numberOfQueuesTicks);

		String[] simulationIntervalTicks = new String[SimulationManager.getSimulationTime()];
		for (int i = 0; i < simulationIntervalTicks.length; i++) {
			simulationIntervalTicks[i] = String.valueOf(i + 1);
		}
		resultsView.setMinSimTimeItems(simulationIntervalTicks);
		resultsView.setMaxSimTimeItems(simulationIntervalTicks);

		Stat stat = lines.get(0).getStat();
		setTextFields(stat, lines.get(0).getPeakHour());
	}

	private static void analyzeSimulation() {
		lineManager.displayStats();
	}

	private static void setTextFields(Stat stat, int peakHour) {
		resultsView.setMaxSimTimeText(String.valueOf(SimulationManager.getSimulationTime()));
		resultsView.setMinSimTimeText(String.valueOf(0));

		stat.computeAvgProcTime();
		stat.computeAvgWaitTime();

		resultsView.setAvgServiceText(String.format("%.2f", stat.getAvgProcTime()));
		resultsView.setAvgWaitText(String.format("%.2f", stat.getAvgProcTime()));
		resultsView.setEmptyQueuesTimeText(String.valueOf(stat.getTotalEmptyTime()));
		resultsView.setClientNumberText(String.valueOf(stat.getProcessedEntities()));
		resultsView.setPeakHourText(String.valueOf(peakHour));
	}

}
