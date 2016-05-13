package ule.edi.tree;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class WorldTests {

	private World w = null;
	
	private LinkedList<Character> lC = null;
	
	@Before
	public void setupWorlds() {
		
		w = World.createEmptyWorld();
		
		lC = new LinkedList<Character>();
	}
	
	@Test
	public void testInsertRender() {
		
		w.insert("LL", Entity.warriors(2));		
		
		System.out.println(w);
		System.out.println(w.render());		
	}
	
}
