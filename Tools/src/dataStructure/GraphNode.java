package dataStructure;

import java.util.ArrayList;

public class GraphNode {
	private ArrayList<GraphNode> neighbours;
	private boolean visited;
	private int start;
	private int end;
	
	public GraphNode(){
		neighbours = new ArrayList<GraphNode>();
		visited = false;
		start = end = -1;
	}
}
