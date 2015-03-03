package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashTableUse {

	private Map<String, String> dictionary;
	private List<String> names;
	private Queue<String> queue;
	
	public HashTableUse () {
		dictionary = new HashMap<String, String>();
		names = new ArrayList<String>();
		queue = new Queue<String>();
	}
	
	public void useMap () {
		System.out.println();
		dictionary.put("Alex", "SFU Computer Engineering");
		dictionary.put("Tim", "SFU Electronic Engineering");
		dictionary.put("Mimo", "Alex's girfriend");
		
		System.out.println("Check student Alex is in the dictionary: " + dictionary.containsKey("Alex"));
		System.out.println("Check student Billy is in the dictionary: " + dictionary.containsKey("Billy"));
		
		for (Map.Entry<String, String> mapEntry: dictionary.entrySet()) {
			System.out.println("Student: " + mapEntry.getKey() + ", Description: "+ mapEntry.getValue());
		}
	}
	
	public void useList () {
		System.out.println();
		names.add("1");
		names.add("2");
		names.add("3");
		
		for (String n : names) {
			System.out.println(n);
		}
	}
	
	public static void main (String[] args) {
		HashTableUse use = new HashTableUse();
		use.useQueue();
		use.useMap();
		use.useList();
		use.bitManipulation();
	}
	
	public void bitManipulation(){
		System.out.println();
		
		int a, b, c;
		a = 0b1010;
		c = 0b1011 ^ a ;
		System.out.println("a: " + a);
		b = a << 1;
		System.out.println("b: " + b);
		System.out.println("c: " + c);
		a = 0xFF;
		b = a - 1;
		System.out.println("b: " + b);
		a = 0xAB;
		b = a + 0x11;
		System.out.println("b: " + b);
	}
	
	public void useQueue() {
		System.out.println("Test push:");
		queue.push("1.alex");
		queue.push("2.tim");
		queue.push("3.joyce");
		queue.push("4.kelvin");
		queue.push("5.noan");
		queue.push("6.brian");
		for (Object node : queue.toElementArray()) {
			System.out.println("Queue element: " + node);
		}
		
		System.out.println("");
		System.out.println("Test peek:");
		try {
			System.out.println("front: "+queue.peek());
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		System.out.println("");
		System.out.println("Test pull:");
		try {
			queue.pull();
			queue.pull();
			for (Object node : queue.toElementArray()) {
				System.out.println("Queue element: " + node);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		System.out.println("");
		System.out.println("Test size:");
		System.out.println("size: "+ queue.getSize());
		
		System.out.println("");
		System.out.println("Test removeall:");
		queue.removeAll();
		System.out.println("size: "+ queue.getSize());
	}
	
	class Queue<T> {
		private Node<T> front,back;
		private int size;
		
		public Queue(){
			removeAll();
		}
		
		public int getSize(){
			return size;
		}
		
		public void removeAll(){
			front = back = null;
			size = 0;
		}
		
		public T peek() throws Exception {
			if(size == 0) throw new Exception("Empty queue");
			return front.getElement();
		}
		
		public T pull() throws Exception {
			if(size == 0) throw new Exception("Empty queue");
			T element = front.getElement();
			if (size == 1) {
				front = back = null;
			}
			else {
				front = front.getNext();
			}
			size --;
			return element;
		}
		
		public void push(T element) {
			Node<T> newNode = new Node<T>(element, null);
			if(size == 0) {
				front = back = newNode;
			}
			else {
				back.setNext(newNode);
				back = newNode;
			}
			size ++;
		}
		
		public Object[] toElementArray() {
			Object[] elements = new Object[size];
			int i = 0;
			for (Node<T> node= front; node != null; node = node.getNext()) {
				elements[i] = node.getElement();
				i++;
			}
			return elements;
		}
	}
	
	class Node<T> {
		private T element;
		private Node<T> next;
		
		public Node() {
			element = null;
			next = null;
		}
		
		public Node(T element, Node<T> next) {
			this.element = element;
			this.next = next;
		}
		
		public void setElement(T element){
			this.element = element;
		}
		
		public void setNext(Node<T> next) {
			this.next = next;
		}
		
		public T getElement(){
			return this.element;
		}
		
		public Node<T> getNext() {
			return this.next;
		}
	}
}
