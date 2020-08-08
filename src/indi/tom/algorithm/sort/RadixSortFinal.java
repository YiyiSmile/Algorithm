package indi.tom.algorithm.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2019/11/11 19:36
 * @Version 1.0
 * @Description 基数排序，最终方法
 */
public class RadixSortFinal {
    public static void main(String[] args) {
        int[] array = {53,3,542,748,14,214};
        radixSort(array);
    }
    //排序函数
    public static void radixSort(int[] array){
        //定义一个二维数组，充当桶
        int[][] buckets = new int[10][array.length];
        //定义一个数组，下标代表第几个桶，值对应该桶中的元素个数。
        int[] elementsCountInBucket = new int[10];
        //要检查的元素的某一位上的数字
        int digitOfElement;
        //找出给定数组的最大数，以便下一步算出该最大数的位数
        int max = array[0];
        for (int i = 0; i < array.length; i++) {
            if(max < array[i]){
                max = array[i];
            }
        }
        //算出数组中最大数有多少位数字，即是我们要做多少轮入桶，出桶的过程
        int numberOfDigits = (max + "").length();
        for (int i = 0; i < numberOfDigits; i++) {
            //遍历数组元素，按个位数加到对应的每一个桶中
            for (int j = 0; j < array.length; j++) {
                digitOfElement = array[j] / (int)Math.pow(10, i) % 10;
                buckets[digitOfElement][elementsCountInBucket[digitOfElement]] = array[j];
                elementsCountInBucket[digitOfElement] ++;
            }
            //将桶中元素重新放回原数组。
            int current = 0;//定义原数组的当前下标。
            for (int j = 0; j < buckets.length; j++) {
                if(elementsCountInBucket[j] > 0){
                    for (int k = 0; k < elementsCountInBucket[j]; k++) {
                        array[current++] = buckets[j][k];
                    }
                }
                //将保存每个bucket元素个数的元素清零。
                elementsCountInBucket[j] = 0;
            }
        }
        System.out.println(Arrays.toString(array));
    }
}
