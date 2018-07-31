package com.dianwoda.alg.orderpack;

import java.util.*;
import static java.lang.Math.abs;

public class OrderPack {

	public static List<OrderPackage> orderPack(List<AlgOrder> orders, int capPackage, double riderLat, double riderLng) {
		//ordersForbid.clear();  // 清空禁忌表
		//packagesGroup.clear();  // 清空订单包
		for (AlgOrder oi: orders) {
			List<AlgOrder> order2Pack = new ArrayList<>();

			boolean hasPacked = false;
			for (AlgOrder ofbd: ordersForbid) {
				if (ofbd.getAnglehash() == oi.getAnglehash() && ofbd.getGeohash().equals(oi.getGeohash())) {
					hasPacked = true;
					break;
				}
			}
			if (hasPacked == true) {
				continue;
			}	
			ordersForbid.add(oi);		
			// 寻找相似的订单
			List<AlgOrder> orderSim = new ArrayList<>();
			for (AlgOrder oj: orders) {				
				// 将相似订单加入列表中
				if (isSimilar(oi, oj)){
					orderSim.add(oj);
				}
			}
			List<AlgOrder> orderSimSorted = sortByShop(oi, orderSim);
			if (orderSimSorted.size() > capPackage) {
				// 只取前面几个订单				
				order2Pack.addAll(orderSimSorted.subList(0, capPackage));
			}
			else {
				// 全部加入订单包
				order2Pack.addAll(orderSimSorted);
			}			
			// 打包并存入订单中
			OrderPackage oPackage = new OrderPackage(order2Pack, riderLat, riderLng);
			packagesGroup.add(oPackage);
		}
		return packagesGroup;
	}

	public List<OrderPackage> getOrderPackages() {
		return packagesGroup;
	}
	
	private static boolean isSimilar(AlgOrder orderA, AlgOrder orderB) {
		// 条件有两个，1、商家位置相近，2、方向相近
		int angleDiff = abs(orderA.getAnglehash() - orderB.getAnglehash());
		if (angleDiff > orderA.getHashMod() / 2) {
			angleDiff = orderA.getHashMod() - angleDiff;
		}
		if (angleDiff >= 2) {
			return false;
		}
		
		if (orderA.getGeohash().equals(orderB.getGeohash()) || orderA.getGeohashNeighber().contains(orderB.getGeohash())) {
			return true;
		}
		else {
			return false;
		}
	}

	
	private static List<AlgOrder> sortByShop(AlgOrder coreOrder, List<AlgOrder> orders) {
		// 为所有订单计算订单与核心订单的距离
		for (AlgOrder o: orders) {
			o.setFar(coreOrder);
		}
		Collections.sort(orders);
        return orders;
    }
	
	private static List<OrderPackage> packagesGroup = new ArrayList<OrderPackage>();
	private static List<AlgOrder> ordersForbid = new ArrayList<AlgOrder>();
}
