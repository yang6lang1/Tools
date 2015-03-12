package dataStructure;

import java.util.ArrayList;
import java.util.List;

public class LargeStack{

    private List<Stack> stacks;
    private Stack curr;
    private int size;
    
    public LargeStack(){
        stacks = new ArrayList<Stack>();
        curr = new Stack();
        stacks.add(curr);
        size = 0;
    }
    
    public void push(int element){
        if (curr == null || curr.isFull()) {
            Stack newStack = new Stack();
            stacks.add(newStack);
            curr = newStack;
        }
        
        curr.push(element);
        size++;
    }
    
    public int pop() throws Exception {
        if (stacks == null || stacks.isEmpty() || size == 0) throw new Exception("Empty stack!");
        
        int e;
        if (size > 1) {
            if (curr.isEmpty()){
                stacks.remove(stacks.size() -1);
                curr = stacks.get(stacks.size() - 1);
            }
        }
        e = curr.pop();
        size --;
        return e;
    }
    
    public int popAt(int StackID) throws Exception {
        if(StackID < 0 || StackID >= stacks.size()) throw new Exception("Invalid stack ID");

        int e;
        if(StackID == stacks.size() - 1){
            try {
                e = pop();
            }
            catch (Exception excption){
                throw new Exception(excption.getMessage());
            }
            return e;
        }
        
        Stack temp = stacks.get(StackID);
        e = temp.pop();
        if(temp.isEmpty()){
            stacks.remove(StackID);
        }
        size --;
        return e;
    }
    
    public String toString(){
    	String o = "";
    	for (int i = 0; i < stacks.size() ; i++){
    		o += "Stack: "+ i+"\n"+stacks.get(i).toString();
    	}
    	
    	return o;
    }
    
    class Stack{
        public static final int MAX_CAP = 5;
        private Node tail;
        private int size;
        
        //constructors
        
        public void push(int element){
        	Node newNode = new Node(element, null);
        	if (size != 0){
        		newNode.next = tail;
        	}
        	tail = newNode;
        	size ++;
        };
        
        public int pop() throws Exception{
        	if(size == 0) throw new Exception("Empty stack");
            int element =  tail.element;
            tail = tail.next;
            size --;
            
            return element;
        };
        
        public boolean isFull(){
            return size >= MAX_CAP;
        }
        
        public boolean isEmpty(){
            return size == 0;
        }
        
        public String toString(){
        	String o ="";
        	if(size== 0) return o;
        	Node temp = tail; 
        	int index = 0;
        	while(temp != null){
        		o += "index: " + index + " element: " + temp.element + "\n";
        		temp = temp.next;
        		index += 1;
        	}
        	
        	return o;
        }
    }
    
    class Node{
        public int element;
        public Node next;
        
        public Node(int e, Node n){
        	element = e; next = n;
        }
    }
    
    public static void main(String[] args){
    	LargeStack largeStack = new LargeStack();
    	largeStack.push(1);
    	largeStack.push(2);
    	largeStack.push(3);
    	largeStack.push(4);
    	largeStack.push(5);
    	largeStack.push(6);
    	largeStack.push(7);
    	largeStack.push(8);
    	largeStack.push(9);
    	largeStack.push(10);
    	largeStack.push(11);
    	
    	try {
    		largeStack.pop();
    		largeStack.pop();
    		largeStack.popAt(1);
    		largeStack.popAt(0);
    		largeStack.popAt(0);
    		largeStack.popAt(0);
    		largeStack.popAt(0);
    		largeStack.popAt(0);
    		largeStack.popAt(0);largeStack.popAt(0);
    		largeStack.popAt(0);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return;
		}
    	System.out.println(largeStack.toString());
    }
}
