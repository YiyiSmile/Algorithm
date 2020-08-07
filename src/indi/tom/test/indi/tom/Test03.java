package indi.tom.test.indi.tom;

/**
 * @Author Tom
 * @Date 2019/11/9 21:52
 * @Version 1.0
 * @Description
 */
public class Test03 {
    private int a,b,c;
    boolean d;
    public static void exchange(Comparable a, Comparable b){
        Comparable temp = a;
        a = b;
        b = temp;
    }
    public static void main(String[] args) {
        int a = 10;
        int b = 2;
        int[] arr = {1,2,3};
        for (int i : arr) {
            System.out.println(i);
        }

//        System.out.println((int) Math.pow(10, 2));
    }
}
