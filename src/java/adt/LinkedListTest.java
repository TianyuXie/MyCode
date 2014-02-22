package adt;

import util.ArrayUtils;

public class LinkedListTest {
	
	static class Node<T> {
		
		private T mData;
		private Node<T> mNext;
		
		public Node() {
			this(null);
		}
		
		public Node(T data) {
			this(data, null);
		}
		
		public Node(T data, Node<T> next) {
			mData = data;
			mNext = next;
		}
		
		public void setData(T data) {
			mData = data;
		}
		
		public void setNext(Node<T> next) {
			mNext = next;
		}
		
		public T getData() {
			return mData;
		}
		
		public Node<T> getNext() {
			return mNext;
		}
		
	}
	
	public static Node<Integer> randomGen(int range) {
		Integer[] array = ArrayUtils.randomGen(range);

		Node<Integer> head, currNode, loopNode; 
		head = new Node<Integer>(array[0]); currNode = head;
		for (int i = 1; i < range; ++i) {
			loopNode = new Node<Integer>(array[i]);
			currNode.setNext(loopNode);
			currNode = currNode.getNext();
		}
		
		return head;
	}
	
	public static <T> Node<T> reverse(Node<T> head) {
		if (null == head) {
			return null;
		}
		
		Node<T> new_head = new Node<T>();
		
		Node<T> node_cur, node_next;
		
		new_head.setNext(head);
		node_cur = head.getNext();
		
		while (null != node_cur) {
			node_next = node_cur.getNext();
			
			node_cur.setNext(new_head.getNext());
			new_head.setNext(node_cur);
			
			node_cur = node_next;
		}
		
		head.setNext(null);
		
		return new_head.getNext();
	}
	
	public static void print(Node<?> head) {
		Node<?> ptr = head;
		do {
			System.out.printf("%s ", ptr.getData().toString());
		} while (null != (ptr = ptr.getNext()));
		
		System.out.println();
	}
	
	public static void main(String[] args) {
		Node<Integer> head = randomGen(10);
		
		print(head);

		head = reverse(head);
		
		print(head);
	}
}
