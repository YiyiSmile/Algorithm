package indi.tom.DataStructure;

/**
 * @Author totian
 * @Date 2019/11/8 22:22
 * @Version 1.0
 * @Description： 模拟存储水浒英雄的双向链表
 */
public class DoubleLinkedList {
    public static void main(String[] args) {
        Node heroNode1 = new Node(1, "松江", "及时雨");
        Node heroNode2 = new Node(2, "吴用", "小诸葛");
        Node heroNode3 = new Node(3, "晁盖", "及时雨");
        Node heroNode4 = new Node(4, "林冲", "及时雨");

        DoubleLinkedList myList = new DoubleLinkedList();
        myList.add(heroNode1);
        myList.add(heroNode2);
        myList.add(heroNode3);
        myList.add(heroNode4);

        myList.list();
        System.out.println("--------------------------------------");
        //测试更新
//        Node heroNode5 = new Node(3, "张飞", "猛张飞");
//        myList.update(heroNode5);
        //测试删除
        myList.delete(4);
        myList.list();
    }

    private Node head = new Node(0, "", "");
    //返回头节点
    public Node getHead(){
        return this.head;
    }
    //遍历链表
    public void list(){
        if(head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Node temp = head.next;
        while(temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    //增加节点(无序)
    public void add(Node node){
        Node temp = head;
        while(temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
        node.prev = temp;
    }
    //修改节点,编号不能改，只改名字，外号
    public void update(Node node){
        int no = node.no;
        //true 找到了
        boolean isFound = false;
        Node temp = head.next;
        while(temp != null){
            if(temp.no == no){
                isFound = true;
                break;
            }
            temp = temp.next;
        }
        if(!isFound){
            System.out.println("要更新的英雄不存在");
        }else{
            temp.name = node.name;
            temp.nickName = node.nickName;
        }
    }
    //删除节点
    public void delete(int no){
        Node temp = head.next;
        while(temp != null){
            if(temp.no == no){
                break;
            }
            temp = temp.next;
        }
        if(temp == null){
            System.out.println("链表中没有No是" + no + "的英雄");
        }else{
            temp.prev.next = temp.next;
            if(temp.next != null){
                temp.next.prev = temp.prev;
            }
            System.out.println("节点删除成功");
        }
    }

    /**
     * 内部节点类
     */
    static class Node{
        private int no;
        private String name;
        private String nickName;
        private Node next;
        private Node prev;

        public Node(int no, String name, String nickName){
            this.no = no;
            this.name = name;
            this.nickName = nickName;
            this.prev = this.next = null;
        }

        @Override
        public String toString() {
            return "Node {" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    ", nickName='" + nickName + '\'' + "}";
        }
    }
}

