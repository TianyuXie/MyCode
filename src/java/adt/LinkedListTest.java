package adt;

import java.util.Random;

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
	
	public static Node<Integer> randomGen(int num) {
		Random random = new Random();

		Node<Integer> head, currNode, loopNode; 
		head = new Node<Integer>(random.nextInt(num)); currNode = head;
		for (int index = 0; index < num; ++index) {
			loopNode = new Node<Integer>(random.nextInt(num));
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
