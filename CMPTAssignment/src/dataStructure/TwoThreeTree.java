package dataStructure;

import exceptions.ElementAlreadyExistException;
import exceptions.ElementNotFoundException;
import exceptions.IndexOutOfBoundException;
import interfaces.Tree;

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
  public Node search(int v) throws ElementNotFoundException {
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
