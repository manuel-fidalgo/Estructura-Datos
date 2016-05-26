package ule.edi.tree;

import java.util.LinkedList;
import java.util.Random;

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
		System.out.println("MundoTest->");
		System.out.println(w.render());	
			
	}
	@Test
	public void test00(){
		w = World.createEmptyWorld();
		w.insert("", new Entity(Entity.CASTLE));
		w.insert("", new Entity(Entity.CASTLE));
		w.insert("R", new Entity(Entity.FOREST));
		w.insert("LLLL", new Entity(Entity.DRAGON));
		w.insert("R", new Entity(Entity.PRINCESS));
		w.insert("L", new Entity(Entity.WARRIOR));
		
		System.out.println("Mundo00->");
		System.out.println(w.render());
		
	}
	
}
