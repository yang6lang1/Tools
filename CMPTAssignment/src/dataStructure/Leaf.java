package dataStructure;


public class Leaf extends Node{
  private int value;

  public Leaf(){
	super();
	this.value = 0;
  }

  public Leaf(int v){
	super();
	this.value = v;
  }

  public void setValue(int v){
	this.value = v;
  }

  public int getValue(){
	return this.value;
  }

  /**
   * Inserting into leaf does nothing
   * */
  public Node insert(int v){
	return null;
  }
  
  /**
   * Deleting from leaf does nothing
   * */
  public Node delete(int v){
	return null;
  }
}
