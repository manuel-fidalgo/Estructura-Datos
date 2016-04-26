package ule.edi.ring;

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
		
}
