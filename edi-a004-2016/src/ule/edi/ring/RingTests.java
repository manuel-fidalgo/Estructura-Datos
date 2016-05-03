package ule.edi.ring;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import ule.edi.ring.Ring.Node;

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
		assertEquals(r.find(Ring.BACKWARDS, r.find(Ring.FORWARD, r.reference(), 0), 0).toString(),"(0)");
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
		Ring<Integer> ring = range(1,20);
		Node<Integer> aux = ring.reference();
		assertEquals(aux.toString(),"(null)");
		Node<Integer> decimo = null;
		for (int i = 1; i <= 20; i++) {
			aux = ring.find(Ring.FORWARD, aux, i);
			if(i==10) decimo = aux;
			assertEquals(aux.toString(),"("+i+")");
		}
		ring.insert(decimo, Ring.FORWARD, 37487345);
		assertEquals(ring.find(ring.FORWARD,decimo,37487345),decimo.next);
		assertEquals(ring.find(ring.BACKWARDS,decimo,37487345).content,new Integer(10));
	}
	@Test
	public void test10(){
		Ring<Integer> ring = range(1,8942);
		Ring<Integer> ring_ = range(1,2);
		Ring<Integer> ring_1 = new Ring<Integer>();
		Ring<Integer> ring_2 = new Ring<Integer>();

		assertFalse((ring.equals(new StringBuilder())));
		assertFalse((ring.equals(ring_)));
		assertTrue(ring_1.equals(ring_2));
	}
	@Test (expected = NoSuchElementException.class)
	public void test11(){
		Ring<Integer> ring = range(1,10);
		Iterator<Integer> iter = ring.iterator();
		while(true){
			iter.next();
		}
	}
	@Test
	public void test12(){
		Ring<Integer> ring = new Ring<Integer>();
		assertEquals(ring.toSequence(0),"");
	}
	@Test
	public void test13(){
		Ring<Character> x = new Ring<Character>();
		assertEquals(x,x);
		assertEquals(x.reference(),x.reference());
		assertTrue(x.isEmpty());
		//assertEquals(x);
		x.insert(x.reference(), Ring.FORWARD, 'A');
		assertEquals("> (R) <> A <> (R) <", x.toString());
		x.insert(x.reference(), Ring.FORWARD, 'B');
		assertEquals("> (R) <> B <> A <> (R) <", x.toString());
		x.insert(x.reference(), Ring.BACKWARDS, 'C');
		assertEquals("> (R) <> B <> A <> C <> (R) <", x.toString());
		x.insert(x.find(Ring.FORWARD, x.reference(), 'A'), Ring.BACKWARDS, 'D');
		assertEquals("> (R) <> B <> D <> A <> C <> (R) <", x.toString());
		assertEquals(x.nElements,4);
		x.remove(x.reference().next);
		assertEquals(x.nElements,3);
		//
	}
	@Test
	public void test14(){
		Ring<Integer> r = new Ring<Integer>();
		assertEquals("(null)",r.find(0, r.reference(),2).toString());
		assertEquals("(null)",r.find(0, r.reference(),null).toString());
		assertEquals("(null)",r.find(0, new Node<Integer>(3),4).toString());
		assertEquals("(null)",r.find(0, new Node<Integer>(3).next = new Node<Integer>(0) ,2).toString());
		r = range(1,10);
		Node<Integer> aux = r.find(Ring.FORWARD, r.reference(), 5);
		assertEquals(aux.content,new Integer(5));
		aux = r.find(Ring.FORWARD, aux, 5);
		assertEquals(aux.content,new Integer(5));
		aux = r.find(Ring.FORWARD, aux, 6);
		assertEquals(aux.content,new Integer(6));
		aux = r.find(Ring.FORWARD, aux, 7);
		assertEquals(aux.content,new Integer(7));
		aux = r.find(Ring.BACKWARDS, aux, 8);
		assertEquals(aux.content,new Integer(7));
		aux = r.find(Ring.BACKWARDS, aux, 1);
		assertEquals(aux,r.reference().next);
		aux = r.find(Ring.BACKWARDS, aux, 1);
		assertEquals(aux,r.reference().next);
		aux = r.find(Ring.FORWARD, aux, 10);
		assertEquals(aux,r.reference().previous);
		r = new Ring<Integer>();
		aux = r.find(Ring.FORWARD, aux, 3);
		System.out.println(aux);
		
	}
	/**
	 TEST DE EVA! 
	 */
	@Test
	public void testNodoToString(){
		Ring<Integer> x = new Ring<Integer>();
		Assert.assertEquals("(null)", x.reference().toString());
	}

	@Test
	public void testFind(){
		Ring<Integer> x = range(1, 4);
		Assert.assertEquals(x.reference(), x.find(Ring.FORWARD, x.reference(), x.reference().content));
		Assert.assertEquals("(2)", x.find(Ring.BACKWARDS, x.reference(), 2).toString());
		Assert.assertEquals("(2)", x.find(Ring.FORWARD, x.reference().next, 2).toString());
		Assert.assertEquals("(3)", x.find(Ring.BACKWARDS, x.reference().previous.previous, 3).toString());
		Assert.assertEquals("(3)", x.find(Ring.FORWARD, x.reference().previous.previous, 3).toString());
		Assert.assertEquals("(null)" , x.find(Ring.FORWARD, x.reference(), 8).toString());
	}

	@Test
	public void testInsert(){

		Ring<Integer> rx = new Ring<Integer>();
		for (int i = 1; i <= 5; ++i) {
			rx.insert(rx.reference(), Ring.FORWARD, i);
		}
		Assert.assertEquals("54321", rx.toSequence(Ring.FORWARD));
		Assert.assertEquals("12345", rx.toSequence(Ring.BACKWARDS));

		Ring<Integer> rx1 = new Ring<Integer>();
		for (int i = 1; i <= 5; ++i) {
			rx1.insert(rx1.reference(), Ring.BACKWARDS, i);
		}
		Assert.assertEquals("54321", rx1.toSequence(Ring.BACKWARDS));
	}

	@Test
	public void testToString(){
		Ring<Integer> rx = new Ring<Integer>();
		for (int i = 1; i <= 5; ++i) {
			rx.insert(rx.reference(), Ring.FORWARD, i);
		}
		Assert.assertEquals("> (R) <> 5 <> 4 <> 3 <> 2 <> 1 <> (R) <",  rx.toString());
	}
	@Test
	public void testIterador(){
		Ring<Integer> rx = new Ring<Integer>();
		for (int i = 1; i <= 5; ++i) {
			rx.insert(rx.reference(), Ring.FORWARD, i);
		}
		Iterator<Integer> iter = rx.iterator();
		Iterator<Integer> iterBack = rx.backwardsIterator();
		Iterator<Integer> iterFor = rx.forwardIterator();
		String cadena = "";
		while(iter.hasNext()){
			cadena = cadena + iter.next().toString();
		}
		Assert.assertEquals("54321", cadena);
	}

	@Test (expected = NoSuchElementException.class)
	public void testIteratortException1() throws NoSuchElementException{
		Ring<Integer> rx = new Ring<Integer>();
		for (int i = 1; i <= 5; ++i) {
			rx.insert(rx.reference(), Ring.FORWARD, i);
		}
		Iterator<Integer> iter = rx.iterator();
		while(iter.hasNext()){
			iter.next();
		}
		iter.next();
	}

	@Test (expected = UnsupportedOperationException.class)
	public void testIteratortException2() throws UnsupportedOperationException{
		Ring<Integer> rx = new Ring<Integer>();
		for (int i = 1; i <= 5; ++i) {
			rx.insert(rx.reference(), Ring.FORWARD, i);
		}
		Iterator<Integer> iter = rx.iterator();
		iter.remove();
	}
	@Test
	public void testEqualsRingEmpty_() {

		Ring<Integer> x = new Ring<Integer>();
		Ring<Integer> z = new Ring<Integer>();

		Assert.assertTrue(x.equals(z));
		Assert.assertTrue(z.equals(x));
		z.insert(z.reference(), Ring.BACKWARDS, 8);
		Assert.assertFalse(z.equals(x));
		Assert.assertEquals(0, x.size());

	}

	@Test
	public void testRemove() {

		Ring<Integer> x = range(1, 4);
		x.remove(x.reference());
		Assert.assertEquals("1234", x.toSequence(Ring.FORWARD));
		x.remove(x.find(Ring.FORWARD, x.reference(), 1));
		Assert.assertEquals("234", x.toSequence(Ring.FORWARD));

		Assert.assertEquals(3, x.size());
	}

}
