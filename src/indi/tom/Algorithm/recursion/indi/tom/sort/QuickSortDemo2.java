package indi.tom.Algorithm.recursion.indi.tom.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2020/8/8 16:58
 * @Version 1.0
 * @Description
 */
public class QuickSortDemo2 {
    public static void main(String[] args) {
//        int[] elements = {1,2,3,4,5};
        int[] elements = {21, 11, 38, 1, 5, 45, 34, 23, 7, 8, 66, 98, 54, 27, 33, 79};
        System.out.println(Arrays.toString(elements));
        quickSort2(elements, 0, elements.length - 1);
        System.out.println(Arrays.toString(elements));
    }

    public static void quickSort2(int[] elements, int left, int right) {
        if(left >= right ) return;
        int pivot = elements[(right + left) / 2];
        //l is the index that all elements on the left of l is smaller than pivot
        //r is the index that all elements on the left of r is bigger than pivot
        int l = left;
        int r = right;

        int temp;
        while (l < r) {
            while (elements[l] < pivot) {
                l++;
            }
            while(elements[r] > pivot){
                r--;
            }
            //when l==r, it means all elements on the left of pivot is smaller than pivot,
            //all elemtns on the right is greater than pivot
            if(l == r)
                break;
            //switch
            temp = elements[l];
            elements[l] = elements[r];
            elements[r] = temp;

        }
        //at this time point, l should be equal to r which is the index where pivot is located
        quickSort2(elements, left, l-1);
        quickSort2(elements, l+1,right );
    }
}
