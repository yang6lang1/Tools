package test;

import dataStructure.Leaf;
import dataStructure.Node;

public class TreeTestCases {
  public static void main(String[] args){
	TreeTestCases t = new TreeTestCases();

	//testcase1: test search method
	t.testCase1();
	
	//testcase2: test insert method with Case A
	t.testCase2();
  }

  public void testCase1(){
	Node n1 = new Node();
	n1.setL(2);
	n1.setM(4);

	Node n2 = new Node();
	n2.setL(1);
	n2.setM(2);

	Node n3 = new Node();
	n3.setL(3);
	n3.setM(4);

	Node n4 = new Node();
	n4.setL(5);
	n4.setM(6);

	Leaf l1 = new Leaf(1);
	Leaf l2 = new Leaf(2);
	Leaf l3 = new Leaf(3);
	Leaf l4 = new Leaf(4);
	Leaf l5 = new Leaf(5);
	Leaf l6 = new Leaf(6);

	n1.setFirstChild(n2);
	n1.setSecondChild(n3);
	n1.setThirdChild(n4);

	n2.setFirstChild(l1);
	n2.setSecondChild(l2);

	n3.setFirstChild(l3);
	n3.setSecondChild(l4);

	n4.setFirstChild(l5);
	n4.setSecondChild(l6);

	System.out.println("Test case #1: Test search function on a root node");
	int index = 7;
	Node n = n1.search(index);
	if(n == null){
	  System.out.println("Searching result of " + index + ": not found");
	}
	else {
	  System.out.println("Searching result of " + index + ": found in the tree leaf with a value: " + ((Leaf)n).getValue());
	}
	System.out.println();
	
	index = 2;
	n = n1.search(index);
	if(n == null){
	  System.out.println("Searching result of " + index + ": not found");
	}
	else {
	  System.out.println("Searching result of " + index + ": found in the tree leaf with a value: " + ((Leaf)n).getValue());
	}
	System.out.println();
	
	index = -1;
	n = n1.search(index);
	if(n == null){
	  System.out.println("Searching result of " + index + ": not found");
	}
	else {
	  System.out.println("Searching result of " + index + ": found in the tree leaf with a value: " + ((Leaf)n).getValue());
	}	
	System.out.println();
  }
  
  public void testCase2(){

	Node n1 = new Node();
	n1.setL(3);
	n1.setM(7);

	Node n2 = new Node();
	n2.setL(1);
	n2.setM(3);

	Node n3 = new Node();
	n3.setL(5);
	n3.setM(6);

	Node n4 = new Node();
	n4.setL(8);
	n4.setM(9);

	Leaf l1 = new Leaf(1);
	Leaf l2 = new Leaf(3);
	Leaf l3 = new Leaf(5);
	Leaf l4 = new Leaf(6);
	Leaf l5 = new Leaf(7);
	Leaf l6 = new Leaf(8);
	Leaf l7 = new Leaf(9);

	n1.setFirstChild(n2);
	n1.setSecondChild(n3);
	n1.setThirdChild(n4);

	n2.setFirstChild(l1);
	n2.setSecondChild(l2);

	n3.setFirstChild(l3);
	n3.setSecondChild(l4);
	n3.setThirdChild(l5);

	n4.setFirstChild(l6);
	n4.setSecondChild(l7);

	System.out.println("Test case #2: Test insert function case A");
	int index = 5;
	Node n = n1.insert(index);
	System.out.println("Inserting "+index);
	if(n == null){
	  System.out.println("Value "+index+ " already exist in the tree");
	}
	else{
	  System.out.println("Insert successfully. New leaf with value " + index + " is created");
	}
	System.out.println();
	
	index = 2;
	n = n1.insert(index);
	System.out.println("Inserting "+index);
	if(n == null){
	  System.out.println("Value "+index+ " already exist in the tree");
	}
	else{
	  System.out.println("Insert successfully. New leaf with value " + index + " is created");
	}
	System.out.println();
  
	index = 2;
	n = n1.insert(index);
	System.out.println("Inserting "+index);
	if(n == null){
	  System.out.println("Value "+index+ " already exist in the tree");
	}
	else{
	  System.out.println("Insert successfully. New leaf with value " + index + " is created");
	}
	System.out.println();
  }
}
