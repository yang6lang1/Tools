package test;

import dataStructure.ListItem;
import dataStructure.StackDirectImplementation;

public class StackTest {

  public static void main(String[] args) {
	//Stack<String> stack = new Stack<String>();
	
	StackDirectImplementation<String> stack = new StackDirectImplementation<String>();
	
	// push
	ListItem<String> first = new ListItem<String>();
	first.setElement("first");
	stack.push(first);
	ListItem<String> second = new ListItem<String>();
	second.setElement("second");
	stack.push(second);
	ListItem<String> third = new ListItem<String>();
	third.setElement("third");
	stack.push(third);
	ListItem<String> fourth = new ListItem<String>();
	fourth.setElement("fourth");
	stack.push(fourth);
	ListItem<String> fifth = new ListItem<String>();
	fifth.setElement("fifth");
	stack.push(fifth);
	ListItem<String> sixth = new ListItem<String>();
	sixth.setElement("sixth");
	stack.push(sixth);
	System.out.println(stack.toString());

	// peek
	try {
	  System.out.println(stack.peek().getElement());
	  System.out.println();
	} catch (Exception e) {
	  e.printStackTrace();
	}

	// pull
	try {
	  stack.pull();
	  stack.pull();
	  stack.pull();
	  stack.pull();
	  stack.pull();
	  stack.pull();
	  //stack.push(fifth);
	} catch (Exception e) {
	  e.printStackTrace();
	}
	System.out.println(stack.toString());
  }
}
