package indi.tom.dataStructure;

/**
 * @Author totian
 * @Date 2019/11/9 14:44
 * @Version 1.0
 * @Description 约瑟夫问题，在有n个人的圆圈中，从第k(1<k<n)个人开始,数m个数，到第m个人，第m个人出列，
 * 他的下一个人又从m报数，直到所有人出列。问，出来的人的序号是多少？
 */
public class JosephuCircleLinkedList {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList(100);
//测试环形列表写的没有问题，可以正确初始化列表
//        circleLinkedList.list();
//        System.out.println(circleLinkedList.size());
//        System.out.println(circleLinkedList.getFirst().next);

        int[] result = josephu(circleLinkedList, 10, 10);
        for (int i = 0; i < result.length; i++) {
            System.out.printf("%d\t",result[i]);
            if(i % 9 == 0) System.out.println();
        }
    }
    //根据给定的环形列表和第k个元素，按照Josephu原则，返回一个int数组，里边包含了按顺序出列的元素
    //m是每次数的数
    public static int[] josephu(CircleLinkedList circleLinkedList, int k, int m){
        int size = circleLinkedList.size();
        if(!(k>1 && k<size)) throw new IllegalArgumentException("k值必须大于1，小于给定列表长度！");
        int [] result = new int[size - 1 ];
        //找到第k个元素
        CircleLinkedList.Node current = circleLinkedList.getFirst();
        int count = 1;
        while(count<k){
            current = current.next;
            count++;
        }
        //从k个元素开始遍历，每遍历一个元素，计数加1，直到计数等于m-1，删除当前元素的下一个元素（之所以用m-1,
        // 是因为传递个链表的remove方法的参数为要删除的元素的前一个元素比较好，直接传要删除的元素，不便于删除），
        // 从下一个元素开始重新循环同样过程
        int index = 0;
        while(true) {
            count = 1;
            while (count < m-1) {
                current = current.next;
                count++;
            }
            CircleLinkedList.Node temp = current;
            current = current.next.next;

            result[index++] = temp.next.id;
            circleLinkedList.remove(temp);
            if(circleLinkedList.size()==1) break;
        }
        return result;
    }
    /**
     * 环形列表，构造函数传入一个列表的长度，构造函数会自动构造出一个有指定个数节点的环形列表。
     */
    static class CircleLinkedList {
        Node first;
        private int size;
        //创建一个含有size个元素的链表，第一个节点id=1，最后一个节点的id=size.
        public CircleLinkedList(int size) {
            if (size <= 1) throw new IllegalArgumentException("队列的大小必须大于1");
            Node current;
            Node previous;
            current = previous = first = new Node(1);
            int i = 2;
            while (i <= size) {
                current = new Node(i);
                previous.next = current;
                previous = current;
                i++;
            }
            current.next = first;
            this.size = i - 1;
        }
        //获取链表长度
        public int size() {
            return size;
        }
        //遍历列表
        public void list() {
            Node current = first;
            while (current != null) {
                System.out.printf("%d\t", current.id);
                if (current.id % 10 == 0) System.out.println();
                if (current.id < 100) {
                    current = current.next;
                }else {
                    break;
                }
            }
        }
        public Node getFirst() {
            return first;
        }
        //根据node 的id属性值删除元素
        public void remove(int id){}
        //根据给定的node引用删除，指定的node的下一个节点将被删除
        public void remove(Node node){
            node.next = node.next.next;
            size--;
        }
        class Node {
            private int id;
            Node next;

            public Node(int id) {
                this.id = id;
            }
            @Override
            public String toString() {
                return "Node{" +
                        "id=" + id +
                        '}';
            }
        }
    }
}
