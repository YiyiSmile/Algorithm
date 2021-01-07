package indi.tom.algorithm.search;

import org.junit.Test;

/**
 * @Author: Tom
 * @Date: 2021年1月7日 下午5:26:29
 * @Version: 1.0
 * @Description: two implement of binary search: look and recursion
 */
public class BinarySearch {

	@Test
	public void test01() {
		int[] array = {0,1,2,3,4,5,6,7,8,9,10};
		System.out.println(searchWithLoop(array, 6));
		System.out.println(searchRecursively(array, 6, 0, 9));
	}

	/**
	 * 
	 * @Description: use loop to search @param @return -1 if not found, the index of
	 * target otherwise. @throws
	 */
	private int searchWithLoop(int[] array, int target) {
		if (null == array || array.length == 0)
			throw new IllegalArgumentException("The given array can't be null or 0 length size");
		int left = 0;
		int right = array.length;
		int mid = (left + right) / 2;
		while (left <= right) {
			if (target == array[mid]) {
				return mid;
			} else if (target > array[mid]) {
				left = mid;
				left++;
			} else if (target < array[mid]) {
				right = mid;
				right--;
			}
			mid = (left + right) / 2;
		}
		return -1;
	}
	
	private int searchRecursively(int[] array, int target, int left, int right) {
		if (null == array || array.length == 0)
			throw new IllegalArgumentException("The given array can't be null or 0 length size");
		if(left > right) return -1;
		int mid = (left + right) / 2;
		
		if(target == array[mid]) {
			return mid;
		}else if(target > array[mid]) {
			left = mid + 1;
		}else if(target < array[mid]) {
			right = mid - 1;
		}
		return searchRecursively(array, target, left, right);
		
	}

}
