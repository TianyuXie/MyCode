package adt;

import util.ArrayUtils;

public class LinkedListTest {
	
	static class Node<T> {
		
		private T mData;
		private Node<T> mNext;
		
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
		} else if (null == head.getNext()) {
			return head;
		}
		
		Node<T> newHead = new Node<T>(null);
		
		Node<T> curNode, nextNode, loopNode;
		curNode = head; nextNode = head.getNext(); head.setNext(null);
		
		while (nextNode != null) {
			loopNode = nextNode.getNext();
			
			newHead.setNext(nextNode);
			nextNode.setNext(curNode);
			curNode = nextNode;
			nextNode = loopNode;
		}
		
		return newHead.getNext();
	}
	
	public static void main(String[] args) {
		Node<Integer> head = randomGen(10);
		
		Node<Integer> ptr = head;
		do {
			System.out.printf("%d ", ptr.getData());
		} while(null != (ptr = ptr.getNext()));

		System.out.println();

		ptr = reverse(head);
		do {
			System.out.printf("%d ", ptr.getData());
		} while(null != (ptr = ptr.getNext()));
	}
}
