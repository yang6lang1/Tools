package dataStructure;

public class Stack <T extends Comparable<T>>{
	SHSLLinkedList<T> list;

	public Stack(){
		list = new SHSLLinkedList<T>();
	}

	//push
	public void push(ListItem<T> item){
		list.addFront(item);
	}

	//pull
	public ListItem<T> pull() throws Exception{
		ListItem<T> item = null;
		try{
			item= list.removeFront();
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}

		return item;
	}

	//peek
	public ListItem<T> peek() throws Exception{
		ListItem<T> item = null;
		try{
			item= list.peekFront();
		}
		catch(Exception e){
			throw new Exception(e.getMessage());
		}

		return item;
	}
	
	public int getSize(){
		return list.getSize();
	}
	public String toString(){
		return list.toString();
	}
}
