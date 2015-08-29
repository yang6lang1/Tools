package test;

import java.util.ArrayList;
import java.util.List;

import dataStructure.MinHeap;

public class HeapTest {

	public static void main(String[] args){
		HeapTest t = new HeapTest();
		MinHeap<Integer> heap = new MinHeap<Integer>();
		heap.insert(29);
		System.out.println(heap.toString());
		
		heap.insert(20);
		System.out.println(heap.toString());
		
		heap.insert(39);
		System.out.println(heap.toString());
		
		heap.insert(32);
		System.out.println(heap.toString());
		
		heap.insert(22);
		System.out.println(heap.toString());

		heap.insert(1);
		System.out.println(heap.toString());

		heap.insert(22);
		System.out.println(heap.toString());

		heap.remove();
		System.out.println(heap.toString());

		heap.remove();
		System.out.println(heap.toString());
		
		List<Integer> list = new ArrayList<Integer>();
		list.add(99);
		list.add(88);
		list.add(77);
		list.add(66);
		list.add(55);
		list.add(44);
		list.add(33);
		list.add(22);
		list.add(11);
		System.out.println("Original List:");
		for(int i : list){
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println("Heapified List:");
		list = heap.heapify(list);
		for(int i : list){
			System.out.print(i + ", ");
		}
	}
}
