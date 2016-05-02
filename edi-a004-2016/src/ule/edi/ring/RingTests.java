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
		assertEquals(ring,other);
		
		
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
		Ring<Integer> ring = range(1,10);
		Iterator<Integer> iter = ring.forwardIterator();
		while(iter.hasNext()){
			iter.next();
		}
	}
	@Test (expected = UnsupportedOperationException.class)
	public void test04(){
		Ring<Integer> ring = range(1,10);
		Iterator<Integer> iter = ring.forwardIterator();
		while(iter.hasNext()){
			iter.remove();
		}
	}
	@Test
	public void test05(){
		Ring<Integer> ring_1 = range(1,20);
		Ring<Integer> ring_2 = range(1,20);
		for (int i = 1; i <= ring_1.nElements; i++) {
			assertEquals(ring_1.find(Ring.BACKWARDS, ring_1.reference(),i),ring_2.find(Ring.FORWARD, ring_1.reference(),i));
		}
		assertEquals(ring_1,ring_2);
		assertEquals(ring_1.toSequence(Ring.BACKWARDS),ring_2.toSequence(Ring.BACKWARDS));
		assertEquals(ring_1.toSequence(Ring.FORWARD),ring_2.toSequence(Ring.FORWARD));
		assertEquals(ring_1.toString(),ring_2.toString());
	}
	@Test
	public void test06(){
		
		Ring<Integer> ring = new Ring<Integer>();
		StringBuffer sb = new StringBuffer();
		
		for (int j = 0; j < 20; j++) {
			ring.insert(ring.reference(), Ring.FORWARD, j);
			sb.append(j);
			ring.insert(ring.reference(), Ring.BACKWARDS, j);
			sb.insert(0, j);
		}
		assertEquals(sb.toString(),ring.toSequence(Ring.BACKWARDS));
		assertEquals(sb.toString(),ring.toSequence(Ring.FORWARD));
		
		
	}
	@Test
	public void test07(){
		Ring<Integer> r = new Ring<Integer>();
		r.insert(r.reference(),Ring.FORWARD,0);
		r.insert(r.reference(),Ring.FORWARD,0);
		assertEquals(r.find(Ring.BACKWARDS, r.find(Ring.FORWARD, r.reference(), 0), 0).toString(),"(null)");
		
	}
	@Test
	public void test08(){
		Ring<Integer> ring = new Ring<Integer>();
		Iterator<Integer> iter = ring.iterator();
		while(iter.hasNext()){
			iter.next();
		}
	}
	@Test
	public void test09(){
		
	}		
}
