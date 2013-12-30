package dataStructure;

public class Queue <T extends Comparable<T>>{

	private CircularLinkedList<T> list;
	
	public Queue(){
		list = new CircularLinkedList<T>();
	}
	
	/**Add an element at the back of the queue*/
	public void queue(final ListItem<T> item){
		list.addBack(item);
	}

	/**Remove an element from the front of the queue*/
	public ListItem<T> dequeue() throws Exception{
		try {
			return list.removeFront();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public int getSize(){
		return list.getSize();
	}
}
