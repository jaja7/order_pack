package com.dianwoda.alg.orderpack;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;
import ch.hsr.geohash.GeoHash;

public class AlgOrder implements Comparable<AlgOrder> {

	public AlgOrder (long id, double srcLat, double srcLng, double dstLat, double dstLng) {
		this.id = id;
		this.srcLat = srcLat;
		this.srcLng = srcLng;
		this.dstLat = dstLat;
		this.dstLng = dstLng;
		this.geohash = GeoHash.withCharacterPrecision(srcLat, srcLng, 7);
		this.anglehash = angleHashCal(srcLat, srcLng, dstLat, dstLng);
		
	}
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
	public double getDstLat() {
		return dstLat;
	}
	public void setDstLat(double dstLat) {
		this.dstLat = dstLat;
	}
	public double getDstLng() {
		return dstLng;
	}
	public void setDstLng(double dstLng) {
		this.dstLng = dstLng;
	}
	
	public String getGeohash() {
		return geohash.toBase32();
	}
	public List<String> getGeohashNeighber() {
		GeoHash[] adjacent = geohash.getAdjacent();

		List<String> ans = new ArrayList<>();
		for (GeoHash adj: adjacent) {
			ans.add(adj.toBase32());
		}
		return ans;
	}
	public int getHashMod() {
		return hashMod;
	}
	public void setHashMod(int hashMod) {
		this.hashMod = hashMod;
	}
	public int getAnglehash() {
		return anglehash;
	}

	private int angleHashCal(double lat0, double lng0, double lat1, double lng1) {
		double deltaLat = lat1 - lat0;
		double deltaLng = lng1 - lng0;
		deltaLng *= Math.cos(Math.toRadians(lat0));

		double angle = Math.toDegrees((Math.atan2(deltaLng, deltaLat) + 2*Math.PI)%(2*Math.PI));
		return (int)Math.floor(angle/15);
	}

	@Override
	public int compareTo(AlgOrder orderB) {
		return (this.getFar() < orderB.getFar() ? -1 : (this.getFar() == orderB.getFar() ? 0 : 1));
	}

	/**
	 * @return the far
	 */
	public double getFar() {
		return far;
	}
	/**
	 * @param far the far to set
	 */
	public void setFar(AlgOrder orderCore) {
		this.far = AlgDistHaversine.distH(srcLat, srcLng, orderCore.getSrcLat(), orderCore.getSrcLng());
	}

	private long id;
	private double srcLat;
	private double srcLng;
	private double dstLat;
	private double dstLng;
	private GeoHash geohash;
	private int anglehash;
	private int hashMod = 24;
	private double far;
}
