package search;

public class BinarySearch {
	
	public static <T extends Comparable<T>> int binarySearch(T[] a, T key) {
		
		return binarySearch(a, 0, a.length - 1, key);
	}
	
	private static <T extends Comparable<T>> int binarySearch(T[] a, int fromIndex, int toIndex, T key) {
		int low = fromIndex;
		int high = toIndex;
		
		while (low <= high) {
			int mid = (low + high) >>> 1;
			Comparable<T> midVal = (Comparable<T>)a[mid];
			int cmp = midVal.compareTo(key);
			
			if (cmp < 0) {
				low = mid + 1;
			} else if (cmp > 0) {
				high = mid - 1;
			} else {
				return mid;
			}
		}
		
		return -(low);
	}
	
	public static void main(String[] args) {
		Integer[] arrays = {1, 1, 3, 4, 5, 9, 10, 13, 13, 13, 14};
		
		int index = binarySearch(arrays, 5);
		System.out.println(index);
	}
}
