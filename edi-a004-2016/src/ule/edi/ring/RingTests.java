package ule.edi.ring;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Test;

public class RingTests {

	private Ring<Integer> range(int a, int b) {
		
		Ring<Integer> rx = new Ring<Integer>();
		for (int i = a; i <= b; ++i) {
			rx.insert(rx.reference(), Ring.BACKWARDS, i);
		}
		
		return rx;
	}
	
	@Test
	public void testEqualsRingEmpty() {
		
		Ring<Integer> x = new Ring<Integer>();
		Ring<Integer> z = new Ring<Integer>();
		
		Assert.assertTrue(x.equals(z));
		Assert.assertTrue(z.equals(x));
	
	}

	@Test
	public void testRemoveFirst() {
		
		Ring<Integer> x = range(1, 4);
		
		x.remove(x.find(Ring.FORWARD, x.reference(), 1));
		Assert.assertEquals("234", x.toSequence(Ring.FORWARD));
		
		Assert.assertEquals(3, x.size());
	}
	@Test
	public void test00(){
		int TAM = 100;
		Ring<Integer> ring = range(1,TAM);
		for (int i = 1; i < TAM; i++) {
			assertEquals(ring.find(Ring.FORWARD,ring.reference(),i).content,new Integer(i));
		}
		for (int i = 1; i < TAM; i++) {
			ring.remove(ring.reference().next);
		}
		ring.remove(ring.reference().next);
		assertTrue(ring.nElements==0);
	}
	@Test
	public void test01(){
		Ring<Integer> ring = range(1,10);
		
		Ring<Integer> other = new Ring<Integer>();
		
		
		Iterator<Integer> iter = ring.forwardIterator();
		
		
		while(iter.hasNext()){
			other.insert(other.reference(), Ring.BACKWARDS, iter.next());
		}
		assertTrue(ring.equals(other));
		//assertEquals(ring,other);
		
		
	}
	@Test
	public void test02(){
		Ring<Integer> ring = range(1,10);
		Ring<Integer> other_ = new Ring<Integer>();
		
		Iterator<Integer> iter_ = ring.backwardsIterator();
		
		while(iter_.hasNext()){
			other_.insert(other_.reference(), Ring.FORWARD, iter_.next());
		}
		assertEquals(ring,other_);
	}
	@Test
	public void test03(){
		
	}
	@Test
	public void test04(){
		
	}
	@Test
	public void test05(){
		
	}
	@Test
	public void test06(){
		
	}
	@Test
	public void test07(){
		
	}
	@Test
	public void test08(){
		
	}
	@Test
	public void test09(){
		
	}		
}
