package dataStructure;

public class Stack <T extends Comparable<T>>{
  SHSLLinkedList<T> list;

  public Stack(){
	list = new SHSLLinkedList<T>();
  }

  //push
  public void push(T element){
	ListItem<T> item = new ListItem<T>();
	item.setElement(element);
	list.addFront(item);
  }

  //pull
  public T pull() throws Exception{
	ListItem<T> item = null;
	try{
	  item= list.removeFront();
	}
	catch(Exception e){
	  throw new Exception(e.getMessage());
	}

	return item.getElement();
  }

  //peek
  public T peek() throws Exception{
	ListItem<T> item = null;
	try{
	  item= list.peekFront();
	}
	catch(Exception e){
	  throw new Exception(e.getMessage());
	}

	return item.getElement();
  }

  public int getSize(){
	return list.getSize();
  }
  public String toString(){
	return list.toString();
  }
}
