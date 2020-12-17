package indi.tom.test.indi.tom;

import org.junit.Test;

/**
 * @Author Tom
 * @Date 2020/12/16 13:56
 * @Version 1.0
 * @Description
 */
public class TestJDK {
    @Test
    public void test01(){
//        String s = Integer.toBinaryString(7);
//        System.out.println(s);
//        byte b = -127;
//        int a = -127;
//        a |= 256;
/*        String s = "10000001";
        int i = Integer.parseInt(s, 2);
        byte b1 = (byte) i;
        int j = b1;
        System.out.println(Integer.toBinaryString(j));
        j |= 256;*/
        byte b = 2; //11111110
        int i = b;
        i |= 256;
        System.out.println(Integer.toBinaryString(i));

//        System.out.println(i1);
//        System.out.println(i);
//        System.out.println();
//        Byte i =  (byte) Integer.parseInt(s, 2);
//        int j = i;
//        System.out.println();
//        System.out.println(Integer.toBinaryString(a));
//        System.out.println();
    }

}
