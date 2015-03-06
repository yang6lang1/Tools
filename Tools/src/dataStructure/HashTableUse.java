package dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class HashTableUse {

	private Map<String, String> dictionary;
	private List<String> names;
	private Queue<String> queue;
	private Set<Integer> numbers;

	public void rotateMatrix(){
		int[][] a = new int[4][4], b;

		int count = 0;
		for(int i = 0; i < a.length; i++){
			for(int j = 0; j<a[0].length; j++){
				a[i][j] = count++;
			}
		}

		for(int i = 0; i < a.length; i++){
			for(int j = 0; j<a[0].length; j++){
				System.out.print(a[i][j] + "\t");
			}
			System.out.print("\n");
		}
		System.out.print("\n");

		b = rotateMatrix(a);

		for(int i = 0; i < b.length; i++){
			for(int j = 0; j<b[0].length; j++){
				System.out.print(b[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}

	private int[][] rotateMatrix(int[][] a){
		if (a == null) return null;
		if(a.length != a[0].length) return null;
		int rowcount = a.length;

		int temp;
		for(int i = 0; i < rowcount/2 ; i++){
			for(int j = i; j < rowcount - 1 - i; j++){
				temp = a[i][j];
				a[i][j] = a[rowcount - 1 - j][i];
				a[rowcount - 1 - j][i] = a[rowcount - 1 - i][rowcount - 1 - j];
				a[rowcount - 1 - i][rowcount - 1 - j] = a[j][rowcount - 1 - i];
				a[j][rowcount - 1 - i] = temp;
			}
		}

		return a;
	}

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

	public void useSet(){
		numbers = new HashSet<Integer>();
		numbers.add(1);
		numbers.add(2);
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

	public void useAreAnagrams(){
		System.out.println();
		String s1 = "aaaa", s2 = "aaaa";
		System.out.println("String: "+ s1 + " and string: "+ s2 + " are anagrams? "+ areAnagrams(s1,s2));
	}

	//determine if two strings are anagrams or not
	private boolean areAnagrams(String s1, String s2){
		if (s1 == null || s2 == null) return false;

		int[] s1Count = new int[256], s2Count = new int[256];

		for(int i = 0; i < s1.length(); i++) {
			s1Count[s1.charAt(i)]++;
		}
		for(int i = 0; i < s2.length(); i++){
			s2Count[s2.charAt(i)]++;
		}

		for(int i = 0; i < s1Count.length; i++){
			if(s1Count[i] != s2Count[i]) {
				return false;
			}
		}

		return true;
	}
	//this is not the best solution
	public boolean checkStringNoCharRepeat(String s) {
		if (s == null) return true;
		boolean[] status = new boolean[256]; //char is 8-bit => 256 chars
		for (char c : s.toCharArray()) {
			if (status[c] == true) return false;
			status[c] = true;
		}

		return true;
	}

	public String reverse(String s) {
		if (s == null) return null;
		char[] chars = s.toCharArray();
		char temp;
		for (int i = 0; i <chars.length / 2; i++) {
			temp = chars[i];
			chars[i] = chars[chars.length - 1 - i];
			chars[chars.length - 1 - i] = temp;
		}

		return new String(chars);
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

	public void deleteMiddle(){
		System.out.println();
		Node<Integer> node6 = new Node<Integer>(6,null);
		Node<Integer> node5 = new Node<Integer>(5,null);
		Node<Integer> node4 = new Node<Integer>(4,node5);
		Node<Integer> node3 = new Node<Integer>(3,node4);
		Node<Integer> node2 = new Node<Integer>(2,null);
		Node<Integer> node1 = new Node<Integer>(1,node2);
		
		deleteMiddle(node1);
	}
	
	private void deleteMiddle(Node<Integer> head){
		if(head == null) return;
		if(head.getNext() == null) {
			head = null;
			return;
		}

		Node<Integer> slowptr = head, fastptr = head, prevslow = head;

		while(fastptr != null){
			fastptr = fastptr.getNext();
			if(fastptr == null){
				prevslow.setNext(slowptr.getNext());
				break;
			}

			fastptr = fastptr.getNext();

			prevslow = slowptr;
			slowptr = slowptr.getNext();

			if(fastptr == null){
				prevslow.setNext(slowptr.getNext());
			}
		}
		
		Node<Integer> ptr = head;
		while(ptr != null){
			System.out.println("Element: " + ptr.getElement());
			ptr = ptr.getNext();
		}
	}
	
	public static void main (String[] args) {
		HashTableUse use = new HashTableUse();
		use.useQueue();
		use.useMap();
		use.useList();
		use.bitManipulation();
		use.useAreAnagrams();
		use.rotateMatrix();
		use.deleteMiddle();
	}
}
