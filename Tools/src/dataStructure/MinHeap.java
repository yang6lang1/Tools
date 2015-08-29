package dataStructure;

import java.util.ArrayList;
import java.util.List;

public class MinHeap<T extends Comparable<T>> {
	private List<T> elements;
	
	public MinHeap(){
		elements = new ArrayList<T>();
	}
	
	public void insert(T element){
		if(elements == null) elements = new ArrayList<T>();
		if(element == null) return;
		
		elements.add(element);
		int index = elements.size() - 1;
		elements = bubbleUp(elements, index);
	}
	
	public T remove(){
		if(elements == null || elements.size() == 0) return null;
		T min = null;
		int lastIndex = elements.size() - 1;
		
		min = elements.get(0);
		elements.set(0, elements.remove(lastIndex));
		elements = bubbleDown(elements, 0);
		return min;
	}
	
	public List<T> heapify(List<T> list){
		int size = list.size();
		List<T> result = list;
		for(int i = size / 2 - 1; i >= 0; i--){
			result = bubbleDown(result, i);
		}
		
		return list;
	}
	
	private List<T> bubbleUp(List<T> elements, int index){
		if(elements == null || elements.size() == 0) return elements;
		if(index < 0 || index > elements.size() - 1) return elements;
		
		//base case
		if(index == 0) return elements;
		
		int parIndex = (index - 1) / 2;
		if(elements.get(index).compareTo(elements.get(parIndex)) <= 0){
			T temp = elements.get(index);
			elements.set(index, elements.get(parIndex));
			elements.set(parIndex, temp);
			return bubbleUp(elements, parIndex);
		}
		else{
			return elements;
		}
	}
	
	private List<T> bubbleDown(List<T> elements, int index){
		if(elements == null || elements.size() == 0) return elements;
		if(index < 0 || index > elements.size() - 1) return elements;
		
		//base case
		if(index * 2 + 1 > elements.size() - 1) return elements;
		
		//special case
		if(index * 2 + 2 > elements.size() - 1){
			if(index * 2 + 1 <= elements.size() - 1){
				if(elements.get(index * 2 + 1).compareTo(elements.get(index)) <= 0){
					T temp = elements.get(index);
					elements.set(index, elements.get(index * 2 + 1));
					elements.set(index * 2 + 1, temp);
					return bubbleDown(elements, index * 2 + 1);
				}
				else{
					return elements;
				}
			}
			else{
				return elements;
			}
		}
		else{
			if(elements.get(index * 2 + 1).compareTo(elements.get(index * 2 + 2)) <= 0){
				T temp = elements.get(index);
				elements.set(index, elements.get(index * 2 + 1));
				elements.set(index * 2 + 1, temp);
				return bubbleDown(elements, index * 2 + 1);
			}
			else{
				T temp = elements.get(index);
				elements.set(index, elements.get(index * 2 + 2));
				elements.set(index * 2 + 2, temp);
				return bubbleDown(elements, index * 2 + 2);
			}
		}
	}
	
	public String toString(){
		StringBuffer sb = new StringBuffer();
		for(T e : elements){
			sb.append(e.toString() + ", ");
		}
		return sb.toString();
	}
}
