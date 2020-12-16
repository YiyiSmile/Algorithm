package indi.tom.dataStructure.tree;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2020/12/16 8:43
 * @Version 1.0
 * @Description
 */
public class HeapSortTest01 {
    @Test
    public void test(){
     int[] array = {9,5,3,6,7,0,1,2};
     int length = array.length;
        for (int i = 2*(length -1)/2; i >= 0 ; i--) {
            adjust(array, i, length);
        }
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
    private void heapSort(int[] array){
        int length = array.length;
        while(length > 1){
            swap(array, 0, length -1);
            adjust(array, 0, length -1);
            length--;
        }
    }

    private void adjust(int[] array, int index, int length){
        if(2*index + 1 >= length) return;
        int max = 0;
        if((2*index + 2) < length ){
            max = array[2*index + 1] < array[2*index +2]? 2*index + 2:2*index + 1;
        }else{
            max = 2*index + 1;
        }
        if(array[index] < array[max]){
            swap(array, index, max);
        }else{
            return;
        }
        adjust(array, max, length);
    }

    private void swap(int[] array, int i, int j){
        if(i > array.length - 1 || j > array.length -1) throw new IndexOutOfBoundsException();
        int temp =array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

