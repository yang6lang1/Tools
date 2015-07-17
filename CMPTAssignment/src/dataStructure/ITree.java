package dataStructure;


public interface ITree {
	public Node search(int v);
	public void insert(int v);
	public void delete(int v);
	public Node select(int kth);
}
