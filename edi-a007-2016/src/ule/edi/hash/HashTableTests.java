package ule.edi.hash;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ule.edi.hash.HashTableImpl.HashFunction;

public class HashTableTests {

	private HashTableImpl<String, String> TS;
	
	//	Mala función de hash, pero útil para los tests
	private static final HashFunction<String> hLength =  new HashFunction<String>() {

		@Override
		public int apply(int n, String g) {

			return (g.length() % n);
		}
	};

	@Before
	public void setupTables() {
		
		TS = new HashTableImpl<String, String>(hLength, 3, 3);
	}
	
	private String key(int n) {
		
		return String.format("K%03d", n);
	}
	
	private String value(int n) {
		
		return String.format("V%03d", n);
	}
	
	private void put(HashTableImpl<String, String> t, int n) {
		
		t.put(key(n), value(n));
	}
	
	@Test
	public void testPrinting() {
		/*
		TS.put("A", "0");
		TS.put("A", "1");
		TS.put("B", "2");
		TS.put("D", "3");
		TS.put("G", "4");
		TS.put("J", "5");
		TS.put("Z", "6");
		System.out.println(TS);
		*/
	}
	@Test
	public void test00(){
		TS = new HashTableImpl<String,String>();
		TS.put("A","00");
		TS.put("B","01");
		TS.put("C","02");
		TS.put("D","03");
		TS.put("E","04");
		TS.put("F","05");
		TS.put("G","06");
		TS.put("H","07");
		TS.put("I","08");
		TS.put("J","09");
		TS.put("K","10");
		TS.put("L","11");
		TS.put("M","12");
		TS.put("N","13");
		TS.put("O","14");
		TS.put("P","15");
		TS.put("Q","16");
		TS.put("R","17");
		TS.put("F","18");
		System.out.println(TS);
		
	}

}
















