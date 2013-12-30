package dataStructure;

/**SHSL*/
public class SHSLLinkedList <T extends Comparable<T>>{
	private ListItem<T> head;
	private int size;
	
	public SHSLLinkedList(){
		this.head = null;
		this.size = 0;
	}
	
	public int getSize(){
		return this.size;
	}
	
	/**Add new element at the head of the LinkedList*/	
	public void addFront(ListItem<T> item) throws NullPointerException{
		if(item == null) throw new NullPointerException();
		
		if(head == null) {
			head = item;
		}
		else{
			item.setNext(head);
			head = item;
		}
		size++;
	}
	
	/**remove the first occurence of this element*/
	public ListItem<T> remove(T element) throws Exception{
		if(head == null) throw new Exception("Empty list");
		
		ListItem<T> temp = null;
		if(head.getNext() == null){
			if(head.getElement().compareTo(element) == 0){
				temp = head;
				head = null;
				size--;
				return temp;
			}
			else{
				throw new Exception("Element not found in the list.");
			}
		}
		else{
			if(head.getElement().compareTo(element) == 0){
				temp = head;
				head = head.getNext();
				size--;
				return temp;
			}
			else{
				boolean found = false;
				for(ListItem<T> curr = head; curr.getNext() != null; curr = curr.getNext()){
					ListItem<T> next = curr.getNext();
					if(next.getElement().compareTo(element) == 0){
						temp = next;
						curr.setNext(next.getNext());
						size--;
						found = true;
						return temp;
					}
				}
				
				if(!found) throw new Exception("Element not found in the list.");
			}
		}
		
		return temp;
	}
	
	/**Remove the element at the head of the LinkedList*/
	public ListItem<T> removeFront() throws Exception{
		if(head == null) throw new Exception("Empty list");
		
		ListItem<T> front;
		front = head;
		head = head.getNext();
		size--;
		return front;
	}
	
	public ListItem<T> peekFront() throws Exception{
		if(head == null) throw new Exception("Empty list");
		
		return head;
	}
	
	/**Search by traversing the linked list. O(n)*/
	public ListItem<T> search(T element) throws Exception{
		if(head == null) throw new Exception("Empty list");
		ListItem<T> item = null;
		
		boolean found = false;
		for(ListItem<T> temp = head; (temp != null && !found) ; temp = temp.getNext()){
			if(temp.getElement().compareTo(element) == 0){
				found = true;
				item = temp;
				break;
			}
		}
		
		if(found){
			return item;
		}
		else{
			throw new Exception("Element not found in the list.");
		}
	}
	
	public String toString(){
		String out = "";
		for(ListItem<T> item = head; item != null; item = item.getNext()){
			out += "Element: " + item.getElement().toString();
			if(item.getNext() == null){
				out += ", Next: " + "null\n";
			}
			else{
				out += ", Next: " + item.getNext().getElement().toString()+"\n";
			}
		}
		out += "Size: " + size + "\n";
		return out;
	}
}
