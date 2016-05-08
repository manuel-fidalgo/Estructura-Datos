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
}
