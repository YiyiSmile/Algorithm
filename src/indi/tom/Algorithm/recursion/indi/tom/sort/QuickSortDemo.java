package indi.tom.Algorithm.recursion.indi.tom.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2020/8/8 11:03
 * @Version 1.0
 * @Description my own version, which is not as good as the one given by the book
 */
public class QuickSortDemo {
    public static void main(String[] args) {
        int[] elements = {21,11,5,1,5,45,34,23,7,8,66,98,54,34,33,79};
        System.out.println(Arrays.toString(elements));
        quickSort(elements, 0, elements.length - 1);
        System.out.println(Arrays.toString(elements));

    }

    public static void quickSort(int[] elements, int start, int end){
        if(start >= end) return;
        //value of base element
        int value = elements[end];
        //index where base element is hosted
        int index1 = end;
        //index for the element to be compared with base element
        int index2 = start;
        for (int i = start; i < end; i++) {
            if(elements[index2] > value){
                elements[index1] = elements[index2];
                index1--;
                //shift all the elements to the left
                for (int j = index2; j < index1; j++) {
                    elements[j] = elements[j+1];
                }
            }else
                index2 ++;
        }
        elements[index1] = value;
        quickSort(elements,start,index1-1);
        quickSort(elements,index1+1,end);


    }
}
