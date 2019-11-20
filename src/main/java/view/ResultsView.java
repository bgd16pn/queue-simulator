package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeListener;
import javax.swing.JSlider;
import javax.swing.JSeparator;

public class ResultsView extends JFrame {

	private static final long serialVersionUID = -1406241033417273098L;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 450;
	private static final Font tahoma13bold = new Font("Tahoma", Font.BOLD, 13);

	private JTextField peakHourTextField;
	private JTextField avgServTextField;
	private JTextField avgWaitTextField;
	private JTextField emptyQueuesTextField;
	private JTextField clientsTextField;

	private JSlider simTimeSlider;
	private JComboBox<String> minSimTimeComboBox;
	private JComboBox<String> maxSimTimeComboBox;
	private JComboBox<String> queueSelectorcomboBox;

	private JLabel lblMinSimTime;
	private JLabel lblMaxSimTime;
	private JLabel lblCurrentSimTime;

	public ResultsView(Dimension screenSize) {

		this.setBounds((int) (screenSize.getWidth() / 2.0) - (int) (WINDOW_WIDTH / 2.0),
				(int) (screenSize.getHeight() / 2.0) - (int) (WINDOW_HEIGHT / 2.0), WINDOW_WIDTH, WINDOW_HEIGHT);

		getContentPane().setLayout(null);
		this.setTitle("Simulation results");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JLabel lblQueueSelector = new JLabel("Select a queue");
		lblQueueSelector.setFont(tahoma13bold);
		lblQueueSelector.setBounds(36, 15, 116, 25);
		getContentPane().add(lblQueueSelector);

		queueSelectorcomboBox = new JComboBox<String>();
		queueSelectorcomboBox.setMaximumRowCount(100);
		queueSelectorcomboBox.setBounds(274, 16, 83, 22);
		getContentPane().add(queueSelectorcomboBox);

		JLabel lblPeakHour = new JLabel("Peak hour");
		lblPeakHour.setFont(tahoma13bold);
		lblPeakHour.setBounds(36, 53, 74, 25);
		getContentPane().add(lblPeakHour);

		JLabel lblAvgServ = new JLabel("Average service time");
		lblAvgServ.setFont(tahoma13bold);
		lblAvgServ.setBounds(36, 91, 149, 24);
		getContentPane().add(lblAvgServ);

		JLabel lblAvgWait = new JLabel("Average waiting time");
		lblAvgWait.setFont(tahoma13bold);
		lblAvgWait.setBounds(36, 128, 149, 25);
		getContentPane().add(lblAvgWait);

		JLabel lblEmptyQueues = new JLabel("Empty queues time");
		lblEmptyQueues.setFont(tahoma13bold);
		lblEmptyQueues.setBounds(35, 166, 168, 25);
		getContentPane().add(lblEmptyQueues);

		JLabel lblClients = new JLabel("Processed clients");
		lblClients.setFont(tahoma13bold);
		lblClients.setBounds(35, 204, 168, 25);
		getContentPane().add(lblClients);

		peakHourTextField = new JTextField();
		peakHourTextField.setBounds(241, 54, 116, 22);
		getContentPane().add(peakHourTextField);
		peakHourTextField.setColumns(10);

		avgServTextField = new JTextField();
		avgServTextField.setColumns(10);
		avgServTextField.setBounds(241, 92, 116, 22);
		getContentPane().add(avgServTextField);

		avgWaitTextField = new JTextField();
		avgWaitTextField.setColumns(10);
		avgWaitTextField.setBounds(241, 129, 116, 22);
		getContentPane().add(avgWaitTextField);

		emptyQueuesTextField = new JTextField();
		emptyQueuesTextField.setColumns(10);
		emptyQueuesTextField.setBounds(241, 167, 116, 22);
		getContentPane().add(emptyQueuesTextField);

		clientsTextField = new JTextField();
		clientsTextField.setColumns(10);
		clientsTextField.setBounds(241, 205, 116, 22);
		getContentPane().add(clientsTextField);

		JSeparator separator = new JSeparator();
		separator.setBounds(36, 242, 321, 7);
		getContentPane().add(separator);

		JLabel lblListBetween = new JLabel("List between");
		lblListBetween.setFont(tahoma13bold);
		lblListBetween.setBounds(36, 282, 116, 25);
		getContentPane().add(lblListBetween);

		JLabel lblMax = new JLabel("MAX");
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setFont(tahoma13bold);
		lblMax.setBounds(294, 260, 50, 25);
		getContentPane().add(lblMax);

		JLabel lblMin = new JLabel("MIN");
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setFont(tahoma13bold);
		lblMin.setBounds(188, 260, 50, 25);
		getContentPane().add(lblMin);

		simTimeSlider = new JSlider();
		simTimeSlider.setMajorTickSpacing(5);
		simTimeSlider.setPaintTicks(true);
		simTimeSlider.setBounds(37, 359, 320, 26);
		getContentPane().add(simTimeSlider);

		minSimTimeComboBox = new JComboBox<>();
		minSimTimeComboBox.setMaximumRowCount(100);
		minSimTimeComboBox.setBounds(174, 283, 83, 22);
		getContentPane().add(minSimTimeComboBox);

		maxSimTimeComboBox = new JComboBox<>();
		maxSimTimeComboBox.setMaximumRowCount(100);
		maxSimTimeComboBox.setBounds(274, 283, 83, 22);
		getContentPane().add(maxSimTimeComboBox);

		lblMinSimTime = new JLabel("1");
		lblMinSimTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMinSimTime.setBounds(31, 346, 25, 16);
		getContentPane().add(lblMinSimTime);

		lblMaxSimTime = new JLabel("1");
		lblMaxSimTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblMaxSimTime.setBounds(332, 346, 25, 16);
		getContentPane().add(lblMaxSimTime);

		lblCurrentSimTime = new JLabel("1");
		lblCurrentSimTime.setFont(tahoma13bold);
		lblCurrentSimTime.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentSimTime.setBounds(183, 335, 25, 16);
		getContentPane().add(lblCurrentSimTime);

	}

	public void setMinSimTimeText(String str) {
		lblMinSimTime.setText(str);
	}

	public void setMaxSimTimeText(String str) {
		lblMaxSimTime.setText(str);
	}

	public void setCurrentSimTimeText(String str) {
		lblCurrentSimTime.setText(str);
	}

	public void setPeakHourText(String str) {
		peakHourTextField.setText(str);
	}

	public String getPeakHourText() {
		return peakHourTextField.getText();
	}

	public void setAvgServiceText(String str) {
		avgServTextField.setText(str);
	}

	public String getAvgServiceText() {
		return avgServTextField.getText();
	}

	public void setAvgWaitText(String str) {
		avgWaitTextField.setText(str);
	}

	public String getAvgWaitText() {
		return avgWaitTextField.getText();
	}

	public void setEmptyQueuesTimeText(String str) {
		emptyQueuesTextField.setText(str);
	}

	public String getEmptyQueuesTimeText() {
		return emptyQueuesTextField.getText();
	}

	public void setClientNumberText(String str) {
		clientsTextField.setText(str);
	}

	public String getClientNumberText() {
		return clientsTextField.getText();
	}

	public void setMinTimeSlider(int min) {
		simTimeSlider.setMinimum(min);
		lblMinSimTime.setText(String.valueOf(min));
	}

	public void setMaxTimeSlider(int max) {
		simTimeSlider.setMaximum(max);
		lblMaxSimTime.setText(String.valueOf(max));
	}
	
	public void setCurrentSimTime(int value) {
		simTimeSlider.setValue(value);
	}
	
	public int getCurrentSimTime() {
		return simTimeSlider.getValue();
	}

	public void setTimeSliderTickSize(int tickSize) {
		simTimeSlider.setMajorTickSpacing(tickSize);
	}

	public int getMinSimTimeSelectedIndex() {
		return minSimTimeComboBox.getSelectedIndex();
	}

	public int getMaxSimTimeSelectedIndex() {
		return maxSimTimeComboBox.getSelectedIndex();
	}

	public void setMaxSimTimeSelectedIndex(int anIndex) {
		maxSimTimeComboBox.setSelectedIndex(anIndex);
	}

	public void setMinSimTimeSelectedIndex(int anIndex) {
		minSimTimeComboBox.setSelectedIndex(anIndex);
	}

	public void setMinSimTimeItems(String[] items) {
		for (String item : items) {
			minSimTimeComboBox.addItem(item);
		}
	}

	public void setMaxSimTimeItems(String[] items) {
		for (String item : items) {
			maxSimTimeComboBox.addItem(item);
		}
	}

	public int getQueueSelectorSelectedIndex() {
		return queueSelectorcomboBox.getSelectedIndex();
	}

	public void setQueueSelectorSelectedIndex(int anIndex) {
		queueSelectorcomboBox.setSelectedIndex(anIndex);
	}

	public void setQueueSelectorItems(String[] items) {
		for (String item : items) {
			queueSelectorcomboBox.addItem(item);
		}
	}

	public void addQueueSelectorActionListener(ActionListener actionListener) {
		queueSelectorcomboBox.addActionListener(actionListener);
	}

	public void addMinSimTimeActionListener(ActionListener actionListener) {
		minSimTimeComboBox.addActionListener(actionListener);
	}

	public void addMaxSimTimeActionListener(ActionListener actionListener) {
		maxSimTimeComboBox.addActionListener(actionListener);
	}

	public void addSimulationTimeSliderChangeListener(ChangeListener changeListener) {
		simTimeSlider.addChangeListener(changeListener);
	}
}
