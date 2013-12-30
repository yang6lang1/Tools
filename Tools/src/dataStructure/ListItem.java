package dataStructure;

public class ListItem<T>{
  private T element;
  private ListItem<T> next;

  //constructor
  public ListItem(){
  	this(null, null);
  }
  
  public ListItem(T element, ListItem<T> next){
  	this.element = element;
  	this.next = next;
  }
  
  //getters, setters
  public void setElement(final T element){
    this.element = element;
  }

  public T getElement(){
    return this.element;
  }

  public void setNext(final ListItem<T> next){
    this.next = next;
  }

  public ListItem<T> getNext(){
    return this.next;
  }
}