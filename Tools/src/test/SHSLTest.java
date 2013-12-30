package test;

import dataStructure.ListItem;
import dataStructure.SHSLLinkedList;

public class SHSLTest {

	public static void main(String[] args) {
		SHSLLinkedList<String> test = new SHSLLinkedList<String>();
		
		// addFront
		ListItem<String> first = new ListItem<String>();
		first.setElement("first");
		test.addFront(first);
		ListItem<String> second = new ListItem<String>();
		second.setElement("second");
		test.addFront(second);
		ListItem<String> third = new ListItem<String>();
		third.setElement("third");
		test.addFront(third);
		ListItem<String> fourth = new ListItem<String>();
		fourth.setElement("fourth");
		test.addFront(fourth);
		ListItem<String> fifth = new ListItem<String>();
		fifth.setElement("fifth");
		test.addFront(fifth);
		ListItem<String> sixth = new ListItem<String>();
		sixth.setElement("sixth");
		test.addFront(sixth);
		System.out.println(test.toString());
		
		// peekFront
		try {
			System.out.println(test.peekFront().getElement());
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// removeFront
		try {
			test.removeFront();
			test.removeFront();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(test.toString());
		
		// remove
		try {
			test.remove("fourth");
			System.out.println(test.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// search
		try {
			ListItem<String> temp = test.search("first");
			System.out.println("Element in the list! " + temp.getElement());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
