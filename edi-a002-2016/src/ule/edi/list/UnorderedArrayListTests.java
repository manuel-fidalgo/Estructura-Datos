package ule.edi.list;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

public class UnorderedArrayListTests {

	private UnorderedArrayList<Number> lN1;
	
	@Before
	public void setupLists() {
		
		this.lN1 = new UnorderedArrayList<Number>();
	}
	
	//	JUnit no executa los tests anotados con @Ignore
	
	@Ignore
	public void testDistinct() {
	
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		lN1.addToRear(2);
		lN1.addToRear(1);
		
		Assert.assertEquals(5L, lN1.size());
		
		//	distinct es un método estático de 'utilidad'
		UnorderedListADT<Number> dlN1 = UnorderedArrayList.distinct(lN1);
		
		Assert.assertEquals(2L, dlN1.size());
		Assert.assertTrue(dlN1.contains(1));
		Assert.assertTrue(dlN1.contains(2));
	}
	
	@Ignore
	public void testRangedIterator() {
	
		UnorderedListADT<Number> lN2;
		
		for (int i = 0; i < 32; i++) {
			lN1.addToRear(Integer.valueOf(i));
		}

		//	listWith es un método estático
		lN2 = UnorderedArrayList.listWith(lN1.rangedIterator(1, 16, 4));
		Assert.assertEquals("[0, 4, 8, 12]", lN2.toString());		
		
	}	
	
}
