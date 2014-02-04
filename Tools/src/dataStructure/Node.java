package dataStructure;

public class Node <T>{
	
	private T element;
	private Node<T> next;
	
	//constructors
	public Node(){
		this(null, null);
	}
	
	public Node(T e, Node<T> n){
		this.element = e;
		this.next = n;
	}
	
	public void setElement(final T element){
		this.element = element;
	}
	
	public T getElement(){
		return this.element;
	}

	public void setNext(final Node<T> next){
		this.next = next;
	}
	
	public Node<T> getNext(){
		return this.next;
	}
}
