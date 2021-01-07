package indi.tom.algorithm.kmp;

import org.junit.Test;

/**
 * @Author: Tom
 * @Date: 2021年1月7日 下午5:06:53   
 * @Version: 1.0
 * @Description: brute force or naive algorithm for string search
 */
public class StringSearchBruteForce {
	

	
	@Test
	public void test01(){
		
		String source = "Hello world";
		String target = "llo";
		
		System.out.println(search(source, target));
		
	}
	/**
	 * 
	* @Description: use brute force match algorithm to search the substring
	* @param 
	* @return   the first char index of the matched substring in the source string. Return -1 if not found; 
	* @throws
	 */
	private int search(String source, String target) {
		char[] src = source.toCharArray();
		char[] dest = target.toCharArray();
		
		int i=0,j=0;
		
		while(i < src.length && j < dest.length) {
			if(src[i] == dest[j]) {
				i++;
				j++;
			}else {
				i = i - (j-1);
				j = 0;
			}
		}
		
		if(j == target.length()) {
			return i - dest.length;
		}else {
			return -1;
		}
	}
}
