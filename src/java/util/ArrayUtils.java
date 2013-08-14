package util;

import java.util.Random;

public class ArrayUtils {

	public static Integer[] randomGen() {
		return randomGen(10);
	}
	
	public static Integer[] randomGen(int range) {
		Integer[] array = new Integer[range];
		
		for (int i = 0; i < range; ++i) {
			array[i] = i;
		}
		
		Random random = new Random();
		for (int i = array.length - 1; i >= 0; --i) {
			swap(array, i, random.nextInt(range));
		}
		
		return array;
	}
	
	public static <T> void swap(T[]a, int from, int to) {
		T tmp = a[from];
		a[from] = a[to];
		a[to] = tmp;
	}
}
