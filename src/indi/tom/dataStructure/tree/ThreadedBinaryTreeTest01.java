package indi.tom.dataStructure.tree;

import org.junit.Test;

/**
 * @Author Tom
 * @Date 2020/12/9 15:56
 * @Version 1.0
 * @Description
 */
public class ThreadedBinaryTreeTest01 {
    private BinaryTree binaryTree = null;
    {
        //构建一个树
        HeroNode node1 = new HeroNode(1, "Iron man");
        HeroNode node2 = new HeroNode(2, "Spider man");
        HeroNode node3 = new HeroNode(3, " Hulk");
        HeroNode node4 = new HeroNode(4, "Thor");
        HeroNode node5 = new HeroNode(5, "Ant man");
        HeroNode node6 = new HeroNode(6, "Black widow");

        node1.setLeft(node2);
        node1.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setRight(node6);
        binaryTree = new BinaryTree(node1);
    }

    @Test
    public void test01(){


//tree structure
/*                     1
                 2        3
            4        5        6*/

        //1. 前序遍历： 1 2 4 5 3 6
        System.out.println("Pre-Oder(NLR):");
        binaryTree.preOrder();
        System.out.println("**********************");
        //2. 中序遍历：4 2 5 1 3 6
        System.out.println("In-Oder(LNR):");
        binaryTree.inOrder();
        System.out.println("**********************");
        //3. 后序遍历：4  5 2 6 3 1
        System.out.println("Post-Oder(LRN):");
        binaryTree.postOrder();
        System.out.println("**********************");
    }

    //4.测试删除
    @Test
    public void test02(){
        //tree structure
/*                     1
                 2        3
            4        5        6*/

        //1. 前序遍历： 1 2 4 5 3 6
        System.out.println("Pre-Oder(NLR):");
        binaryTree.preOrder();
        System.out.println("**********************");
        binaryTree.deleteNode(3);
        System.out.println("After delete, Pre-Oder(NLR):");
        binaryTree.preOrder();


    }

    //5. Test thread binary tree
    @Test
    public void test03(){
        binaryTree.threadBinaryTree();
        binaryTree.threadedBinaryTreeInOrder1();
        System.out.println("*******************");
        binaryTree.threadedBinaryTreeInOrder();
    }

    static class BinaryTree {
        HeroNode root;

        HeroNode pre;





        public BinaryTree(HeroNode root) {
            this.root = root;
        }

        //1. 前序遍历 中 左 右
        private void preOrder() {
            preOder(this.root);
        }

        public void preOder(HeroNode root) {
            if (root == null) return;
            System.out.println(root);
            preOder(root.getLeft());
            preOder(root.getRight());
        }

        //2. 中序遍历 左 中 右
        public void inOrder() {
            inOrder(root);
        }

        public void inOrder(HeroNode root) {
            if (root == null) return;
            if (root.getLeft() != null) {
                inOrder(root.getLeft());
            }
            System.out.println(root);
            if (root.getRight() != null) {
                inOrder(root.getRight());
            }
        }

        //3. 后续遍历 左 右 中
        public void postOrder() {
            postOrder(root);
        }

        public void postOrder(HeroNode root) {
            if (root == null) return;
            if (root.getLeft() != null) postOrder(root.getLeft());
            if (root.getRight() != null) postOrder(root.getRight());
            System.out.println(root);
        }

        //4. 删除Node (根据给定No删除Node)
        public int deleteNode(int nodeId) {
            //这里采用前序搜索，返回被删除节点的父节点
            HeroNode node = preSearch(nodeId);
            if (node == null) {
                System.out.println("The hero to be deleted doesn't exist!!!");
                return 1;
            } else {
                if (node.getLeft() != null && node.getLeft().getNo() == nodeId) node.setLeft(null);
                if (node.getRight() != null && node.getRight().getNo() == nodeId) node.setRight(null);
                System.out.println("The Here with ID " + nodeId + " get deleted!!!");
                return 0;
            }


        }

        //5.前序搜索,一定要返回被删除节点的父节点
        public HeroNode preSearch(int nodeId) {
            return preSearch(nodeId, root, root);
        }

        //tree structure
/*                     1
                 2        3
            4        5        6*/
        public HeroNode preSearch(int nodeId, HeroNode node, HeroNode parent) {
            if(node == null) return null;
            if (node.getNo() == nodeId) return parent;

                if (preSearch(nodeId, node.getLeft(), node) != null) return preSearch(nodeId, node.getLeft(), node);


                if (preSearch(nodeId, node.getRight(), node) != null) return preSearch(nodeId, node.getRight(), node);



            return null;


        }

        //6. Thread binary tree with inOrder
        public void threadBinaryTree(){
            threadBinaryTree(root);
        }

        private void threadBinaryTree(HeroNode node){
            if(node == null) return;

            //1. thread left sub tree
            threadBinaryTree(node.getLeft());

            //2. thread current node
            if(node.getLeft() == null){
                node.setLeft(pre);
                node.setLeftType(1);


            }
            if(pre != null && pre.getRight() == null){
                pre.setRightType(1);
                pre.setRight(node);
            }
            pre = node;

            //3. thread right sub tree
            threadBinaryTree(node.getRight());
        }

        // 7 Traverse a threaded binary tree with in order
        public void threadedBinaryTreeInOrder1(){
            threadedBinaryTreeInOrder1(root);

        }
        private void threadedBinaryTreeInOrder1(HeroNode node){
            if(node == null) return;
            //1. iterate left sub tree
            if(node.getLeftType() == 0){
                threadedBinaryTreeInOrder1(node.getLeft());
            }
            //2. iterate current node
            System.out.println(node);
            //3. iterate right sub tree
            if(node.getRightType() == 0){
                threadedBinaryTreeInOrder1(node.getRight());
            }
        }

        // 7 Traverse a threaded binary tree with in order
        private void threadedBinaryTreeInOrder(){
            HeroNode node = root;
            while(node != null){
                while(node.getLeftType() == 0 ){
                    node = node.getLeft();
                }
                System.out.println(node);
                while(node.getRightType() == 1 && node.getRight() != null){
                    node = node.getRight();
                    System.out.println(node);
                }

                node = node.getRight();
            }
        }


    }

    static class HeroNode {


        private Integer no;
        private String name;
        private HeroNode left;
        private HeroNode right;

        //leftType = 0 -> point to left node
        //leftType = 1 -> point to inoder predecessor,
        private int leftType;
        //rightType = 0 -> point to right node
        //rightType = 1 -> point to inoder successor,
        private int rightType;


        public HeroNode(Integer no, String name) {
            this.no = no;
            this.name = name;
        }

        public Integer getNo() {
            return no;
        }

        public void setNo(Integer no) {
            this.no = no;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public HeroNode getLeft() {
            return left;
        }

        public void setLeft(HeroNode left) {
            this.left = left;
        }

        public HeroNode getRight() {
            return right;
        }

        public void setRight(HeroNode right) {
            this.right = right;
        }

        public int getLeftType() {
            return leftType;
        }

        public void setLeftType(int leftType) {
            this.leftType = leftType;
        }

        public int getRightType() {
            return rightType;
        }

        public void setRightType(int rightType) {
            this.rightType = rightType;
        }

        @Override
        public String toString() {
            return "HeroNode{" +
                    "no=" + no +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
