package sort;

import java.lang.reflect.Array;

import util.ArrayUtils;

public class MergeSortTest {
	
	@SuppressWarnings("unchecked")
	public static <T extends Comparable<T>> void mergeSort(T[] a) {
		T[] tmp = ((Object)a == (Object)Object[].class) 
				? (T[]) new Object[a.length]
				: (T[]) Array.newInstance(a.getClass().getComponentType(), a.length);
		mergeSort(a, 0, a.length - 1, tmp);
	}
	
	private static <T extends Comparable<T>> void mergeSort(T[] a, int from, int to, T[] tmp) {
		
		if (from < to) {
			int mid = (from + to) >>> 1;
			mergeSort(a, from, mid, tmp);
			mergeSort(a, mid + 1 , to, tmp);
			merge(a, from, to, tmp);
		}
	}
	
	private static <T extends Comparable<T>> void merge(T[] a, int from, int to, T[] tmp) {
		
		int mid = (from + to) >>> 1;
		
		int i = from, j = mid + 1;
		int k = 0;
		
		while (i <= mid && j <= to) {
			if (a[i].compareTo(a[j]) > 0) {
				tmp[k++] = a[i++];
			} else {
				tmp[k++] = a[j++];
			}
		}
		
		while (i <= mid) {
			tmp[k++] = a[i++];
		}
		
		while (j <= to) {
			tmp[k++] = a[j++];
		}
		
		for (int offset = 0; offset < k; ++offset)	{
			a[from + offset] = tmp[offset];
		}
	}

	public static void main(String[] args) {
		Integer[] array = ArrayUtils.randomGen();
		
		for (Integer element : array) {
			System.out.printf("%d ", element);
		}
		
		System.out.println();
		
		mergeSort(array);
	
		for (Integer element : array) {
			System.out.printf("%d ", element);
		}
	}
}
