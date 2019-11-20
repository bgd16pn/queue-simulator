package application;

import controller.SetupController;

public class MainClass {
	public static void main(String[] args) {
		new SetupController().start();
		
//		Clock.getInstance().start();
//		SwingClock.getInstance().start();
//		SwingWorkerClock.getInstance().run();
	}
}
