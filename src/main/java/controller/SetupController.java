package controller;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.UIManager;

import view.SetupView;

public class SetupController {

	private SetupView setupView;

	public void start() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.getMessage();
		}

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		setupView = new SetupView(screenSize);

		initializeFields();
		initializeButtonListeners();
		setupView.setVisible(true);
	}

	private void initializeFields() {
		setupView.setMinServiceText("");
		setupView.setMaxServiceText("");
		setupView.setMinArrivalText("");
		setupView.setMaxArrivalText("");
		setupView.setSimulationTimeText("");
		setupView.setQueuesNumberSelectedIndex(2);
	}

	private void initializeButtonListeners() {
		setupView.addBtnStartSimActionListener(e -> {
			String minServ = setupView.getMinServiceText();
			String maxServ = setupView.getMaxServiceText();
			String minArr = setupView.getMinArrivalText();
			String maxArr = setupView.getMaxArrivalText();
			String simInterval = setupView.getSimulationTimeText();
			int numberOfQueues = setupView.getQueuesNumberSelectedIndex();

			if (validateInputData(minServ, maxServ, minArr, maxArr, numberOfQueues, simInterval)) {
				setupView.setVisible(false);
				DashboardController.start(setupView);
			} else {
				setupView.displayMessage("Invalid input data");
			}
		});
	}

	private boolean validateInputData(String minServ, String maxServ, String minArr, String maxArr, int numberOfQueues,
			String simInterval) {
		if (minServ.equals("") || maxServ.equals("") || minArr.equals("") || maxArr.equals("")
				|| simInterval.equals("")) {
			return false;
		}

		int minServiceTime = Integer.parseInt(minServ);
		int maxServiceTime = Integer.parseInt(maxServ);
		int minArrivalTime = Integer.parseInt(minArr);
		int maxArrivalTime = Integer.parseInt(maxArr);
		int simulationInterval = Integer.parseInt(simInterval);

		return !(minServiceTime < 1 || maxServiceTime < minServiceTime || minArrivalTime < 1
				|| maxArrivalTime < minArrivalTime || numberOfQueues < 1 || numberOfQueues > 5
				|| simulationInterval < 1);
	}
}
