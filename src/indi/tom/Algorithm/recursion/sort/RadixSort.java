package indi.tom.Algorithm.recursion.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2019/11/11 19:36
 * @Version 1.0
 * @Description 基数排序，空间换时间.这里列出推导过程。在RadixSort1中给出最终方法。
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] array = {53,3,542,748,14,214};
        radixSort(array);
    }
    //基数排序
    public static void radixSort(int[] array){
        //推导过程
        //第一轮
        //定义一个二维数组，充当桶
        int[][] buckets = new int[10][array.length];
        //定义一个数组，下标代表第几个桶，值对应该桶中的元素个数。
        int[] elementsCountInBucket = new int[10];
        //遍历数组元素，按个位数加到对应的每一个桶中
        for (int i = 0; i < array.length; i++) {
            int digitOfElement = array[i] % 10;
            buckets[digitOfElement][elementsCountInBucket[digitOfElement]] = array[i];
            elementsCountInBucket[digitOfElement] ++;
        }
        //将桶中元素重新放回原数组。
        int current = 0;//定义原数组的当前下标。
        for (int i = 0; i < buckets.length; i++) {
            if(elementsCountInBucket[i] > 0){
                for (int j = 0; j < elementsCountInBucket[i]; j++) {
                    array[current++] = buckets[i][j];
                }
            }
            //将保存每个bucket元素个数的元素清零。
            elementsCountInBucket[i] = 0;
        }
        System.out.println(Arrays.toString(array));
        //第二轮
        //遍历数组元素，按个位数加到对应的每一个桶中
        for (int i = 0; i < array.length; i++) {
            int digitOfElement = array[i] /10 % 10;
            buckets[digitOfElement][elementsCountInBucket[digitOfElement]] = array[i];
            elementsCountInBucket[digitOfElement] ++;
        }
        //将桶中元素重新放回原数组。
        current = 0;//定义原数组的当前下标。
        for (int i = 0; i < buckets.length; i++) {
            if(elementsCountInBucket[i] > 0){
                for (int j = 0; j < elementsCountInBucket[i]; j++) {
                    array[current++] = buckets[i][j];
                }
            }
            elementsCountInBucket[i] = 0;
        }
        System.out.println(Arrays.toString(array));
        //第三轮
        for (int i = 0; i < array.length; i++) {
            int digitOfElement = array[i] /100 % 10;
            buckets[digitOfElement][elementsCountInBucket[digitOfElement]] = array[i];
            elementsCountInBucket[digitOfElement] ++;
        }
        //将桶中元素重新放回原数组。
        current = 0;//定义原数组的当前下标。
        for (int i = 0; i < buckets.length; i++) {
            if(elementsCountInBucket[i] > 0){
                for (int j = 0; j < elementsCountInBucket[i]; j++) {
                    array[current++] = buckets[i][j];
                }
            }
            elementsCountInBucket[i] = 0;
        }
        System.out.println(Arrays.toString(array));
    }
}
