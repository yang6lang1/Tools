package dataStructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

public class TreeTestCases {
  public static void main(String[] args){
	TreeTestCases t = new TreeTestCases();

	t.testcase();
	//testcase1: test search method
	//t.testCase1();

	//testcase2: test insert method with Case A
	//t.testCase2();

	//testcase3: test insert method with Case B
	//t.testCase3();
  }

  public void testcase(){
	try {
	  TXTReader reader = new TXTReader("input.txt");
	  File file = new File("src/dataStructure/output.txt");
	  FileWriter fw = new FileWriter(file);
	  PrintWriter writer = new PrintWriter(fw);
	  TwoThreeTree t = new TwoThreeTree();

	  //read inputs
	  String line = reader.readNextLine();
	  String[] values = line.split(",");
	  for(String s : values){
		t.insert(Integer.parseInt(s));
	  }
	  System.out.println("Original tree:");
	  this.printTree(t.getRoot());

	  //read operations
	  while((line = reader.readNextLine())!= null){
		String[] input = line.split(" ");
		String operation = input[0];
		int value = Integer.parseInt(input[1]);
		switch(operation){
		case "Find":
		  Node n = t.search(value);
		  writer.print("Find "+ value+"\t\t:");
		  if(n instanceof Leaf && ((Leaf)n).getValue() == value){
			writer.print("Found; ");
			int order = t.searchAndDetermineOrder(value);
			writer.print(value + " is the " + order +"th element of the list");
			writer.println();
		  }
		  else{
			writer.println("Not found");
		  }

		  System.out.println("Find "+ value+"\t\t");
		  System.out.println("New tree:");
		  this.printTree(t.getRoot());
		  System.out.println();
		  break;
		case "Insert":
		  writer.print("Insert "+ value+"\t:");
		  Node n1 = t.search(value);
		  if(n1 instanceof Leaf){
			writer.print("Value " + value + " is already in the list, not going to insert");
		  }
		  else{
			t.insert(value);
			int order = t.searchAndDetermineOrder(value);
			writer.print("After the insertion, "+ value + " is the " + order +"th element of the list");
		  }
		  writer.println();
		  System.out.println("Insert "+ value+"\t\t");
		  System.out.println("New tree:");
		  this.printTree(t.getRoot());
		  System.out.println();
		  break;
		case "Delete":
		  writer.print("Delete "+ value+"\t:");
		  int order = t.searchAndDetermineOrder(value);
		  if(order != 0){
			t.delete(value);
			writer.print(value + " is the " + order + "th element of the list, deleted");
		  }
		  else{
			writer.print(value + " is not found in the list.");
		  }

		  writer.println();
		  System.out.println("Delete "+ value+"\t\t");
		  System.out.println("New tree:");
		  this.printTree(t.getRoot());
		  System.out.println();
		  break;
		default:
		  System.out.println("Wrong operation: "+ operation);
		  break;
		}
	  }

	  writer.close();
	} catch (FileNotFoundException e) {
	  e.printStackTrace();
	} catch (IOException e) {
	  e.printStackTrace();
	}
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
	ITree t = new TwoThreeTree(n1, 6);

	System.out.println("Test case #1: Test search function on a root node");
	int index = 7;
	Node n = t.search(index);
	if(n == null){
	  System.out.println("Searching result of " + index + ": not found");
	}
	else {
	  System.out.println("Searching result of " + index + ": found in the tree leaf with a value: " + ((Leaf)n).getValue());
	}
	System.out.println();

	index = 2;
	n = t.search(index);
	if(n == null){
	  System.out.println("Searching result of " + index + ": not found");
	}
	else {
	  System.out.println("Searching result of " + index + ": found in the tree leaf with a value: " + ((Leaf)n).getValue());
	}
	System.out.println();

	index = -1;
	n = t.search(index);
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
	TwoThreeTree t = new TwoThreeTree(n1, 7);

	System.out.println("Test case #2: Test insert function case A");
	int index = 5;
	System.out.println("Inserting "+index+" which is originally in the tree");
	System.out.println("Original tree:");
	this.printTree(t.getRoot());
	System.out.println("New tree:");
	t.insert(index);
	this.printTree(t.getRoot());
	System.out.println();

	index = 2;
	System.out.println("Inserting "+index);
	System.out.println("Original tree:");
	this.printTree(t.getRoot());
	System.out.println("New tree:");
	t.insert(index);
	this.printTree(t.getRoot());
	System.out.println();

	index = 2;
	System.out.println("Inserting "+index+" which is already in the tree");
	System.out.println("Original tree:");
	this.printTree(t.getRoot());
	System.out.println("New tree:");
	t.insert(index);
	this.printTree(t.getRoot());
	System.out.println();
  }

  public void testCase3(){
	TwoThreeTree t = new TwoThreeTree();
	System.out.println();
	System.out.println("Original Tree:");
	this.printTree(t.getRoot());

	int index = 4;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 2;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 19;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = -5;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 6;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 2;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 9;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 8;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 7;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 3;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 12;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 2;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();

	index = 4;
	System.out.println("Delete "+index);
	t.delete(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();
	
	index = 3;
	System.out.println("Delete "+index);
	t.delete(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();
	
	index = 15;
	System.out.println("Insert "+index);
	t.insert(index);
	System.out.println("New Tree:");
	this.printTree(t.getRoot());
	System.out.println();
  }

  private LinkedList<Node> queue;
  private LinkedList<String> StrQueue;
  public void printTree(Node n){
	if(n == null){
	  System.out.println("Tree is empty.");
	  return;
	}

	queue  = new LinkedList<Node>();
	StrQueue = new LinkedList<String>();

	Node curr = n;
	String currStr = n.toString();

	queue.addLast(curr);
	StrQueue.addLast(currStr);
	queue.addLast(null);
	StrQueue.addLast("\n");
	while(!queue.isEmpty()){
	  curr = queue.removeFirst();
	  currStr = StrQueue.removeFirst();

	  System.out.print("\t"+ currStr);

	  if(curr != null){
		if(curr.getFirstChild() != null){
		  queue.addLast(curr.getFirstChild());
		  StrQueue.addLast(curr.getFirstChild().toString());
		}
		if(curr.getSecondChild()!= null){
		  queue.addLast(curr.getSecondChild());
		  StrQueue.addLast(curr.getSecondChild().toString());
		}
		if(curr.getThirdChild()!= null){
		  queue.addLast(curr.getThirdChild());
		  StrQueue.addLast(curr.getThirdChild().toString());
		}
	  }
	  else{
		queue.addLast(null);
		StrQueue.addLast("\n");
		if(queue.size() == 1){
		  break;
		}
	  }
	}
  }
}
