package dataStructure;

public class BSTNode<T extends Comparable<T>> implements Comparable<BSTNode<T>>{
  
  public BSTNode<T> left;
  public BSTNode<T> right;
  public T element;
  
  public BSTNode(){
	this(null, null, null);
  }

  public BSTNode(T element, BSTNode<T> left, BSTNode<T> right){
	this.element = element;
	this.left = left;
	this.right = right;
  }

  public BSTNode<T> getLeft() {
	return left;
  }

  public void setLeft(BSTNode<T> left) {
	this.left = left;
  }

  public BSTNode<T> getRight() {
	return right;
  }

  public void setRight(BSTNode<T> right) {
	this.right = right;
  }

  public T getElement() {
	return element;
  }

  public void setElement(T element) {
	this.element = element;
  }

  @Override
  public int compareTo(BSTNode<T> o) {
	return this.element.compareTo(o.getElement());
  }
}
