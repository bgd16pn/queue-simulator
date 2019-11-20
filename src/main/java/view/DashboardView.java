package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.ScrollPane;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class DashboardView extends JFrame {

	private static final long serialVersionUID = 1737881595502454295L;

	private static final Font tahoma13bold = new Font("Tahoma", Font.BOLD, 15);

	private static final int WINDOW_WIDTH = 1017;
	private static final int WINDOW_HEIGHT = 835;

	private static final ImageIcon waitingIcon = new ImageIcon("src\\main\\resources\\icons\\empty-circle.png");
	private static final ImageIcon processingIcon = new ImageIcon("src\\main\\resources\\icons\\filled-circle.png");

	private JLabel[][] buttonIcons;
	private JLabel[] lineLabels;

	private JButton btnCancelSim;
	private JButton btnRerunSim;
	private JProgressBar progressBar;

	private JTextArea loggerTextArea;

	public DashboardView(Dimension screenSize) {

		this.setBounds((int) (screenSize.getWidth() / 2.0) - (int) (WINDOW_WIDTH / 2.0),
				(int) (screenSize.getHeight() / 2.0) - (int) (WINDOW_HEIGHT / 2.0), WINDOW_WIDTH, WINDOW_HEIGHT);

		this.setResizable(false);
		this.setTitle("Queue manager simulator");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		buttonIcons = new JLabel[5][12];
		lineLabels = new JLabel[5];

		lineLabels = generateLineLabels(92);
		setLineLabelsVisible(false);

		buttonIcons = generateButtonIcons(80);
		setButtonIconsVisible(false);

		generateHorizontalSeparators();
		generateVerticalSeparators();

		progressBar = new JProgressBar();
		progressBar.setMinimum(0);
		progressBar.setMaximum(100);
		progressBar.setStringPainted(true);
		progressBar.setBounds(141, 365, 727, 23);
		getContentPane().add(progressBar);

		JSeparator sprtrLog = new JSeparator();
		sprtrLog.setBounds(52, 439, 907, 10);
		getContentPane().add(sprtrLog);

		JLabel lblLog = new JLabel("Log");
		lblLog.setFont(tahoma13bold);
		lblLog.setBounds(55, 448, 56, 16);
		getContentPane().add(lblLog);

		loggerTextArea = new JTextArea();
		loggerTextArea.setFont(loggerTextArea.getFont().deriveFont(15f));
		loggerTextArea.setEditable(false);

		ScrollPane scrollPane = new ScrollPane();
		scrollPane.add(loggerTextArea);
		scrollPane.setBounds(52, 470, 907, 303);
		getContentPane().add(scrollPane);

		btnCancelSim = new JButton("Cancel");
		btnCancelSim.setBounds(454, 401, 97, 25);
		getContentPane().add(btnCancelSim);

		btnRerunSim = new JButton("Rerun");
		btnRerunSim.setBounds(454, 401, 97, 25);
		getContentPane().add(btnRerunSim);
		btnRerunSim.setVisible(false);

	}

	private void generateVerticalSeparators() {
		JSeparator sprtrSide1 = new JSeparator();
		sprtrSide1.setOrientation(SwingConstants.VERTICAL);
		sprtrSide1.setBounds(197, 71, 7, 256);
		getContentPane().add(sprtrSide1);

		JSeparator sprtrSide2 = new JSeparator();
		sprtrSide2.setOrientation(SwingConstants.VERTICAL);
		sprtrSide2.setBounds(753, 71, 7, 256);
		getContentPane().add(sprtrSide2);

		JSeparator sprtrSide3 = new JSeparator();
		sprtrSide3.setOrientation(SwingConstants.VERTICAL);
		sprtrSide3.setBounds(816, 71, 7, 256);
		getContentPane().add(sprtrSide3);
	}

	private void generateHorizontalSeparators() {
		for (int i = 0; i < 6; i++) {
			JSeparator sprtr1 = new JSeparator();
			sprtr1.setBounds(187, 71 + 52 * i, 636, 10);
			getContentPane().add(sprtr1);
		}
	}

	private JLabel[] generateLineLabels(int startHeight) {

		JLabel[] lblLines = new JLabel[5];
		for (int i = 0; i < 5; i++) {
			lblLines[i] = new JLabel(String.valueOf(i + 1));
			lblLines[i].setFont(tahoma13bold);
			lblLines[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblLines[i].setBounds(129, startHeight + i * 50, 56, 16);
			getContentPane().add(lblLines[i]);
		}
		return lblLines;
	}

	private JLabel[][] generateButtonIcons(int startHeight) {
		JLabel[][] lblClient = new JLabel[5][12];

		for (int i = 0; i < 5; i++) {
			lblClient[i][0] = new JLabel("");
			lblClient[i][0].setIcon(processingIcon);
			lblClient[i][0].setBounds(768, startHeight + 52 * i, 36, 36);
			getContentPane().add(lblClient[i][0]);

			for (int j = 1; j < 12; j++) {
				lblClient[i][j] = new JLabel("");
				lblClient[i][j].setIcon(waitingIcon);
				lblClient[i][j].setBounds(750 - j * 48, startHeight + 52 * i, 36, 36);
				getContentPane().add(lblClient[i][j]);
			}
		}
		return lblClient;
	}

	public JLabel[][] getButtonIcons() {
		return buttonIcons;
	}

	public void setButtonIconsVisible(boolean flag) {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 12; j++) {
				buttonIcons[i][j].setVisible(flag);
			}
		}
	}

	public void setLineLabelsVisible(boolean flag) {
		for (int i = 0; i < 5; i++) {
			lineLabels[i].setVisible(flag);
		}
	}

	public boolean isButtonIconVisible(int row, int col) {
		return buttonIcons[row][col].isVisible();
	}

	public void setButtonIconVisible(int row, int col, boolean flag) {
		buttonIcons[row][col].setVisible(flag);
	}

	public void setLineLabelVisible(int row, boolean flag) {
		lineLabels[row].setVisible(flag);
	}

	public void setProgressBarValue(int value) {
		progressBar.setValue(value);
	}

	public void setProgressBarLimits(int min, int max) {
		progressBar.setMinimum(min);
		progressBar.setMaximum(max);
	}

	public void appendLoggerTextArea(String str) {
		loggerTextArea.append(str);
	}

	public void setLoggerTextArea(String str) {
		loggerTextArea.setText(str);
	}

	public void setRerunButtonVisible(boolean flag) {
		btnRerunSim.setVisible(flag);
	}

	public void addCancelButtonActionListener(ActionListener actionListener) {
		btnCancelSim.addActionListener(actionListener);
	}

	public void addRerunButtonActionListener(ActionListener actionListener) {
		btnRerunSim.addActionListener(actionListener);
	}

	public void setCancelButtonVisible(boolean flag) {
		btnCancelSim.setVisible(flag);
	}

	public void updateLog() {
		loggerTextArea.repaint();
	}
}
