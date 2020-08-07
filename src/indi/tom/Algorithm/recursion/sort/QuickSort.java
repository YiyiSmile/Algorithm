package indi.tom.Algorithm.recursion.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author Tom
 * @Date 2019/11/11 9:44
 * @Version 1.0
 * @Description The Quick Sort
 */
public class QuickSort {
    //This is an util class, so should not be instantiated
    private QuickSort(){}
    public static void main(String[] args) {
        String[] array = {"i","q","w","e","r","t","y","u","i","o","p","a","s",
                "d","f","g","h","j","k","l","z","x","c","v","b","n","m"};
        quickSort(array, 0, array.length - 1);
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%s  ",array[i]);
        }

        //测试快速排序的速度
        String[] array1 = new String[80000];
        for (int i = 0; i < 80000; i++) {
            array1[i] = (int) (Math.random()*80000) + "";
        }

        System.out.println("排序前时间：");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(date));

        quickSort(array1, 0, 7999);
//        for (int i = 0; i < array1.length; i++) {
//            System.out.printf("%s  ",array1[i]);
//            if(i%10000 == 0) System.out.println();
//        }
        System.out.println();
        Date date1 = new Date();
        System.out.println("排序后时间：");
        System.out.println(sdf.format(date1));
    }
    public static void quickSort(Comparable[] array, int low, int high){
        if (high <= low) return;
        int j = partition(array, low, high);
        quickSort(array,low, j-1);
        quickSort(array,j+1,high);
    }

    public static int partition(Comparable[] array, int low, int high){
        //need to validate the input?
        int i = low;
        int j = high + 1;
        Comparable temp = array[low];

        while(true){
            //iterate the array from left, if the element is greater than temp, then exit the loop
            while(array[++i].compareTo(temp) < 0){
                if(i == high) break;
            }
            //no consideration when equal?
            while(array[--j].compareTo(temp) > 0){
                if(j == low) break;
            }
            if(i >= j) break;

            exchange(array, i,j);
        }
        exchange(array,low, j);
        return j;
    }

    public static void exchange(Comparable[] array, int i, int j){
        Comparable temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
