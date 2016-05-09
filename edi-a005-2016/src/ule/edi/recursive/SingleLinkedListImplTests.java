package ule.edi.recursive;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SingleLinkedListImplTests {


	private SimpleListADT<String> lS;

	private SimpleListADT<String> lSABC;

	private static final String[] ABC_ = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};

	@Before
	public void setupLists() {

		lS = new SingleLinkedListImpl<String>();

		lSABC = new SingleLinkedListImpl<String>("A", "B", "C");
	}

	@Test
	public void testConstructDefault() {

		Assert.assertEquals("[]", lS.toString());
	}

	@Test
	public void testConstructWithContent() {

		Assert.assertEquals("[A, B, C]", lSABC.toString());
	}

	@Test
	public void testAddFirst() {

		Assert.assertEquals("[]", lS.toString());

		lS.addFirst("A");
		Assert.assertEquals("[A]", lS.toString());

		lS.addFirst("B");
		Assert.assertEquals("[B, A]", lS.toString());

		lS.addFirst("C");
		Assert.assertEquals("[C, B, A]", lS.toString());
	}

	@Test
	public void test00(){
		for (int i = 0; i < ABC_.length; i++) {
			lS.addToRear(ABC_[i]);
		}
		assertEquals("[A, B, C, D, E, F, G, H, I, J, K, L, M, N, O, P, Q, R, S, T, U, V, W, X, Y, Z]",lS.toString());
		lS.dropElements(ABC_.length-2);
		assertEquals("[Y, Z]",lS.toString());
		lS.dropElements(30);
		assertEquals("[]",lS.toString());
	}
	@Test
	public void test01(){
		SingleLinkedListImpl<String> l1 = new SingleLinkedListImpl<String>();
		SingleLinkedListImpl<String> l2 = new SingleLinkedListImpl<String>();
		assertTrue(l1.isEqualStructure(l2));
		l1.addToRear("Z");
		assertFalse(l1.isEqualStructure(l2));
		for (int i=0; i<3; i++) {
			l1.addToRear(ABC_[i]);
			l2.addToRear(ABC_[i]);
		}
		assertFalse(l1.isEqualStructure(l2));
		l2.addToRear("Y");
		assertTrue(l2.isEqualStructure(l1));
	}
	@Test
	public void test02(){
		SimpleListADT<String> l1 = new SingleLinkedListImpl<String>();
		SimpleListADT<String> l2 = new SingleLinkedListImpl<String>();
		for (int i = 0; i < ABC_.length; i++) {
			l1.addToRear(ABC_[i]);
			l2.addFirst(ABC_[i]);
		}
		assertEquals(l1.toString(),l2.reverse().toString());
		l1 = new SingleLinkedListImpl<String>();
		l2 = new SingleLinkedListImpl<String>();
		assertEquals(l1.toString(),l2.reverse().toString());
		l1 = new SingleLinkedListImpl<String>("A","B","C","D","C","B","A");
		assertEquals(l1.toString(),l1.reverse().toString());
	}
	@Test
	public void test03(){
		SimpleListADT<String> l1 = new SingleLinkedListImpl<String>();
		SimpleListADT<String> l2 = new SingleLinkedListImpl<String>();
		for (int i = 0; i < ABC_.length; i++) {
			l1.addToRear(ABC_[i]);
			l2.addToRear(ABC_[i]);
			l2.addToRear(ABC_[i]);
		}
		assertEquals(l2.toString(),l1.repeatAllElements().toString());
		l1 = new SingleLinkedListImpl<String>("A","B");
		assertEquals(l1.reverse().repeatAllElements().toString(),"[B, B, A, A]");
	}
	@Test
	public void test04(){
		SimpleListADT<String> l1 = new SingleLinkedListImpl<String>();
		SimpleListADT<String> l2 = new SingleLinkedListImpl<String>();
		for (int i = 0; i < ABC_.length; i++) {
			l1.addToRear(ABC_[i]);
			for (int j = 0; j < 5; j++) {
				l2.addToRear(ABC_[i]);
			}
		}
		System.out.println(l1.repeatNElements(5).toString());
		System.out.println(l2.toString());
		assertEquals(l1.repeatNElements(5).toString(),l2.toString());
	}
}
