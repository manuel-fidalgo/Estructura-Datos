package ule.edi.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import ule.edi.EmptyCollectionException;

public class UnorderedSingleLinkedListTests {

	private UnorderedSingleLinkedList<Number> lN1;
	
	@Before
	public void setupLists() {
		
		this.lN1 = new UnorderedSingleLinkedList<Number>();
	}
	
	//	JUnit no executa los tests anotados con @Ignore
	
	@Test
	public void testDistinct() {
	
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		
		Assert.assertEquals(5L, lN1.size());
		
		//	distinct es un método estático de 'utilidad'
		UnorderedListADT<Number> dlN1 = UnorderedSingleLinkedList.distinct(lN1);
		
		Assert.assertEquals(2L, dlN1.size());
		Assert.assertTrue(dlN1.contains(1));
		Assert.assertTrue(dlN1.contains(2));
	}
	
	@Test
	public void testRangedIterator() {
	
		UnorderedListADT<Number> lN2;
		
		for (int i = 0; i < 32; i++) {
			lN1.addToRear(Integer.valueOf(i));
		}

		//	listWith es un método estático
		lN2 = UnorderedSingleLinkedList.listWith(lN1.rangedIterator(1, 16, 4));
		Assert.assertEquals("[0, 4, 8, 12]", lN2.toString());		
		
	}
	
	@Test
	public void testDistinct_old() {
	
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		
		Assert.assertEquals(5L, lN1.size());
		
		//	distinct es un método estático de 'utilidad'
		UnorderedListADT<Number> dlN1 = UnorderedSingleLinkedList.distinct(lN1);
		
		Assert.assertEquals(2L, dlN1.size());
		Assert.assertTrue(dlN1.contains(1));
		Assert.assertTrue(dlN1.contains(2));
	}
	@Test
	public void testRangedIterator_old() {
	
		UnorderedListADT<Number> lN2;
		
		for (int i = 0; i < 32; i++) {
			lN1.addToRear(Integer.valueOf(i));
		}
		//	listWith es un método estático
		// 0 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18
		lN2 = UnorderedSingleLinkedList.listWith(lN1.rangedIterator(1, 16, 4));
		Assert.assertEquals("[0, 4, 8, 12]", lN2.toString());		
		
	}
	@Test
	public void test00(){
		for (int i = 0; i < 10; i++) {
			lN1.addToRear(i);
			assertEquals(lN1.size(),i+1);
		}
		for (int i = 0; i < 10; i++) {
			try {
				lN1.remove(i);
				assertEquals(lN1.size(),9-i);
			} catch (EmptyCollectionException e) {
				
			}
		}
		for (int i = 0; i < 10; i++) {
			lN1.addToFront(i);
			assertEquals(lN1.size(),i+1);
		}for (int i = 0; i < 10; i++) {
			try {
				lN1.remove(i);
				assertEquals(lN1.size(),9-i);
			} catch (EmptyCollectionException e) {
				
			}
		}
	}
	
	@Test
	public void test01(){
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		Assert.assertEquals(lN1.toString(),lN1.toString());
	}
	@Test
	public void test02(){
		for (int i = 0; i < 230; i++) {
			lN1.addToFront(i);
			try {
				Assert.assertEquals(lN1.getFirst(),i);
				Assert.assertEquals(lN1.size(), i+1);
			} catch (EmptyCollectionException e) {
				Assert.assertEquals(e.getMessage(),e.getMessage());
			}
		}
		
		for (int i = 0; i < 230; i++) {
			lN1.removeElementAt(1);
			try {
				Assert.assertEquals(lN1.getLast(),0);
			} catch (EmptyCollectionException e) {
				Assert.assertEquals(e.getMessage(),e.getMessage());
			}
		}
		
	}
	@Test
	public void test03(){
		for (int i = 0; i < 230; i++) {
			lN1.addToFront(i);
			try {
				Assert.assertEquals(lN1.getFirst(),i);
			} catch (EmptyCollectionException e) {
				Assert.assertEquals(e.getMessage(),e.getMessage());
			}
		}
		int size=230;
		for (int i = 0; i < 230; i++) {
			try {
				Number e = lN1.remove(i);
				size--;
				Assert.assertEquals(size,lN1.size());
				Assert.assertEquals(e,i);
			} catch (EmptyCollectionException e) {
				if(lN1.size()==0){
					Assert.assertEquals(e.getMessage(),e.getMessage());
				}else{
					Assert.assertTrue(false);
				}
			}
		}
	}
	@Test(expected = NoSuchElementException.class)
	public void test04() throws NoSuchElementException, EmptyCollectionException{
		lN1.addToRear(1);
		lN1.remove(110);
	}
	@Test(expected = EmptyCollectionException.class)
	public void test05() throws NoSuchElementException, EmptyCollectionException{
		lN1.remove(110);
	}
	@Test(expected = EmptyCollectionException.class)
	public void test06() throws EmptyCollectionException{
		lN1.getFirst();
	}
	@Test(expected = EmptyCollectionException.class)
	public void test07() throws EmptyCollectionException{
		lN1.getLast();
	}
	@Test
	public void test08() throws EmptyCollectionException{
		lN1.addToFront(0);
		lN1.addToRear(1);
		Assert.assertEquals(lN1.size(),2);
		lN1.removeFirst();
		lN1.removeLast();
		Assert.assertEquals(0,lN1.size());
	}
	@Test
	public void test09(){
		
		Assert.assertEquals(true,true);
	}
	@Test (expected = EmptyCollectionException.class)
	public void test10() throws EmptyCollectionException{
		lN1.addToFront(0);
		lN1.addToRear(1);
		lN1.removeFirst();
		lN1.removeLast();
		Assert.assertEquals(lN1.size(),0);
		lN1.removeFirst();
		lN1.removeLast();
	}
	@Test
	public void test11(){
		Number[] ar = {1,2,3,4,3,2,3,4,5,6};
		Number[] arr = {6,5,4,3,2,3,4,3,2,1};
		Number[] noDupls = {1,2,3,4,5,6};
		
		UnorderedSingleLinkedList<Number> lista = new UnorderedSingleLinkedList<>(ar);
		Assert.assertEquals(new UnorderedSingleLinkedList<Number>(arr).toString(),UnorderedSingleLinkedList.reverse(lista).toString());
		Assert.assertEquals(new UnorderedSingleLinkedList<Number>(noDupls).toString(),UnorderedSingleLinkedList.distinct(lista).toString());
	}
	@Test
	public void test12(){
		for (int i = 0; i < 10; i++) {
			lN1.addToRear(i);
		}
		for (int i = 0; i < 10; i++) {
			lN1.replaceElementAt(i+1,100);
			Assert.assertEquals(lN1.getElementAt(i+1),100);
		}		
	}
	@Test
	public void test13(){
		for (int i = 0; i < 10; i++)
			lN1.addToRear(i);
		
		Iterator<Number> iterador = lN1.rangedIterator(3, 9, 3);
		int i=1;
		for(;iterador.hasNext();i++){
			Number next = iterador.next();
			switch(i){
			case 1:
				Assert.assertEquals(2, next);
				break;
			case 2:
				Assert.assertEquals(5, next);
				break;
			case 3:
				Assert.assertEquals(8, next);
				break;
			}
		}
	}
	@Test (expected = EmptyCollectionException.class)
	public void test14() throws EmptyCollectionException{
		lN1.removeLast();
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test15() throws EmptyCollectionException{
		throw new IndexOutOfBoundsException();
	}
	@Test 
	public void test16(){
		int i;
		for (i = 0; i < 10; i++) 
			lN1.addToRear(i);
		
		Iterator<Number> iter = lN1.iterator();
		for (i = 0; iter.hasNext(); i++) {
			Assert.assertEquals(i,iter.next());
		}
		Iterator<Number> skipping_iter = lN1.skippingIterator();
		Assert.assertEquals(0, skipping_iter.next());
		Assert.assertEquals(2, skipping_iter.next());
		Assert.assertEquals(4, skipping_iter.next());
		Assert.assertEquals(6, skipping_iter.next());
		Assert.assertEquals(8, skipping_iter.next());
		
	}
	@Test
	public void test17(){
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(3);
		lN1.addToRear(4);
		Assert.assertEquals("[1, 2, 3, 4]",lN1.toString());
	}
	@Test
	public void test18() throws EmptyCollectionException{
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(3);
		lN1.addToRear(4);
		for (int i = 0; i < 4; i++) {
			lN1.removeFirst();
		}
		Assert.assertEquals(lN1.size(),0);
	}
	@Test (expected = EmptyCollectionException.class)
	public void test19() throws EmptyCollectionException{
		lN1.removeFirst();
	}
	@Test
	public void test20(){
		for(int i=0; i<100; i++){
			lN1.addToRear(i+1);
		}
		Iterator<Number> iter = lN1.iterator();
		for(int j=0;j<100;j++){
			if(iter.hasNext()){
				Assert.assertTrue(true);
			}else{
				Assert.assertTrue(false);
			}
			Assert.assertEquals(j+1,iter.next());
		}
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void test21(){
		Iterator<Number> iter = lN1.iterator();
		iter.remove();
	}
	@Test (expected = UnsupportedOperationException.class)
	public void test22(){
		Iterator<Number> iter = lN1.rangedIterator(1, 4, 2);
		iter.remove();
	}
	@Test (expected = UnsupportedOperationException.class)
	public void test23(){
		Iterator<Number> iter = lN1.skippingIterator();
		iter.remove();
	}
	
	@Test (expected = NoSuchElementException.class)
	public void test24(){
		UnorderedSingleLinkedList<Number> LN2 = new UnorderedSingleLinkedList<Number>();
		Iterator<Number> iter = LN2.skippingIterator();
		iter.next();
	}
	@Test (expected = NoSuchElementException.class)
	public void test25(){
		UnorderedSingleLinkedList<Number> LN2 = new UnorderedSingleLinkedList<Number>();
		Iterator<Number> iter = LN2.iterator();
		iter.next();
	}
	@Test (expected = NoSuchElementException.class)
	public void test26(){
		UnorderedSingleLinkedList<Number> LN2 = new UnorderedSingleLinkedList<Number>();
		Iterator<Number> iter = LN2.rangedIterator(1, 10, 5);
		iter.next();
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test27(){
	for (int i = 0; i < 20; i++) {
		lN1.addToRear(i);
		lN1.getNodeAt(i+1);
	}
	lN1.getNodeAt(-1);
	}
	
	@Test
	public void test28(){
		UnorderedSingleLinkedList<Number> lN2 = new UnorderedSingleLinkedList<Number>();
		for (int i = 1; i <= 40; i++) {
			lN1.addToRear(i);
			lN2.addToRear(i);
			assertEquals(lN1.getElementAt(i),i);
			lN1.replaceElementAt(i, 0);
			assertEquals(lN1.getElementAt(i),0);
		}
		assertEquals(lN1.size(),40);
		assertEquals(lN1.toString(),"[0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]");
	}
	@Test
	public void test29() throws EmptyCollectionException{
		for (int i = 0; i < 5; i++) {
			lN1.addToRear(i);
		}
		assertEquals(lN1.getNodeAt(1).getNext().getTwoNext().toString(),"(3)");
		assertEquals(lN1.getFirst().toString(),"0");
		for (int i = 0; i < 5; i++) {
			lN1.removeLast();
		}
		try{
			lN1.removeLast();
			fail();
		}catch(EmptyCollectionException e){
			assertTrue(true);
		}
	}
	@Test
	public void test30(){
		for (int i = 1; i <= 15; i++) {
			lN1.addToRear(i);
		}
		Iterator<Number> iter = lN1.skippingIterator();
		assertEquals(UnorderedSingleLinkedList.listWith(iter).toString(),"[1, 3, 5, 7, 9, 11, 13, 15]");
		Iterator<Number> iter_2 = lN1.rangedIterator(1, 15, 3);
		assertEquals(UnorderedSingleLinkedList.listWith(iter_2).toString(),"[1, 4, 7, 10, 13]");
	}
	@Test
	public void test31(){
		for (int i = 1; i <= 15; i++) {
			lN1.addToRear(i);
		}
		Iterator<Number> iter_2 = lN1.rangedIterator(1, 15, 25);
		assertEquals(UnorderedSingleLinkedList.listWith(iter_2).toString(),"[1]");
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test32() throws EmptyCollectionException{
		for (int i = 1; i <= 15; i++) {
			lN1.addToRear(i);
		}
		lN1.getFirst();
		lN1.getElementAt(-1);
		//assertTrue(true);
	}
	@Test
	public void test33() throws EmptyCollectionException{
		for (int i = 1; i <= 15; i++) {
			lN1.addToRear(i);
		}
		Iterator<Number> iter = lN1.rangedIterator(4,10,3);
		assertEquals(UnorderedSingleLinkedList.listWith(iter).toString(),"[4, 7, 10]");
	}
	@Test
	public void test34(){
		for (int i = 1; i <= 5; i++) {
			lN1.addToRear(i);
		}
		assertEquals(lN1.size(),5);
		assertEquals(lN1.getElementAt(1),1);
		assertEquals(lN1.getElementAt(5),5);
		assertEquals(lN1.replaceElementAt(3,0),3);
		assertEquals(lN1.getElementAt(3),0);
		assertEquals(lN1.removeElementAt(3),0);
		assertEquals(lN1.size(),4);
		assertEquals(lN1.getElementAt(3),4);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test35(){
		assertFalse(lN1.contains(0));
		for (int i = 0; i < 5; i++) {
			lN1.addToRear(i);
		}
		assertFalse(lN1.contains(9345));
		lN1.replaceElementAt(-1, 0);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test36(){
		for (int i = 0; i < 5; i++) {
			lN1.addToRear(i);
		}
		lN1.removeElementAt(9876);
		
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test37(){
		for (int i = 0; i < 5; i++) {
			lN1.addToRear(i);
		}
		lN1.removeElementAt(99);
		
	}
	@Test (expected = EmptyCollectionException.class)
	public void test38() throws EmptyCollectionException{
		lN1.removeFirst();
		lN1.removeLast();
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test39(){
		lN1.getElementAt(99);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test40(){
		lN1.getNodeAt(99);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test41(){
		lN1.removeElementAt(0);
	}
	@Test (expected = IndexOutOfBoundsException.class)
	public void test42(){
		lN1.replaceElementAt(99, 0);
	}
	@Test (expected = EmptyCollectionException.class)
	public void test43() throws EmptyCollectionException{
		lN1.removeLast();
	}
	@Test
	public void test44(){
		lN1.addToRear(9);
	}
	

}
