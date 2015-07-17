package dataStructure;


public class Node {
  private int L;
  private int M;
  private Node f;
  private Node s;
  private Node t;
  private Node p;
  private int size;
  
  private int numChildren;

  public Node(){
	M = L = 0;
	p = f = s = t = null;
	numChildren = 0;
	size = 0;
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
	if(t != null){
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

  public void updateL(){
	if(this instanceof Leaf){
	  return;
	}
	else{
	  if(f == null) return;
	  if(this.f != null && this.f instanceof Leaf){
		//if children are leaves, we get the value of the first child
		this.L = ((Leaf)this.f).getValue();
	  }
	  else{
		this.L = max(this.f);
	  }
	}
  }

  public void updateM(){
	if(this instanceof Leaf){
	  return;
	}
	else{
	  if(s == null) return;
	  if(this.s != null && this.s instanceof Leaf){
		this.M = ((Leaf)this.s).getValue();
	  }
	  else{
		if(this.s != null){
		  this.M = max(this.s);
		}
		else{
		  this.M = this.L;
		}
	  }
	}
  }

  /**Recursively find the max value of a node's subtree\
   * Efficiency: O(Logn)
   * */
  public int max(Node n){
	if(n == null) return 0;
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
  
  public void removeChild(Node n){
	if(this.f != null && this.f.CompareTo(n) == 0){
	  this.setFirstChild(this.s);
	  this.setSecondChild(this.t);
	  this.setThirdChild(null);
	  return;
	}
	
	if(this.s != null && this.s.CompareTo(n) == 0){
	  this.setSecondChild(this.t);
	  this.setThirdChild(null);
	  return;
	}
	
	if(this.t != null && this.t.CompareTo(n) == 0){
	  this.setThirdChild(null);
	  return;
	}
	this.updateSize();
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

  /**Compare the order of two nodes
   * Return value:
   * -1: current node is to the left of the other node
   * 1: current node is to the right of the other node
   * 0: current node is equal to the other node
   * -2: invalid comparison
   * */
  public int CompareTo(Node anotherNode){
	if(this.getL() > anotherNode.getM()){
	  return 1;
	}
	else if(this.getM() < anotherNode.getL()){
	  return -1;
	}
	else if(this.getL() == anotherNode.getL() && this.getM() == anotherNode.getM()){
	  return 0;
	}
	else{
	  return -2;
	}
  }
  
  public String toString(){
	return "[L("+this.L+"), M("+this.M+")]:"+size;
  }

  public void updateSize(){
	if(this instanceof Leaf){
	  size = 1;
	}
	else{
	  int sum = 0;
	  if(this.f != null){
		sum += this.f.getSize();
	  }
	  if(this.s != null){
		sum += this.s.getSize();
	  }
	  if(this.t != null){
		sum += this.t.getSize();
	  }
	  size = sum;
	}
  }
  
  public void setSize(int s){
	this.size = s;
  }
  
  public int getSize() {
	return size;
  }
}
