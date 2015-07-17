package dataStructure;


public class TwoThreeTree implements ITree{

  private Node root;
  private int size;

  public TwoThreeTree(){
	root = null;
	size = 0;
  }

  public TwoThreeTree(Node root, int size){
	this.root = root;
	this.size = size;
  }

  public Node getRoot(){
	return root;
  }

  public int getSize(){
	return size;
  }

  /**Searching the value v from root
   * Return value:
   * - null: value not found
   * - not null: return the leaf containing the value v 
   * */
  @Override
  public Node search(int v) {
	if(this.root == null) return null;
	Node n = searchNode(v, this.root);

	if(n instanceof Leaf){
	  //found the value
	  return n;
	}
	else{
	  return null;
	}
  }

  private Node searchNode(int v, Node n){
	if(n == null) return null;

	//node is a leaf
	if(n instanceof Leaf) {
	  Leaf l = (Leaf)n;
	  if(l.getValue() == v){
		return l;
	  }
	  else{
		//if the value doesn't exist, return the last node visited
		//System.out.println("Value "+ v +" not found. Last node visited: L(" + l.getParent().getL()+"), M("+l.getParent().getM()+")");
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

  /**Inserting the value v into this tree
   * Return value:
   * */
  @Override
  public void insert(int v) {
	if(this.root == null){
	  //empty tree, create a new node
	  root = new Leaf(v);
	  size++;
	  return;
	}

	if(size == 1){
	  Leaf l = new Leaf(v);
	  if(((Leaf)this.root).CompareTo(l) == 1){
		Leaf l2 = (Leaf)this.root;
		this.root = new Node();
		this.root.setFirstChild(l);
		this.root.setSecondChild(l2);
		this.root.updateL();
		this.root.updateM();
		this.root.updateSize();
		size++;
	  }
	  else if(((Leaf)this.root).CompareTo(l) == -1){
		Leaf l2 = (Leaf)this.root;
		this.root = new Node();
		this.root.setFirstChild(l2);
		this.root.setSecondChild(l);
		this.root.updateL();
		this.root.updateM();
		this.root.updateSize();
		size++;
	  }
	  return;
	}

	Node n = searchNode(v, this.root);
	if(n instanceof Leaf){
	  //the value already exist in the tree
	  return;
	}
	else{
	  insertNode(n, new Leaf(v));
	  size++;
	}
  }

  /**Insert the newChild node into n node, and split if needed
   * */
  private void insertNode(Node n, Node newChild){
	if(n.getNumChildren() == 2){
	  if(newChild.CompareTo(n.getFirstChild()) == -1){
		n.setThirdChild(n.getSecondChild());
		n.setSecondChild(n.getFirstChild());
		n.setFirstChild(newChild);
		if(newChild instanceof Leaf){
		  n.setL(((Leaf)newChild).getValue());
		  n.setM(((Leaf)n.getSecondChild()).getValue());
		}
		else{
		  n.updateL();
		  n.updateM();
		}
	  }
	  else if(newChild.CompareTo(n.getFirstChild()) == 1 && newChild.CompareTo(n.getSecondChild()) == -1){
		n.setThirdChild(n.getSecondChild());
		n.setSecondChild(newChild);
		if(newChild instanceof Leaf){
		  n.setL(((Leaf)n.getFirstChild()).getValue());
		  n.setM(((Leaf)n.getSecondChild()).getValue());
		}
		else{
		  n.updateL();
		  n.updateM();
		}
	  }
	  else{
		n.setThirdChild(newChild);
	  }
	  Node temp = n;
	  while(temp != null){
		temp.updateSize();
		temp = temp.getParent();
	  }
	}
	else if(n.getNumChildren() == 3){
	  //split the node
	  Node g = new Node();
	  if(newChild.CompareTo(n.getThirdChild()) == 1){
		g.setSecondChild(newChild);
		g.setFirstChild(n.getThirdChild());
		n.setThirdChild(null);
	  }
	  else if(newChild.CompareTo(n.getThirdChild()) == -1 && newChild.CompareTo(n.getSecondChild()) == 1){
		g.setSecondChild(n.getThirdChild());
		g.setFirstChild(newChild);
		n.setThirdChild(null);
	  }
	  else if(newChild.CompareTo(n.getSecondChild()) == -1 && newChild.CompareTo(n.getFirstChild()) == 1){
		g.setSecondChild(n.getThirdChild());
		g.setFirstChild(n.getSecondChild());
		n.setThirdChild(null);
		n.setSecondChild(newChild);
	  }
	  else{
		g.setSecondChild(n.getThirdChild());
		g.setFirstChild(n.getSecondChild());
		n.setThirdChild(null);
		n.setSecondChild(n.getFirstChild());
		n.setFirstChild(newChild);
	  }
	  g.updateL();
	  g.updateM();
	  g.updateSize();
	  n.updateL();
	  n.updateM();
	  n.updateSize();
	  
	  //recursively check upper level
	  if(n.getParent() != null){
		insertNode(n.getParent(), g);
	  }
	  else{
		//n is already the root so it has no parent
		root = new Node();
		if(g.CompareTo(n) == 1){
		  root.setFirstChild(n);
		  root.setSecondChild(g);
		}
		else{
		  root.setFirstChild(g);
		  root.setSecondChild(n);
		}
		root.updateL();
		root.updateM();
		root.updateSize();
	  }
	}
	else{
	  //impossible to be 1 or other values
	  System.out.println("Error: a node cannot have one child");
	}
  }

  @Override
  public void delete(int v) {
	if(this.root == null) return;

	if(this.size == 1){
	  if(((Leaf)this.root).getValue() == v){
		this.root = null;
		size = 0;
	  }
	  return;
	}

	Node n = searchNode(v, this.root);
	if(n instanceof Leaf){
	  //The node with value v is found in the tree, delete it
	  deleteNode(n.getParent(), n);
	  size--;
	}
	else{
	  //The node with value v is not found in the tree
	  return;
	}
  }

  /**Recursively deleting the node
   * - n: parent node to be deleted from
   * - delNode: the node to be deleted 
   * */
  public void deleteNode(Node n, Node delNode){
	if (n == null){
	  //delNode is already the root
	  this.root.removeChild(delNode);
	  this.root.getFirstChild().setParent(null);
	  this.root = this.root.getFirstChild();
	  return;
	}

	if(n.getNumChildren() == 3){
	  n.removeChild(delNode);
	  Node temp = n;
	  while(temp != null){
		temp.updateSize();
		temp = temp.getParent();
	  }
	  return;
	}
	else if(n.getNumChildren() == 2){
	  //Possibility 1: parent is the root
	  if(n.getParent() == null){
		n.removeChild(delNode);
		n.getFirstChild().setParent(null);
		this.root = n.getFirstChild();
	  }
	  else{ //Possibility 2: Parent is not root
		if(n.getParent().getFirstChild().CompareTo(n) == 0){ 
		  //this n node is the first child of its parent
		  Node s = n.getParent().getSecondChild();
		  if(s.getNumChildren() == 2){
			n.removeChild(delNode);
			s.setThirdChild(s.getSecondChild());
			s.setSecondChild(s.getFirstChild());
			s.setFirstChild(n.getFirstChild());
			s.updateL();
			s.updateM();
			s.updateSize();
			deleteNode(n.getParent(), n);
		  }
		  else if(s.getNumChildren() == 3){
			n.removeChild(delNode);
			n.setSecondChild(s.getFirstChild());
			n.updateL();
			n.updateM();
			s.setFirstChild(s.getSecondChild());
			s.setSecondChild(s.getThirdChild());
			s.setThirdChild(null);
			s.updateL();
			s.updateM();
			s.updateSize();
		  }
		}
		else if(n.getParent().getSecondChild().CompareTo(n) == 0){
		  //this n node is the second child of its parent
		  Node f = n.getParent().getFirstChild();
		  if(f.getNumChildren() == 2){
			n.removeChild(delNode);
			f.setThirdChild(n.getFirstChild());
			f.updateL();
			f.updateM();
			f.updateSize();
			deleteNode(n.getParent(), n);
		  }
		  else if(f.getNumChildren() == 3){
			n.removeChild(delNode);
			n.setSecondChild(n.getFirstChild());
			n.setFirstChild(f.getThirdChild());
			n.updateL();
			n.updateM();
			f.setThirdChild(null);
			f.updateL();
			f.updateM();
			f.updateSize();
		  }
		}
	  }
	}
  }

  /**determining the kth smallest element in log time
   * Node:
   * 1th is the smallest index up to tree size
   * */
  @Override
  public Node select(int kth) {
	if(root == null) return null;
	if(kth > root.getSize()) return null;
	
	return selectNode(this.root, kth);
  }
  
  private Node selectNode(Node n, int kth){
	if(n == null) return null;
	if(n instanceof Leaf) return n;
	if(n.getFirstChild() != null && n.getFirstChild().getSize() >= kth){
	  return selectNode(n.getFirstChild(), kth);
	}
	else if(n.getSecondChild()!= null && n.getSecondChild().getSize() >= kth){
	  return selectNode(n.getSecondChild(), kth - n.getFirstChild().getSize());
	}
	else if(n.getThirdChild()!= null && n.getThirdChild().getSize() >= kth){
	  return selectNode(n.getThirdChild(), kth - n.getFirstChild().getSize() - n.getSecondChild().getSize());
	}
	else{
	  System.out.println("Wrong kth number");
	  return null;
	}
  }
  
  /**Search the element in the tree
   * if return 0, not found
   * if return a value > 0, it is found. value is its order in tree
   * */
  public int searchAndDetermineOrder(int v){
	if(this.root == null) return 0;
	int result = searchAndDetermineOrderInNode(this.root, v);
	return result;
  }
  
  private int searchAndDetermineOrderInNode(Node n, int v){
	//node is a leaf
	if(n instanceof Leaf) {
	  Leaf l = (Leaf)n;
	  if(l.getValue() == v){
		return 1;
	  }
	  else{
		return 0;
	  }
	}

	//node is an internal vertex
	if(v <= n.getL()){
	  return searchAndDetermineOrderInNode(n.getFirstChild(), v);
	}
	else{
	  if(n.getNumChildren() == 2 || v <= n.getM()){
		int result = searchAndDetermineOrderInNode(n.getSecondChild(), v);
		if(result == 0){
		  return 0;
		}
		else{
		  if(n.getFirstChild()!= null){
			result += n.getFirstChild().getSize();
		  }
		  return result;
		}
	  }
	  else{
		int result = searchAndDetermineOrderInNode(n.getThirdChild(), v);
		if(result == 0){
		  return 0;
		}
		else{
		  if(n.getFirstChild()!= null){
			result += n.getFirstChild().getSize();
		  }
		  if(n.getSecondChild()!= null){
			result += n.getSecondChild().getSize();
		  }
		  return result;
		}
	  }
	}
  }
}
