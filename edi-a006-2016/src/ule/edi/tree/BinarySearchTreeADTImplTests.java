package ule.edi.tree;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

public class BinarySearchTreeADTImplTests {
	
    /*
	* ∅
    */
	private BinarySearchTreeADTImpl<Integer> TE = null;
	
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
		Integer element = T1234.getContentWithPath("0");
		System.out.println(element);
	}

}
