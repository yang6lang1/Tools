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
}
