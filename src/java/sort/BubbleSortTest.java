package sort;

import util.ArrayUtils;


public class BubbleSortTest {
	
	public static <T extends Comparable<T>> void bubbleSort(T[] a) {
		
		boolean swapped = true;
		int j = 0;
		while(swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < a.length - j; ++i) {
				if (a[i].compareTo(a[i+1]) > 0) {
					ArrayUtils.swap(a, i, i + 1);
					swapped = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Integer[] array = ArrayUtils.randomGen();
		
		for (int i = 0; i < array.length; ++i) {
			System.out.printf(i != array.length - 1 ? "%d " : "%d \n", array[i]);
		}
		
		bubbleSort(array);
		
		for (int i = 0; i < array.length; ++i) {
			System.out.printf(i != array.length - 1 ? "%d " : "%d \n", array[i]);
		}
	}
}
