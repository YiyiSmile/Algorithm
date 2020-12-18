package indi.tom.dataStructure.tree;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Tom
 * @Date 2020/12/16 17:33
 * @Version 1.0
 * @Description Test using Huffman code to zip/unzip file.
 */
public class HuffmanCode {

    //save the Huffman code for each byte(char) in a map
    private HashMap<Byte, String> byteStringHashMap = new HashMap<>();
    //save the number of occurrence for each character in a map.
    private HashMap<Byte, Integer> byteIntegerHashMap = new HashMap<>();

    /**
     * convert a byte array from a string to another byte array coded with Huffman code.
     */
    @Test
    public void test01(){
        String testStr = "i like like like java do you like a java";

        byte[] bytes = testStr.getBytes();//40 bytes


        createHuffmanCodeMap(bytes);
        for (Map.Entry<Byte, Integer> byteIntegerEntry : byteIntegerHashMap.entrySet()) {
            char c = (char) byteIntegerEntry.getKey().byteValue();
            System.out.println(c + " -> " + byteIntegerEntry.getValue());
        }
        /*for (Map.Entry<Byte, String> byteStringEntry : byteStringHashMap.entrySet()) {
            char c = (char) byteStringEntry.getKey().byteValue();
            System.out.println(c + " -> " + byteStringEntry.getValue());
        }*/
        System.out.println(byteStringHashMap);
    }



    public Node createHuffmanTree(byte[] array){
        //1 count the times of occurrence for each byte element and put them into a map
        for (byte b : array) {
            if(byteIntegerHashMap.get(b) == null){
                byteIntegerHashMap.put(b, 1);
            }else{
                byteIntegerHashMap.put(b,byteIntegerHashMap.get(b)+1);
            }
        }
        //2 construct a huffman tree
        ArrayList<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> byteIntegerEntry : byteIntegerHashMap.entrySet()) {
            nodes.add(new Node(byteIntegerEntry.getValue(), byteIntegerEntry.getKey()));
        }
        int size = nodes.size();
        Node newNode = null;
        while(size > 1){
            Collections.sort(nodes);
            Node node0 = nodes.get(0);
            Node node1 = nodes.get(1);
            newNode = new Node(node0.getCount() + node1.getCount(), null);
            newNode.setLeft(node0);
            newNode.setRight(node1);
            nodes.add(newNode);
            nodes.remove(node0);
            nodes.remove(node1);
            size--;
        }
        return newNode;
    }

    public HashMap<Byte, String> createHuffmanCodeMap(byte[] array){
        Node node = createHuffmanTree(array);

        //2. Traverse the huffman tree leaf code,generate a code for each leaf node
        //and put it into a Map<Byte, String>

        String left = "0",right = "1";

        createHuffmanCode(node.getLeft(), "0");

        createHuffmanCode(node.getRight(), "1");

        return byteStringHashMap;

    }

    /**
     *
     * @param node Current node to be checked
     * @param huffmanCode the huffmanCode for the current node
     */
    private void createHuffmanCode(Node node, String huffmanCode){
        if(node == null) return;
        //if current node is not leaf node, then traverse it's sub node
        if(node.getB() == null){
            createHuffmanCode(node.getLeft(), huffmanCode + "0");
            createHuffmanCode(node.getRight(), huffmanCode + "1");
        }else{
        //if current node is leaf node, then generate code and
            byteStringHashMap.put(node.getB(), huffmanCode);
            //Not necessarily, save the code in the leaf node
            node.setHuffmanCode(huffmanCode);
        }
    }


    private class Node implements Comparable{
        //number of occurrence for a char(or byte) in the array
        private Integer count;
        private Byte b;

        private Node left;
        private Node right;

        private String huffmanCode;

        public Node(Integer count, Byte b) {
            this.count = count;
            this.b = b;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Byte getB() {
            return b;
        }

        public void setB(Byte b) {
            this.b = b;
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

        public String getHuffmanCode() {
            return huffmanCode;
        }

        public void setHuffmanCode(String huffmanCode) {
            this.huffmanCode = huffmanCode;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "count=" + count +
                    ", b(letter or char)=" + (char)b.byteValue() +
                    ", huffmanCode='" + huffmanCode + '\'' +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if(!(o instanceof Node)) throw new IllegalArgumentException("a Node type object has to be passed to this function.");
            Node o1 = (Node) o;
            return this.count - o1.getCount();
        }
    }

}
