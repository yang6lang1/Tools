package interfaces;

import dataStructure.*;
import exceptions.*;

public interface Tree {
	public Node search(int v);
	public Node insert(int v);
	public Node delete(int v);
	public Node select(int kth) throws IndexOutOfBoundException;
}
