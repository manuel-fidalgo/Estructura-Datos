package ule.edi.tree;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.swing.plaf.synth.SynthSpinnerUI;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeADTImplTests {
	
    /*
	* ∅
    */
	private BinarySearchTreeADTImpl<Integer> TE = null;
	/*por la izquierda, por la derecha*/
	/*
	* 1
	* |  ∅
	* |  2
	* |  |  ∅
	* |  |  3
	* |  |  |  ∅
	* |  |  |  4
	* |  |  |  |  ∅
	* |  |  |  |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> T1234 = null;
	
	/*
	* 4
	* |  3
	* |  |  2
	* |  |  |  1
	* |  |  |  |  ∅
	* |  |  |  |  ∅
	* |  |  |  ∅
	* |  |  ∅
	* |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> T4321 = null;

	/*
	* 5
	* |  2
	* |  |  1
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  3
	* |  |  |  ∅
	* |  |  |  ∅
	* |  8
	* |  |  7
	* |  |  |  ∅
	* |  |  |  ∅
	* |  |  9
	* |  |  |  ∅
	* |  |  |  ∅
    */	
	private BinarySearchTreeADTImpl<Integer> TC3 = null;

	/*
	* 10
	* |  5
	* |  |  ∅
	* |  |  ∅
	* |  20
	* |  |  ∅
	* |  |  30
	* |  |  |  ∅
	* |  |  |  ∅
	*/
	private BinarySearchTreeADTImpl<Integer> TEx = null;
	
	private LinkedList<String> lS = null;
	
	@Before
	public void setupBSTs() {
		
		TE = new BinarySearchTreeADTImpl<Integer>();
		
		T1234 = new BinarySearchTreeADTImpl<Integer>();
		T1234.insert(1,2,3,4);
		
		T4321 = new BinarySearchTreeADTImpl<Integer>();
		T4321.insert(4, 3, 2, 1);
		
		TC3 = new BinarySearchTreeADTImpl<Integer>();
		TC3.insert(5, 2, 8, 1, 3, 7, 9);
		
		TEx = new BinarySearchTreeADTImpl<Integer>();
		TEx.insert(10, 20, 30, 5);
		
		lS = new LinkedList<String>();
	}
	
	@Test
	public void test00(){
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 3; i++) {
			sb.append("1");
			assertEquals(T1234.getContentWithPath(sb.toString()),new Integer(i+2));
		}
	}
	@Test (expected = IllegalArgumentException.class)
	public void test01(){
		T1234.getContentWithPath("Hola wapo");
	}
	@Test (expected = NoSuchElementException.class)
	public void test02(){
		T1234.getContentWithPath("0000000000");
	}
	@Test
	public void test03(){
		assertEquals(this.T1234.countOddLevelElements(),2);
		assertEquals(this.T4321.countOddLevelElements(),2);
		assertEquals(this.TC3.countOddLevelElements(),5);
	}
	@Test
	public void test04(){
		//LS es el buffer que ha declarado ella como atributo de clase
		T1234.parentChildPairs(lS);
		assertEquals("[(1,2), (2,3), (3,4)]",lS.toString());
		lS = new LinkedList<String>();
		T4321.parentChildPairs(lS);
		assertEquals("[(4,3), (3,2), (2,1)]",lS.toString());
	}
	@Test (expected = NoSuchElementException.class)
	public void test05(){
		T1234.getContentWithPath("0");
	}
	

}
