package dataStructure;

import interfaces.Tree;

import java.util.ArrayList;
import java.util.List;

import exceptions.IndexOutOfBoundException;

public class Node implements Tree{
  private int L;
  private int M;
  private Node f;
  private Node s;
  private Node t;
  private Node p;

  private int numChildren;

  public Node(){
	M = L = 0;
	p = f = s = t = null;
	numChildren = 0;
  }

  public Node getFirstChild() {
	return f;
  }

  public void setFirstChild(Node f) {
	this.f = f;
	if(f != null){
	  this.f.setParent(this);
	}
	this.updateNumChildren();
  }

  public Node getSecondChild() {
	return s;
  }

  public void setSecondChild(Node s) {
	this.s = s;
	if(s != null){
	  this.s.setParent(this);
	}
	this.updateNumChildren();
  }

  public Node getThirdChild() {
	return t;
  }

  public void setThirdChild(Node t) {
	this.t = t;
	if(t == null){
	  this.t.setParent(this);
	}
	this.updateNumChildren();
  }

  private void updateNumChildren(){
	this.numChildren = 0;
	if(this.f != null) this.numChildren++;
	if(this.s != null) this.numChildren++;
	if(this.t != null) this.numChildren++;
  }

  private void updateL(){
	if(this instanceof Leaf){
	  return;
	}
	else{
	  if(this.f != null && this.f instanceof Leaf){
		//if children are leaves, we get the value of the first child
		this.L = ((Leaf)this.f).getValue();
	  }
	  else{
		this.L = max(this.f);
	  }
	}
  }
  
  private void updateM(){
	if(this instanceof Leaf){
	  return;
	}
	else{
	  if(this.f != null && this.f instanceof Leaf){
		this.M = ((Leaf)this.s).getValue();
	  }
	  else{
		this.M = max(this.s);
	  }
	}
  }
  
  /**Recursively find the max value of a node's subtree\
   * Efficiency: O(Logn)
   * */
  public int max(Node n){
	if(n instanceof Leaf){
	  return ((Leaf)n).getValue();
	}
	else{
	  if(n.getThirdChild() != null){
		return max(n.getThirdChild());
	  }
	  else{
		return max(n.getSecondChild());
	  }
	}
  }


  /**Searching the value v from this node
   * Return value:
   * - null: value not found
   * - not null: return the leaf containing the value v 
   * */
  @Override
  public Node search(int v) {
	Node n = searchNode(v, this);

	if(n instanceof Leaf){
	  //found the value
	  return n;
	}
	else{
	  return null;
	}
  }

  /**Inserting the value v into this node (or its child)
   * Return value:
   * - null: this value already exist
   * - not null: the new leaf created with value v
   * */
  @Override
  public Node insert(int v){
	Node n = searchNode(v, this);
	if(n == null)
	  
	if(n instanceof Leaf){
	  //the value already exist in the tree
	  return null;
	}
	else{
	  if(n.getNumChildren() == 2){
		Leaf l = new Leaf(v);
		if(v < ((Leaf)n.getFirstChild()).getValue()){
		  n.setThirdChild(n.getSecondChild());
		  n.setSecondChild(n.getFirstChild());
		  n.setFirstChild(l);
		  n.setL(v);
		  n.setM(((Leaf)n.getSecondChild()).getValue());
		}
		else if(v > ((Leaf)n.getFirstChild()).getValue() && v < ((Leaf)n.getSecondChild()).getValue()){
		  n.setThirdChild(n.getSecondChild());
		  n.setSecondChild(l);
		  n.setL(((Leaf)n.getFirstChild()).getValue());
		  n.setM(((Leaf)n.getSecondChild()).getValue());
		}else{
		  n.setThirdChild(l);
		}
		return l;
	  }
	  else if(n.getNumChildren() == 3){
		int position = 0;
		Node g = new Node();
		if(v > ((Leaf)n.getThirdChild()).getValue()){
		  position = 4;
		}
		else if(v < ((Leaf)n.getThirdChild()).getValue() && v > ((Leaf)n.getSecondChild()).getValue()){
		  position = 3;
		}
		else if(v < ((Leaf)n.getSecondChild()).getValue() && v > ((Leaf)n.getFirstChild()).getValue()){
		  position = 2;
		}
		else{
		  position = 1;
		}
		if(v > ((Leaf)n.getThirdChild()).getValue()){
		  g.setSecondChild(new Leaf(v));
		  g.setFirstChild(n.getThirdChild());
		  n.setThirdChild(null);
		}
		else if(v < ((Leaf)n.getThirdChild()).getValue() && v > ((Leaf)n.getSecondChild()).getValue()){
		  g.setSecondChild(n.getThirdChild());
		  g.setFirstChild(new Leaf(v));
		  n.setThirdChild(null);
		}
		else if(v < ((Leaf)n.getSecondChild()).getValue() && v > ((Leaf)n.getFirstChild()).getValue()){
		  g.setSecondChild(n.getThirdChild());
		  g.setFirstChild(n.getSecondChild());
		  n.setThirdChild(null);
		  n.setSecondChild(new Leaf(v));
		}
		else{
		  g.setSecondChild(n.getThirdChild());
		  g.setFirstChild(n.getSecondChild());
		  n.setThirdChild(null);
		  n.setSecondChild(n.getFirstChild());
		  n.setFirstChild(new Leaf(v));
		}
		
		this.split(n, new Leaf(v));
		
	  }
	  else{
		//It is very unlikely for a node to have 1 child
		return null;
	  }
	}
  } 	

  /**
   * Insert the newChild node into n node, and split if needed
   * */
  private void split(Node n, Node newChild){
	if(n.getNumChildren() == 2){
	  
	}
	else if(n.getNumChildren() == 3){
	  
	}
	else{
	  //impossible to be 1 or other values
	}
	if(newChild instanceof Leaf){
	  int v = ((Leaf)newChild).getValue();
	  if(v > ((Leaf)n.getThirdChild()).getValue()){
		  g.setSecondChild(new Leaf(v));
		  g.setFirstChild(n.getThirdChild());
		  n.setThirdChild(null);
		}
		else if(v < ((Leaf)n.getThirdChild()).getValue() && v > ((Leaf)n.getSecondChild()).getValue()){
		  g.setSecondChild(n.getThirdChild());
		  g.setFirstChild(new Leaf(v));
		  n.setThirdChild(null);
		}
		else if(v < ((Leaf)n.getSecondChild()).getValue() && v > ((Leaf)n.getFirstChild()).getValue()){
		  g.setSecondChild(n.getThirdChild());
		  g.setFirstChild(n.getSecondChild());
		  n.setThirdChild(null);
		  n.setSecondChild(new Leaf(v));
		}
		else{
		  g.setSecondChild(n.getThirdChild());
		  g.setFirstChild(n.getSecondChild());
		  n.setThirdChild(null);
		  n.setSecondChild(n.getFirstChild());
		  n.setFirstChild(new Leaf(v));
		}
	}
  }

  @Override
  public Node delete(int v){
	// TODO Auto-generated method stub
	return null;
  }

  @Override
  public Node select(int kth) throws IndexOutOfBoundException {
	// TODO Auto-generated method stub
	return null;
  }

  /**Search node method recursively search a value from node n down to the leaf
   * Return value:
   * - Leaf instance: the leaf with same value is found
   * - Node instance: the value is not found and the last internal node visited is returned
   * */
  private Node searchNode(int v, Node n){
	//node is a leaf
	if(n instanceof Leaf) {
	  Leaf l = (Leaf)n;
	  if(l.getValue() == v){
		return l;
	  }
	  else{
		//if the value doesn't exist, return the last node visited
		System.out.println("Value "+ v +" not found. Last node visited: L(" + l.getParent().getL()+"), M("+l.getParent().getM()+")");
		return l.getParent();
	  }
	}

	//node is an internal vertex
	if(v <= n.getL()){
	  return searchNode(v, n.getFirstChild());
	}
	else{
	  if(n.getNumChildren() == 2 || v <= n.getM()){
		return searchNode(v, n.getSecondChild());
	  }
	  else{
		return searchNode(v, n.getThirdChild());
	  }
	}
  }

  public Node getParent() {
	return p;
  }

  public void setParent(Node p) {
	this.p = p;
  }

  public int getNumChildren() {
	return numChildren;
  }
  
  public int getL() {
	return L;
  }

  public void setL(int l) {
	L = l;
  }

  public int getM() {
	return M;
  }

  public void setM(int m) {
	M = m;
  }
}
