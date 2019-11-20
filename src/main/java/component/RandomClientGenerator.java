package component;

import java.util.Queue;
import java.util.Random;

import model.Client;

public class RandomClientGenerator {

	private static final Random rand = new Random();

	private RandomClientGenerator() {

	}

	public static void generateEntities(Queue<Client> pool, int minProcTime, int maxProcTime, int minArrTime,
			int maxArrTime, int simTime) {
		int currentArrTime = 0;

		for (int i = 0; currentArrTime + minProcTime <= simTime; i++) {
			int newArrTime = rand.nextInt(maxArrTime) + minArrTime;
			currentArrTime += newArrTime;
			int newProcTime = rand.nextInt(maxProcTime) + minProcTime;
			Client e = new Client(i, currentArrTime, newProcTime);
			e.setStartTime(currentArrTime);
			pool.add(e);
		}
	}

}
