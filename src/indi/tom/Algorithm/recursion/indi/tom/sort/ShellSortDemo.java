package indi.tom.Algorithm.recursion.indi.tom.sort;

import java.util.Arrays;

/**
 * @Author Tom
 * @Date 2020/8/8 8:10
 * @Version 1.0
 * @Description
 */
public class ShellSortDemo {
    public static void main(String[] args) {
        int[] elements = {21,11,5,1,5,45,34,23,7,8,66,98,54,34,33,79};
        System.out.println(Arrays.toString(elements));
        shellSort(elements);
        System.out.println(Arrays.toString(elements));
    }
    public static void shellSort(int[] elements){
        //根据步长循环
        int length = elements.length;
        for (int gap = length/2; gap > 0 ; gap /=2) {
            //从第gap个元素开始，执行插入排序
            for(int j=gap;j<length;j++){
                int insertValue = elements[j];
                int index = j - gap;
                while(index >= 0 && elements[index] > insertValue){
                    elements[index + gap] = elements[index];
                    index -= gap;
                }
                elements[index + gap] = insertValue;
            }
        }
    }
}
