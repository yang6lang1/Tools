package interfaces;

import dataStructure.*;
import exceptions.*;

public interface Tree {
	public Node search(int v) throws ElementNotFoundException;
	public Node insert(int v) throws ElementAlreadyExistException;
	public Node delete(int v) throws ElementNotFoundException;
	public Node select(int kth) throws IndexOutOfBoundException;
}
