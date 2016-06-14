package ule.edi.hash;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

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
	public void test00(){
		TS.put("A","00");
		TS.put("AA", "01");
		TS.put("AAA", "02");
		TS.put("AAAA", "03");
		TS.put("AAAAA", "04");
		TS.put("AAAAAA", "05");
		TS.put("AAAAAAA", "06");		
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
		
		assertTrue(TS.contains("A"));
		assertTrue(TS.contains("B"));
		assertTrue(TS.contains("C"));
		assertTrue(TS.contains("N"));
		assertTrue(TS.contains("Q"));
		assertTrue(!TS.contains("Z"));
		assertEquals(TS.get("A"),"00");
		assertEquals(TS.get("B"),"01");
		assertEquals(TS.get("C"),"02");
		assertEquals(TS.get("Q"),"16");
		assertEquals(TS.get("K"),"10");
		try{
		assertEquals(TS.get("Z"),"yoksetiooooo");
		}catch(NoSuchElementException e){
			assertTrue(true);
		}
		TS.put("K","20");
		assertEquals("20",TS.get("K"));
		TS.put("AA","21");
		TS.put("AN","22");
		TS.put("NA","22");
		TS.put("NN","22");
		TS.put("NA","24");
		assertEquals(TS.get("NA"),"24");
		TS.put("AAA","25");
		TS.put("ANA","25");
		TS.put("NAN", "26");
		TS.put("NNN", "27");
		TS.put("AAAAAA", "1000");
		
		TS = new HashTableImpl<>(hLength,4, 4);
		TS.put("A","00");
		TS.put("B","01");
		TS.put("E","02");
		TS.put("I","03");
		TS.put("J","04");
		TS.put("AA","05");
		System.out.println(TS);
		TS.remove("B");
		System.out.println(TS);
	}

}
















