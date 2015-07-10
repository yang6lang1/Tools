package dataStructure;

public class Node {
	private int L;
	private int M;
	private Node leftTree;
	private Node rightTree;
	
	public Node(){
		M = L = 0;
		leftTree = rightTree = null;
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

	public Node getLeftTree() {
	  return leftTree;
  }

	public void setLeftTree(Node leftTree) {
	  this.leftTree = leftTree;
  }

	public Node getRightTree() {
	  return rightTree;
  }

	public void setRightTree(Node rightTree) {
	  this.rightTree = rightTree;
  }
}
