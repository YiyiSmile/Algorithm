package indi.tom.algorithm.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2020/8/7 12:27
 * @Version 1.0
 * @Description
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] elements = {21, 11, 38, 1, 5, 45, 34, 23, 7, 8, 66, 98, 54, 27, 33, 79};
        System.out.println(Arrays.toString(elements));
        insertSort(elements);
        System.out.println(Arrays.toString(elements));
    }
    public static void insertSort(int[] elements){
        if( elements == null || elements.length <= 0 )
            throw new IllegalArgumentException("The argument is illegal!");
        for(int i=1;i<elements.length;i++){
            int insertValue = elements[i];
            int insertIndex = i-1;
            while(insertIndex>=0 && elements[insertIndex]>insertValue){
                elements[insertIndex+1] = elements[insertIndex];
                insertIndex --;
            }
            elements[insertIndex+1] = insertValue;
        }

    }
}
