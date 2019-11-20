package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;

public class SetupView extends JFrame {

	private static final long serialVersionUID = -1406241033417273098L;
	private static final int WINDOW_WIDTH = 400;
	private static final int WINDOW_HEIGHT = 450;
	private static final Font tahoma13bold = new Font("Tahoma", Font.BOLD, 13);
	private static final String[] numberOfQueuesLbls = new String[] { "1", "2", "3", "4", "5" };

	private JTextField simTimeTextField;
	private JTextField minServTextField;
	private JTextField maxServTextField;
	private JTextField maxArrTextField;
	private JTextField minArrTextField;

	private JButton btnStartSim;
	private JComboBox<String> queuesNumberComboBox;
	private JLabel lblErrorMessage;

	public SetupView(Dimension screenSize) {

		this.setBounds((int) (screenSize.getWidth() / 2.0) - (int) (WINDOW_WIDTH / 2.0),
				(int) (screenSize.getHeight() / 2.0) - (int) (WINDOW_HEIGHT / 2.0), WINDOW_WIDTH, WINDOW_HEIGHT);

		getContentPane().setLayout(null);
		this.setTitle("Queue manager simulator");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel lblTitle = new JLabel("SIMULATION SETUP");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setBounds(67, 41, 259, 35);
		getContentPane().add(lblTitle);

		JLabel lblArrInterval = new JLabel("Interval of arrival");
		lblArrInterval.setFont(tahoma13bold);
		lblArrInterval.setBounds(38, 161, 123, 24);
		getContentPane().add(lblArrInterval);

		JLabel lblServInterval = new JLabel("Interval of service");
		lblServInterval.setFont(tahoma13bold);
		lblServInterval.setBounds(38, 123, 123, 25);
		getContentPane().add(lblServInterval);

		JLabel lblNumberOfLines = new JLabel("Number of queues");
		lblNumberOfLines.setFont(tahoma13bold);
		lblNumberOfLines.setBounds(38, 213, 123, 25);
		getContentPane().add(lblNumberOfLines);

		JLabel lblSimTimeInterval = new JLabel("Simulation time interval");
		lblSimTimeInterval.setFont(tahoma13bold);
		lblSimTimeInterval.setBounds(38, 248, 168, 25);
		getContentPane().add(lblSimTimeInterval);

		JLabel lblMax = new JLabel("MAX");
		lblMax.setFont(tahoma13bold);
		lblMax.setHorizontalAlignment(SwingConstants.CENTER);
		lblMax.setBounds(302, 104, 56, 16);
		getContentPane().add(lblMax);

		JLabel lblMin = new JLabel("MIN");
		lblMin.setFont(tahoma13bold);
		lblMin.setHorizontalAlignment(SwingConstants.CENTER);
		lblMin.setBounds(196, 105, 56, 16);
		getContentPane().add(lblMin);

		simTimeTextField = new JTextField("");
		simTimeTextField.setBounds(278, 249, 74, 22);
		getContentPane().add(simTimeTextField);
		simTimeTextField.setColumns(10);

		queuesNumberComboBox = new JComboBox<>(numberOfQueuesLbls);
		queuesNumberComboBox.setMaximumRowCount(5);
		queuesNumberComboBox.setBounds(278, 214, 74, 22);
		getContentPane().add(queuesNumberComboBox);

		minServTextField = new JTextField("");
		minServTextField.setColumns(10);
		minServTextField.setBounds(201, 124, 46, 22);
		getContentPane().add(minServTextField);

		maxServTextField = new JTextField("");
		maxServTextField.setColumns(10);
		maxServTextField.setBounds(306, 124, 46, 22);
		getContentPane().add(maxServTextField);

		maxArrTextField = new JTextField("");
		maxArrTextField.setColumns(10);
		maxArrTextField.setBounds(306, 161, 46, 22);
		getContentPane().add(maxArrTextField);

		minArrTextField = new JTextField("");
		minArrTextField.setColumns(10);
		minArrTextField.setBounds(201, 161, 46, 22);
		getContentPane().add(minArrTextField);

		btnStartSim = new JButton("Start simulation");
		btnStartSim.setBounds(124, 322, 145, 25);
		getContentPane().add(btnStartSim);

		lblErrorMessage = new JLabel("Error message");
		lblErrorMessage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblErrorMessage.setForeground(Color.RED);
		lblErrorMessage.setBounds(27, 386, 331, 16);
		getContentPane().add(lblErrorMessage);
		lblErrorMessage.setVisible(false);

	}

	public void setErrorMessageVisible(boolean flag) {
		lblErrorMessage.setVisible(flag);
	}

	public void setErrorMessageText(String str) {
		lblErrorMessage.setText(str);
	}

	public void setSimulationTimeText(String t) {
		simTimeTextField.setText(t);
	}

	public String getSimulationTimeText() {
		return simTimeTextField.getText();
	}

	public void setMaxArrivalText(String t) {
		maxArrTextField.setText(t);
	}

	public void setMinArrivalText(String t) {
		minArrTextField.setText(t);
	}

	public void setMaxServiceText(String t) {
		maxServTextField.setText(t);
	}

	public void setMinServiceText(String t) {
		minServTextField.setText(t);
	}

	public String getMaxArrivalText() {
		return maxArrTextField.getText();
	}

	public String getMinArrivalText() {
		return minArrTextField.getText();
	}

	public String getMaxServiceText() {
		return maxServTextField.getText();
	}

	public String getMinServiceText() {
		return minServTextField.getText();
	}

	public void addBtnStartSimActionListener(ActionListener l) {
		btnStartSim.addActionListener(l);
	}

	public int getQueuesNumberSelectedIndex() {
		return queuesNumberComboBox.getSelectedIndex() + 1;
	}

	public void setQueuesNumberSelectedIndex(int anIndex) {
		queuesNumberComboBox.setSelectedIndex(anIndex);
	}

	public void displayMessage(String message) {
		final Runnable runnable = () -> {
			lblErrorMessage.setText(message);
			lblErrorMessage.setVisible(true);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt();
			} finally {
				lblErrorMessage.setVisible(false);
			}
		};

		Thread thread = new Thread(runnable);
		thread.start();
	}
}
