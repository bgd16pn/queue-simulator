package model;

public class Stat {

	private int lineId;
	private int time;
	private int processedEntities;
	private int totalEntities;

	private double avgProcTime;
	private double avgWaitTime;

	private int totalWaitTime;
	private int totalProcessTime;

	private int totalEmptyTime;
	private int totalQueueTime;

	private int peakHour;

	public Stat() {
		lineId = -1;
		totalProcessTime = 0;
		totalWaitTime = 0;
		totalEmptyTime = 0;
		totalQueueTime = 0;
		processedEntities = 0;
		totalEntities = 0;
		avgProcTime = 0;
		avgWaitTime = 0;
		peakHour = 0;
		time = 0;
	}

	@Override
	public String toString() {
		return "STAT: Q" + lineId + ": AvgProcTime = " + String.format("%.2f", avgProcTime) + "s, AvgWaitTime = "
				+ String.format("%.2f", avgWaitTime) + "s, Empty time = " + totalEmptyTime + ", ProcessedEntities = "
				+ processedEntities + ", TotalEntities = " + totalEntities + ", PeakHour = " + peakHour + ", Time = "
				+ time + "s";
	}

	public int getTotalProcessTime() {
		return totalProcessTime;
	}

	public int getTotalWaitTime() {
		return totalWaitTime;
	}

	public int getTotalEmptyTime() {
		return totalEmptyTime;
	}

	public int getTotalQueueTime() {
		return totalQueueTime;
	}

	public int getTotalEntities() {
		return totalEntities;
	}

	public double getAvgProcTime() {
		return avgProcTime;
	}

	public double getAvgWaitTime() {
		return avgWaitTime;
	}

	public void setTotalProcessTime(int totalProcessTime) {
		this.totalProcessTime = totalProcessTime;
	}

	public void setTotalWaitTime(int totalWaitTime) {
		this.totalWaitTime = totalWaitTime;
	}

	public void setTotalEmptyTime(int totalEmptyTime) {
		this.totalEmptyTime = totalEmptyTime;
	}

	public void setTotalQueueTime(int totalQueueTime) {
		this.totalQueueTime = totalQueueTime;
	}

	public void setTotalEntities(int numberOfEntities) {
		this.totalEntities = numberOfEntities;
	}

	public void computeAvgProcTime() {
		avgProcTime = 1.0 * totalProcessTime / processedEntities;
	}

	public void computeAvgWaitTime() {
		avgWaitTime = 1.0 * totalWaitTime / processedEntities;
	}

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int id) {
		lineId = id;
	}

	public int getPeakHour() {
		return peakHour;
	}

	public void setPeakHour(int peakHour) {
		this.peakHour = peakHour;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getProcessedEntities() {
		return processedEntities;
	}

	public void setProcessedEntities(int processedEntities) {
		this.processedEntities = processedEntities;
	}

	public Stat duplicate() {
		Stat newStat = new Stat();
		newStat.lineId = lineId;
		newStat.totalEntities = totalEntities;
		newStat.processedEntities = processedEntities;
		newStat.time = time;
		newStat.totalEmptyTime = totalEmptyTime;
		newStat.totalProcessTime = totalProcessTime;
		newStat.totalQueueTime = totalQueueTime;
		newStat.totalWaitTime = totalWaitTime;
		newStat.peakHour = peakHour;

		if (processedEntities != 0) {
			newStat.avgProcTime = 1.0 * totalProcessTime / processedEntities;
			newStat.avgWaitTime = 1.0 * totalWaitTime / processedEntities;
		}

		return newStat;
	}
}
