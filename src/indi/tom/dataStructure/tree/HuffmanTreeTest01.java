package indi.tom.dataStructure.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author Tom
 * @Date 2020/12/16 11:00
 * @Version 1.0
 * @Description
 */

public class HuffmanTreeTest01 {

    @Test
    public void test01(){
        //give an array whose values of each element are the weight of the leaf node of
        //a tree to be constructed.
        int[] array = {1,3,5,9,6,18,12,28};
        //construct a node for each array element, and put them to an list
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i : array) {
            Node node = new Node(i);
            nodes.add(node);
        }
        //create huffman tree which is represented by the root node
        Node rootNode = createHuffmanTree(nodes);
        //iterate the huffman tree with pre-order
        rootNode.preOrder();

    }

    public Node createHuffmanTree(List<Node> nodes){
        Node newNode = null;
        while(nodes.size() > 1){
            //print the current nodes list
            System.out.println(nodes);
            //sort the list
            Collections.sort(nodes);
            //get the first two smallest node and construct a new node
            Node node0 = nodes.get(0);
            Node node1 = nodes.get(1);
            newNode = new Node(node0.getData() + node1.getData());
            //set the left and right sub node for the new node
            newNode.setLeft(node0);
            newNode.setRight(node1);
            //remove the first two node from list
            nodes.remove(node0);
            nodes.remove(node1);
            //add the new node
            nodes.add(newNode);
        }
        return newNode;
    }

    class Node implements Comparable{
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        //pre-order to traverse the tree
        public void preOrder(){
            //access current node
            System.out.println(this);
            //access left sub tree recursively
            if(this.getLeft() != null) this.getLeft().preOrder();
            //access right sub tree recursively
            if(this.getRight() != null) this.getRight().preOrder();
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof Node)) throw new IllegalArgumentException("argument must be a type of Node");
            Node node = (Node) o;
//            return this.data > node.getData() ? 1 : this.data == node.getData() ? 0 : -1;
            return this.data - node.getData();
        }
    }
}
