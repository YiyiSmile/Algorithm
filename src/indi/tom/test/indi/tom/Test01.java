package indi.tom.test.indi.tom;

/**
 * @Author totian
 * @Date 2019/11/8 22:25
 * @Version 1.0
 * @Description
 */
public class Test01 extends Test02{
    private String a;
    public Test01(){
        //显示调用父类有参构造
        super("test");
        System.out.println("Test01 constructor without arguments is called");
    }
    public Test01(String a){
        //隐式调用父类无参构造,此处如果写super()，效果也是一样的
        //super();
        System.out.println("Test01 constructor with arguments is called");
    }
    public static void main(String[] args) {
        new Test01();
        new Test01("aa");
    }
}

class Test02{
    private String name;
    public Test02(){
        System.out.println("Test02 constructor without arguments is called");
    }
    public Test02(String name){
        System.out.println("Test02 constructor with arguments is called");
    }
}
