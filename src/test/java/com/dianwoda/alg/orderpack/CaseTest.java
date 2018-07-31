package com.dianwoda.alg.orderpack;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.CSVFormat;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.apache.commons.csv.CSVPrinter;

/**
 * Unit test for simple App.
 */
public class CaseTest {
    /**
     * Rigorous Test :-)
     * @throws IOException
     */
    @Test
    public void shouldAnswerWithTrue() throws IOException
    {
        CaseTest caseTest = new CaseTest();
        List<AlgOrder> orders = caseTest.getOrders();

        List<OrderPackage> ordrpackage = OrderPack.orderPack(orders, 10);
        caseTest.writeOrders(ordrpackage);

        assertTrue( true );
    }

    public List<AlgOrder> getOrders() throws IOException {
		Reader in = new FileReader("D:/proj_派单策略/打包算法/order_pack_java_测试用例生成及验证/orderList.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        //CSVFormat.DEFAULT
        List<AlgOrder> orderList = new ArrayList<AlgOrder>();
        long idx = 0;
        for (CSVRecord record : records) {
            //id,srcLat,srcLng,dstLat,dstLng
            AlgOrder orderTmp = new AlgOrder( 
                    Integer.parseInt(record.get("id")), 
                    Double.parseDouble(record.get("srcLat")), 
                    Double.parseDouble(record.get("srcLng")),
                    Double.parseDouble(record.get("dstLat")), 
                    Double.parseDouble(record.get("dstLng")));
        	idx += 1;
            //System.out.println(orderTmp.getId());
            //System.out.println(orderTmp.getLat());
            orderList.add(orderTmp);
        }
        System.out.println(idx);
        return orderList;
	}
	

	public void writeOrders( List<OrderPackage> ordrpackage) throws IOException {

        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get("D:/proj_派单策略/打包算法/order_pack_java_测试用例生成及验证/orderPackages.csv"));
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
                    .withHeader("orderId","getGeohash", "getAnglehash", "orderSrcLat", "orderSrcLng", "orderDstLat", "orderDstLng", "groupId"));
        ) {
        	int groupId = 0;
        	for (OrderPackage orders: ordrpackage) {
        		for (AlgOrder order: orders.getOrders()) {
        			csvPrinter.printRecord(order.getId(), order.getGeohash(), order.getAnglehash(),  order.getSrcLat(), order.getSrcLng(), order.getDstLat(), order.getDstLng(), groupId);
        		}
        		groupId += 1;
    		}
            csvPrinter.flush();
        }
    }
}