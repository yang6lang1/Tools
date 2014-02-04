package dataStructure;

public class DHSLLinkedList <T extends Comparable<T>>{

	Node<T> front;
	Node<T> back;
	
	public DHSLLinkedList(){
		front = null;
		back = null;
	}
	
	//addFront
	public void addFront(T element){
		Node<T> item = new Node<T>(element, null);
		item.setNext(front);
		front = item;
		if(back == null) back = item;
	}
	
	//addBack
	public void addBack(T element){
		Node<T> item = new Node<T>(element, null);
		if(back != null){
			back.setNext(item);
		}
		back = item;
		if(front == null) front = item;
	}
	
	//removeFront
	public T removeFront() throws Exception{
		if(front == null){
			throw new Exception("Empty list.");
		}
		
		T temp = front.getElement();
		front = front.getNext();
		if(front == null) back =  null;
		return temp;
	}
	
	//removeBack
	public T removeBack() throws Exception{
		if(back == null){
			throw new Exception("Empty list.");
		}
		
		T temp = back.getElement();
		if(front == back){
			front = null;
			back = null;
			return temp;
		}
		
		for(Node<T> curr = front; curr != null; curr = curr.getNext()){
			if(curr.getNext() == back){
				curr.setNext(back.getNext());
				back = curr;
				break;
			}
		}
		return temp;
	}
	
	//peekFront
	public T peekFront() throws Exception{
		if(front == null) throw new Exception("Empty list.");
		
		return front.getElement();
	}
	
	//peekBack
	public T peekBack() throws Exception{
		if(back == null) throw new Exception("Empty list.");
		
		return back.getElement();
	}
	
	//add(index,element)
	//remove(element)
	//removeAll
	//toString
}
