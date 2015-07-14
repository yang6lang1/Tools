package dataStructure;

import interfaces.Tree;
import exceptions.IndexOutOfBoundException;

public class TwoThreeTree implements Tree{

  private Node root;
  private int size;

  public TwoThreeTree(){
	root = null;
	size = 0;
  }

  public Node getRoot(){
	return root;
  }

  public int getSize(){
	return size;
  }

  @Override
  public Node search(int v) throws ElementNotFoundException{
	return searchNode(v, root);
  }

  private Node searchNode(int v, Node n){
	if(n == null) return null;
	if(n.getFirstChild() == null) {
	  //node is a leaf
	  Leaf l = (Leaf)n;
	  if(l.getValue() == v){
		return l;
	  }
	  else{
		return null;
	  }
	}

	//node is an internal vertex
	if(v < n.getL()){
	  return searchNode(v, n.getFirstChild());
	}
	else{
	  if(n.getNumChildren() == 2 || v < n.getM()){
		return searchNode(v, n.getSecondChild());
	  }
	}
	return null;
  }

  @Override
  public Node insert(int v) throws ElementAlreadyExistException {
	return null;
  }

  @Override
  public Node delete(int v) throws ElementNotFoundException {
	return null;
  }

  @Override
  public Node select(int kth) throws IndexOutOfBoundException {
	return null;
  }
}
