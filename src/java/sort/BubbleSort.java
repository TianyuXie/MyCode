package sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BubbleSort {
	
	public static <T extends Comparable<T>> void sort(T[] a) {
		
		boolean swapped = true;
		int j = 0;
		while(swapped) {
			swapped = false;
			j++;
			for (int i = 0; i < a.length - j; ++i) {
				if (a[i].compareTo(a[i+1]) > 0) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
		}
	}
	
	public static <T> void swap(T[] a, int fromIndex, int toIndex) {
		T tmp = a[fromIndex];
		a[fromIndex] = a[toIndex];
		a[toIndex] = tmp;
	}

	public static void main(String[] args) {
		Random r = new Random(System.currentTimeMillis());
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < 25; ++i) {
			list.add(r.nextInt(100));
		}
		
		Integer[] array = list.toArray(new Integer[0]);
		
		sort(array);
		
		for (Integer i : array) {
			System.out.println(i);
		}
	}
}
