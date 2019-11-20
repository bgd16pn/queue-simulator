package model;

public class Client {
	private int id;

	private int startTime;
	private int arrivalTime;
	private int processTime;
	private int waitingTime;
	private int finishTime;

	private int serviceLine;

	public Client(int id, int arrivalTime, int processTime) {
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.processTime = processTime;
		startTime = 0;
		waitingTime = 0;
		finishTime = 0;
		serviceLine = -1;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(int finishTime) {
		this.finishTime = finishTime;
	}

	public int getWaitingTime() {
		return waitingTime;
	}

	public void setWaitingTime(int waitingTime) {
		this.waitingTime = waitingTime;
	}

	public int getStartTime() {
		return startTime;
	}

	public void setStartTime(int startTime) {
		this.startTime = startTime;
	}

	public int getProcessTime() {
		return processTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public void setProcessTime(int processTime) {
		this.processTime = processTime;
	}

	public int getServiceLine() {
		return serviceLine;
	}

	public void setServiceLine(int serviceLine) {
		this.serviceLine = serviceLine;
	}

	@Override
	public String toString() {
		return "Id = " + id + ", Arrival = " + arrivalTime + ", Starting = " + startTime + ", Process = " + processTime
				+ ", Waiting = " + waitingTime + ", Finish = " + finishTime + ", Q" + serviceLine;
	}

}
