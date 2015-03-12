package dataStructure;

public class BinarySearchTree <T extends Comparable<T>> {

	private BSTNode<T> root;

	public BinarySearchTree(){
		root = null;
	}

	/**Pre-order traverse*/
	public void traverse(BSTNode<T> root){
		if(root == null) return;

		System.out.println(root.getElement().toString());
		traverse(root.getLeft());
		traverse(root.getRight());
	}

	/**Pre-order traverse using stack*/
	public void traverseWithoutRecursion(){
		Stack<BSTNode<T>> stack = new Stack<BSTNode<T>>();

		stack.push(root);
		while(true){
			BSTNode<T> curr = null;
			try {
				curr = stack.pull();
				if(curr == null) break;
				System.out.println(curr.getElement().toString());
				if(curr.getRight() != null) stack.push(curr.getRight());
				if(curr.getLeft() != null) stack.push(curr.getLeft());
			} catch (Exception e) {
				System.out.println("End of traversing");
				break;
			}
		}
	}

	public BSTNode<T> getRoot(){
		return root;
	}
	
	private int lastNum = Integer.MIN_VALUE;

	public boolean checkBST(BSTNode<Integer> root){
	    if(root == null) return true;
	    
	    if(!checkBST(root.getLeft())) return false;
	    
	    if(lastNum != Integer.MIN_VALUE && lastNum >= root.getElement()) return false;
	    lastNum = root.getElement();
	    
	    if(!checkBST(root.getRight())) return false;

	    return true;
	}
}
