package indi.tom.algorithm.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2020/8/8 10:30
 * @Version 1.0
 * @Description
 */
public class BubbleSortDemo {
    public static void main(String[] args) {
//        int[] elements = {21,11,5,1,5,45,34,23,7,8,66,98,54,34,33,79};
//        System.out.println(Arrays.toString(elements));
//        bubbleSort(elements);
//        System.out.println(Arrays.toString(elements));

        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random()*80000);
        }

        System.out.println(Arrays.toString(arr));

        long start = System.currentTimeMillis();

        bubbleSort(arr);

        long end = System.currentTimeMillis();

        System.out.println((end - start) + " milliseconds is taken");
    }

    public static void bubbleSort(int[] elements){
        int tmp;
        boolean isSorted = true;
        for (int j = 0; j < elements.length; j++) {

            for (int i = 0; i < elements.length-1-j; i++) {
                if(elements[i] > elements [i+1]){
                    tmp = elements[i];
                    elements[i] = elements[i+1];
                    elements[i+1] = tmp;

                    isSorted = false;
                }
            }

//            if(isSorted)
//                break;
//            else
//                isSorted = true;
        }
    }
}
