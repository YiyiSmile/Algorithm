package indi.tom.algorithm.sort;

/**
 * @Author Tom
 * @Date 2020/8/9 11:45
 * @Version 1.0
 * @Description
 */
public class MergeSortDemo {
    public static void main(String[] args) {
        int[] elements = {21, 11, 38, 1, 5, 45, 34, 23, 7, 8, 66, 98, 54, 27, 33, 79};

    }

    public static void mergeSort1(int[] elements,int left, int right) {
        int l = 0;
        int r = elements.length;
        int mid = (l + r) / 2;
        //divide
        mergeSort1(elements,left,mid);
        mergeSort1(elements,mid + 1,right);
        //conquer
//        merge(elements, left, right);

    }

    public static void merge(int[] elements,int left, int right,int[] temp){
        int mid = (left + right)/2;
        int l = left;
        int r = mid + 1;
        int pointerOfTemp = 0;

        while(elements[l] < elements[r]){
            temp[pointerOfTemp] = elements[l];
            l++;
            pointerOfTemp++;
        }

        while(elements[l] > elements[r]){
            temp[pointerOfTemp] = elements[r];
            r++;
            pointerOfTemp++;
        }
    }

}
