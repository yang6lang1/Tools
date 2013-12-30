package dataStructure;

public class CircularLinkedList<T extends Comparable<T>> {

	private ListItem<T> tail;
	private int size;

	public CircularLinkedList(){
		this.tail = null;
	}

	public void addBack(final ListItem<T> item){
		if(tail == null){
			tail = item;
			item.setNext(tail);
		}
		else{
			item.setNext(tail.getNext());
			tail.setNext(item);
			tail = item;
		}

		size++;
	}

	public void addFront(final ListItem<T> item){
		if(tail == null){
			tail = item;
			item.setNext(tail);
		}
		else{
			item.setNext(tail.getNext());
			tail.setNext(item);
		}

		size++;
	}

	public ListItem<T> getBack(){
		return tail;
	}

	public ListItem<T> getFront(){
		return tail.getNext();
	}

	//Remove back can't be implemented in O(1) in SL
	//	public ListItem<T> removeBack() throws Exception{
	//		if(tail == null) throw new Exception("Empty list.");
	//		
	//		ListItem<T> item = null;
	//		if(size == 1){
	//			item = tail;
	//			tail = null;
	//			size--;
	//			return item;
	//		}
	//		else{
	//			item = tail;
	//			
	//			size--;
	//		}
	//	}

	public ListItem<T> removeFront() throws Exception{
		if(tail == null) throw new Exception("Empty list.");
		
		ListItem<T> item = null;
		if(size == 1){
			item = tail;
			tail = null;
			size--;
			return item;
		}
		else{
			item = tail.getNext();
			tail.setNext(tail.getNext().getNext());
			size--;
			return item;
		}
	}

	public ListItem<T> search(final T element) throws Exception{
		if(tail == null) throw new Exception("Empty list.");
		
		ListItem<T> item = null;
		boolean found = false;
		ListItem<T> temp = tail.getNext();
		for(int i = 0; i < size; i++){
			if(element.compareTo(temp.getElement()) == 0){
				found = true;
				return temp;
			}
			temp = temp.getNext();
		}
		
		if(!found){
			throw new Exception("Element not in the list.");
		}
		
		return item;
	}

	public int getSize(){
		return size;
	}
	
	public String toString(){
		String out = "";
		
		if(tail == null){
			return out;
		}
		else{
			ListItem<T> item = tail.getNext();
			for(int i = 0; i < size; i++){
				out += "Element: " + item.getElement().toString() + ", Next: " + item.getNext().getElement().toString() + "\n";
				item = item.getNext();
			}
			out += "Size: "+ this.size + "\n";
		}
	
		return out;
	}
}
