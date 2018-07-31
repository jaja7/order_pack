package com.dianwoda.alg.orderpack;

public class AlgRider {
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getSrcLat() {
		return srcLat;
	}
	public void setSrcLat(double srcLat) {
		this.srcLat = srcLat;
	}
	public double getSrcLng() {
		return srcLng;
	}
	public void setSrcLng(double srcLng) {
		this.srcLng = srcLng;
	}
	
	private long id;
	private double srcLat;
	private double srcLng;
}
