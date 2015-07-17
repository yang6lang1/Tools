package dataStructure;


public class Leaf extends Node{
  private int value;

  public Leaf(){
	super();
	this.value = 0;
	this.updateSize();
  }

  public Leaf(int v){
	super();
	this.value = v;
	this.updateSize();
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
  public void insert(int v){
  }

  /**
   * Deleting from leaf does nothing
   * */
  public Node delete(int v){
	return null;
  }

  /**Compare the order of two leaves
   * Return value:
   * -1: current leaf is to the left of the other leaf
   * 1: current leaf is to the right of the other leaf
   * 0: current leaf is the same as the other leaf
   * -2: invalid comparison
   * */
  public int CompareTo(Node anotherNode){
	if(anotherNode instanceof Leaf){
	  Leaf anotherLeaf = (Leaf)anotherNode;
	  if(this.value > anotherLeaf.value){
		return 1;
	  }
	  else if(this.value < anotherLeaf.value){
		return -1;
	  }
	  else{
		return 0;
	  }
	}
	else{
	  return -2;
	}
  }
  
  public String toString(){
	return "["+this.value+"]:"+this.getSize();
  }
}
