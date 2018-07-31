package com.dianwoda.alg.orderpack;

public class AlgDistHaversine {
	// 计算近似距离
	static double distApprox(double lat1, double lng1, double lat2, double lng2) {
		return distH(lat1, lng1, lat2, lng2) * 1.3;
	}

	// 计算经纬度直线距离
	static double distH(double lat1, double lng1, double lat2, double lng2) {
		double phi1 = Math.toRadians(lat1);
		double phi2 = Math.toRadians(lat2);
		double lambda1 = Math.toRadians(lng1);
		double lambda2 = Math.toRadians(lng2);
		double dphi = phi2 - phi1;
		double dlambda = lambda2 - lambda1;

		double a = haversine(dphi) + Math.cos(phi1) * Math.cos(phi2) * haversine(dlambda);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		double d = radiusEarth * c;
		return d;
	}
	static double haversine(double angle) {
		double h = Math.sin(angle / 2) * Math.sin(angle / 2);
		return h;
	}

	private final static int radiusEarth = 6371393;
}
