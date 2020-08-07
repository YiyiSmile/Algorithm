package indi.tom.Algorithm.recursion.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Tom
 * @Date 2019/11/11 13:48
 * @Version 1.0
 * @Description 合并排序，采用分治策略(divide and conqure)
 */
public class MergeSort {
    public static void main(String[] args) {
//        int[] array = {8, 4, 5, 7, 1, 3, 6, 2};
//        int[] temp = new int[array.length];
//        mergeSort(array, 0, 7,temp );
//        System.out.println(Arrays.toString(array));

        //速度测试
        int[] array1 = new int[8000000];
        int[] temp = new int[array1.length];
        for (int i = 0; i < 8000000; i++) {
            array1[i] = (int)(Math.random()*8000000);
        }
        //开始时间
        Date date1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间：" + sdf.format(date1));
        mergeSort(array1, 0, 8000000-1, temp);
        //结束时间
        System.out.println("结束时间：" + sdf.format(new Date()));
    }
    /**
     * 分 + 合 方法
     * @param array
     * @param left
     * @param right
     * @param temp
     */
    public static void mergeSort(int[] array, int left, int right, int[] temp){
        if(left < right){
            int mid = (right + left) / 2;
            //左子数组合并排序
            mergeSort(array, left, mid, temp);
            //右子数组合并排序
            mergeSort(array, mid + 1, right, temp);
            //合并
            merge(array, left, mid, right, temp);
        }
    }

    /**
     *     合并方法：
     *
     *     合并两个排好序的数组，并拷贝到原数组中
     *     根教材中的方法一致。这里mid是第二个数组的第一个元素的前一个元素，也是第一个子数组的最后一个元素。
     * @param array 原始数组
     * @param left  原始数组要合并的最左边的元素索引
     * @param mid   原始数组要合并的右子数组最左边的元素索引 减一，也即左子数组的最后一个元素的索引
     * @param right 原始数组要合并的最左右的元素索引
     * @param temp  临时数组，保存合并结果，最后要将临时数组的元素全部拷贝到原始数组中
     */
    public static void merge(int[] array, int left, int mid, int right, int[] temp){
        //左子数组指针
        int i = left;
        //又子数组指针
        int j = mid + 1;
        //保存临时数组的当前元素的指针
        int current = 0;
        //1. 将两个子数组的元素按顺序加到临时数组中，直到其中一个子数组遍历完
        while(i <= mid && j <= right){
            if(array[i] < array[j]){
                temp[current] = array[i];
                current++;
                i++;
            }else if(array[i] > array[j]){
                temp[current] = array[j];
                current++;
                j++;
            }else{//array[i]==array[j]
                temp[current] = array[i];
                temp[++current] = array[j];
                i++;
                j++;
                current++;
            }
        }
        //2. 将没有遍历完的子数组剩余元素全部加到临时数组中
//        if(i > mid && j <= right){
//
//        }else if(j > right && i <= mid){
//
//        }//这种采用if else + for循环方式没有下边的while循环好
        while(i <= mid){
            temp[current++] = array[i++];
        }
        while(j<=right){
            temp[current++] = array[j++];
        }
        //3. 将临时数组拷贝到原数组中
        current = 0;
        while(left <= right){
            array[left++] = temp[current++];
        }
    }

    //这个merge1写的不好，请参考更好的方法merge()
    public static void merge1(int[] array, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid;
        int current = 0;
        while (true) {

            if (array[i] < array[j]) {
                temp[current] = array[i];
                current++;
                i++;
                if(i == mid) {
                    //将右边剩余数组元素拷贝
                    for (int k = 0; k <right - j + 1; k++) {
                        temp[current++] = array[k+j];
                    }
                    break;
                }
            } else if (array[i] > array[j]) {
                temp[current] = array[j];
                current++;
                j++;
                if(j == right + 1) {
                    //将左边剩余数组元素拷贝
                    for (int k = 0; k <mid - i ; k++) {
                        temp[current++] = array[k+i];
                    }
                    break;
                }
            } else {
                temp[current] = array[i];
                temp[++current] = array[j];
                i++;
                j++;
                if(i < mid && j <= right){
                    //nothing
                }else if(i < mid && j == right + 1){
                    //将左边剩余数组元素拷贝
                    for (int k = 0; k <mid - i ; k++) {
                        temp[current++] = array[k+i];
                    }
                }else if(i == mid && j<= right){
                    //将右边剩余数组元素拷贝
                    for (int k = 0; k <right - j + 1; k++) {
                        temp[current++] = array[k+j];
                    }
                }else if( i == mid && j == right + 1){
                    break;
                }
            }
        }
    }
}
