package ule.edi.tree;

import static org.junit.Assert.*;

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
		w.insert("RR", new Entity(Entity.CASTLE));
		
		System.out.println("Mundo00->");
		System.out.println(w.render());
		
		for (int i = 0; i < 8; i++) {
			w.insert("LLLLLLLLLLLLLLLL",new Entity(Entity.CASTLE));
		}
		
		assertEquals(w.countCastlesCloserThan(0),2);
		assertEquals(w.countCastlesCloserThan(1),2);
		assertEquals(w.countCastlesCloserThan(2),3);
		assertEquals(w.countCastlesCloserThan(15),3);
		assertEquals(w.countCastlesCloserThan(16),11);
		
		for (int i = 0; i < 8; i++) {
			w.insert("RRRRRRRRRRRRRRRR",new Entity(Entity.CASTLE));
		}
		
		assertEquals(w.countCastlesCloserThan(15),3);
		assertEquals(w.countCastlesCloserThan(16),19);
		
	}
	
}
