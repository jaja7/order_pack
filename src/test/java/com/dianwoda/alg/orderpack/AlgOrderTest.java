package com.dianwoda.alg.orderpack;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.*;
import ch.hsr.geohash.GeoHash;

import com.dianwoda.alg.orderpack.AlgOrder;

/**
 * Unit test for simple AlgOrder.
 */
public class AlgOrderTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void equal() {
        AlgOrder order = new AlgOrder(100, 30.1, 120.3, 30.2, 120.4); 
        assertEquals(order.getId(), 100);
    }
    @Test
	public void testKnownNeighbouringHashes() {
		GeoHash h1 = GeoHash.fromGeohashString("u1pb");

		assertEquals("sp2j", GeoHash.withCharacterPrecision(41.7, 0.08, 4).toBase32());
    }
    
    @Test
    public void geohashTest() {
        AlgOrder order = new AlgOrder(100, 30.1, 120.3, 30.2, 120.4);

        assertEquals("wtme1rx", order.getGeohash());
    }

    @Test
    public void geohashneighber() {
        AlgOrder order = new AlgOrder(100, 30.1, 120.3, 30.2, 120.4);
        List<String> nb = order.getGeohashNeighber();
        List<String> nbb2 = Arrays.asList("wtme1rz", "wtme1xb", "wtme1x8", "wtme1x2", "wtme1rr", "wtme1rq", "wtme1rw", "wtme1ry");
        assertEquals(nbb2, nb);
        //assertArrayEquals(nb, nbb2);
    }


    @Test
    public void test() throws Exception {

        AlgOrder order = new AlgOrder(100, 30.1, 120.3, 30.2, 120.4);
        assertEquals(order.getHashMod(), 24);
    }

    @Test
    public void angleHashTest() {
        AlgOrder order = new AlgOrder(100, 30.1, 120.3, 30.1, 119.5);
        //System.out.println(order.getAnglehash());
        assertEquals(order.getAnglehash(), 18);
    }

    @Test
    public void farTest() {
        AlgOrder order1 = new AlgOrder(100, 30.123456, 120.123456, 30.1, 119.5);
        AlgOrder order2 = new AlgOrder(100, 30.123732, 120.123444, 30.1, 119.5);
        order1.setFar(order2);
        System.out.println(Math.floor(order1.getFar() * 10));
        assertEquals((int)Math.floor(order1.getFar() * 10), 307);
    }
}