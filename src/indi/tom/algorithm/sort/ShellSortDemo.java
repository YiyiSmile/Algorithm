package indi.tom.algorithm.sort;

/**
 * @Author Tom
 * @Date 2020/8/8 8:10
 * @Version 1.0
 * @Description
 */
public class ShellSortDemo {
    public static void main(String[] args) {
/*        int[] elements = {21,11,5,1,5,45,34,23,7,8,66,98,54,34,33,79};
        System.out.println(Arrays.toString(elements));
        shellSort(elements);
        System.out.println(Arrays.toString(elements));*/

        int[] arr = new int[800000];
        for (int i = 0; i < 800000; i++) {
            arr[i] = 800000 - i;
        }

/*        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = dateFormat.format(now);
        System.out.println("Before sort time: " + format);*/

        long start = System.currentTimeMillis();

//        shellSort(arr);
//        insertSort(arr);

        long end = System.currentTimeMillis();
        System.out.println((end - start) + " milliseconds is taken.");

/*        now = new Date();
        format = dateFormat.format(now);
        System.out.println("After sort time: " + format);*/

    }
    public static void shellSort(int[] elements){
        //根据步长循环
        int length = elements.length;
        for (int gap = length/2; gap > 0 ; gap /=2) {
            //从第gap个元素开始，执行插入排序
            for(int j=gap;j<length;j++){
                int insertValue = elements[j];
//                System.out.println(insertValue);
                int index = j - gap;
                while(index >= 0 && elements[index] > insertValue){
                    elements[index + gap] = elements[index];
                    index -= gap;
                }
                elements[index + gap] = insertValue;
            }
        }
    }

//    public static void insertSort(int[] elements){
//        if(elements.length <= 0 || elements == null)
//            throw new IllegalArgumentException("The argument is illegal!");
//        for(int i=1;i<elements.length;i++){
//            int insertValue = elements[i];
//            int insertIndex = i-1;
////            System.out.println(insertValue);
//            while(insertIndex>=0 && elements[insertIndex]>insertValue){
//                elements[insertIndex+1] = elements[insertIndex];
//                insertIndex --;
//            }
//            elements[insertIndex+1] = insertValue;
//        }
//
//    }
}
