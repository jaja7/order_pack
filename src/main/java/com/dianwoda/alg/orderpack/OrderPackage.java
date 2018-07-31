package com.dianwoda.alg.orderpack;

import java.util.List;

public class OrderPackage {

	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public List<AlgOrder> getOrders() {
		return orders;
	}
	public OrderPackage(List<AlgOrder> orders) {
		this.orders = orders;
	}
	
	private double score;
  	private List<AlgOrder> orders;
}
