package indi.tom.dataStructure.tree;

import org.junit.Test;

/**
 * @Author Tom
 * @Date 2020/12/15 16:40
 * @Version 1.0
 * @Description
 */
public class ArrayBinaryTreeTest01 {

//    @Test
//    public void test01(){
//
//        }
    @Test
    public void test01(){
 /*       1
     2     3
   4    5 6    7
 8   9 10   */
        //pre order: 1 2 4 8 9 5 10 3 6 7
        int[] array = {1,2,3,4,5,6,7,8,9,10};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder();
    }
}

class ArrayBinaryTree{
    private int[] data;

    public ArrayBinaryTree(int[] data) {
        this.data = data;
    }

    //pre-oder
    public void preOrder(){
        preOrder(0);
    }

    public void preOrder(int index){
        if(index >= data.length) return;
        //1. print current node
        System.out.println(data[index]);
        //2. iterate the left sub tree

        preOrder(2*index + 1);
        //3. iterate the right sub tree
        preOrder(2*index + 2);
    }

}
