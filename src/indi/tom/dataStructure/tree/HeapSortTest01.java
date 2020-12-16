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
        heapSort(array);
        System.out.println(Arrays.toString(array));
    }
    private void heapSort(int[] array){
        //construct a initial max heap
        //adjust from the last non-leaf node to the first non-leaf node
        int length = array.length;
        for (int i = 2*(length -1)/2; i >= 0 ; i--) {
            adjust(array, i, length);
        }
        //swap first and last element in the max heap, remove the last element
        //then adjust again.
        while(length > 1){
            swap(array, 0, length -1);
            adjust(array, 0, length -1);
            length--;
        }
    }

    /**
     * Adjust a array/tree according to the given first non-leaf element index
     * and the array/tree length that participates.
     *
     * @param array original array/tree
     * @param index the non-leaf element index to start with
     * @param length length of the array to be adjusted
     */
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

    /**
     *
     * @param array
     * @param i
     * @param j
     */
    private void swap(int[] array, int i, int j){
        if(i > array.length - 1 || j > array.length -1) throw new IndexOutOfBoundsException();
        int temp =array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

