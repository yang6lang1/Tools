package dataStructure;

public class StackDirectImplementation<T extends Comparable<T>> {

  private ListItem<T> tail;
  private int size;

  public StackDirectImplementation(){
	size = 0;
	tail = null;
  }

  public int getSize(){
	return this.size;
  }

  public void push(final ListItem<T> item){
	if(item == null) throw new NullPointerException("Item is null.");

	if(tail == null){
	  tail = item;
	  tail.setNext(null);
	}
	else{
	  item.setNext(tail);
	  tail = item;
	}

	size++;
  }

  public ListItem<T> pull() throws Exception{
	if(tail == null) throw new Exception("Empty list.");

	ListItem<T> item = null;
	item = tail;
	tail = tail.getNext();

	size--;
	return item;
  }

  public ListItem<T> peek() throws Exception{
	if(tail == null) throw new Exception("Empty list.");

	ListItem<T> item = null;
	item = tail;

	return item;
  }

  public String toString(){
	String out = "";
	for(ListItem<T> item = tail; item != null; item = item.getNext()){
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
