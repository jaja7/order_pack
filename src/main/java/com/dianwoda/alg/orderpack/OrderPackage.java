package com.dianwoda.alg.orderpack;

import java.util.List;

public class OrderPackage {

	public double getScore() {
		if (score < 0) {
			this.scoreCal();
		}
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public List<AlgOrder> getOrders() {
		return orders;
	}
	public OrderPackage(List<AlgOrder> orders, double riderLat, double riderLng) {
		this.orders = orders;
		this.riderLat = riderLat;
		this.riderLng = riderLng;
		this.score = -1;
	}

	private void scoreCal() {
		long prioritySum = 0;
		double latSum = 0;
		double lngSum = 0;
		for (AlgOrder o: orders) {
			prioritySum += o.getPriority();
			latSum += o.getSrcLat();
			lngSum += o.getSrcLng();
		}
		double latCenter = latSum / orders.size();
		double lngCenter = lngSum / orders.size();
		double d = AlgDistHaversine.distH(latCenter, lngCenter, riderLat, riderLng);
		this.score = prioritySum * Math.sqrt(500.0 / Math.max(500.0, d));	
	}
	
	private double riderLat;
	private double riderLng;
	private double score;
  	private List<AlgOrder> orders;
}
