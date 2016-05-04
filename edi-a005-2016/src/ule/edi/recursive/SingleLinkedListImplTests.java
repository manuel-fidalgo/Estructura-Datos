package ule.edi.recursive;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SingleLinkedListImplTests {


	private SimpleListADT<String> lS;

	private SimpleListADT<String> lSABC;
	
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
	
	
}
