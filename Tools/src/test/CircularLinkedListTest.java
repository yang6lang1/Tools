package test;

import dataStructure.CircularLinkedList;
import dataStructure.ListItem;

public class CircularLinkedListTest {

	public static void main(String[] args) {
		CircularLinkedList<String> list = new CircularLinkedList<String>();
		
		// addBack
		ListItem<String> first = new ListItem<String>();
		first.setElement("first");
		list.addBack(first);
		ListItem<String> second = new ListItem<String>();
		second.setElement("second");
		list.addBack(second);
		ListItem<String> third = new ListItem<String>();
		third.setElement("third");
		list.addBack(third);
		ListItem<String> fourth = new ListItem<String>();
		fourth.setElement("fourth");
		list.addBack(fourth);
		ListItem<String> fifth = new ListItem<String>();
		fifth.setElement("fifth");
		list.addBack(fifth);
		System.out.println(list.toString());
		
		// addFront
		list = new CircularLinkedList<String>();
		list.addFront(first);
		list.addFront(second);
		list.addFront(third);
		list.addBack(fourth);
		list.addBack(fifth);
		System.out.println(list.toString());
		
		// removeFront
		//list = new CircularLinkedList<String>();
		try{
			list.removeFront();
			list.removeFront();
			list.removeFront();
			System.out.println(list.toString());
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		// search
		try {
			ListItem<String> temp;
			temp= list.search("fourth");
			System.out.print("Found element in the list: "+temp.getElement() + " -> Next: "+ temp.getNext().getElement());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
