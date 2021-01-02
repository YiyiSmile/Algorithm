package indi.tom.dataStructure.tree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @Author: Tom
 * @Date: 2021年1月2日 下午6:44:37   
 * @Version: 1.0
 * @Description:
 */
public class TreeHeightTest {
	
	@Test
	public void test01() {
		//constuct a tree
		Node node0 = new Node(0);
		Node node1 = new Node(1);
		Node node2 = new Node(2);
		Node node3 = new Node(3);
		Node node4 = new Node(4);
		Node node5 = new Node(5);
		Node node6 = new Node(6);
		Node node7 = new Node(7);
		Node node8 = new Node(8);
		Node node9 = new Node(9);
		ArrayList<Node> list1 = new ArrayList<Node>();
		list1.add(node1);
		list1.add(node2);
		node0.setChilds(list1);
		ArrayList<Node> list2 = new ArrayList<Node>();
		list2.add(node3);
		list2.add(node4);
		list2.add(node5);
		node1.setChilds(list2);
		
		ArrayList<Node> list3 = new ArrayList<Node>();
		list3.add(node6);
		node2.setChilds(list3);
		
		ArrayList<Node> list4 = new ArrayList<Node>();
		list4.add(node7);
		node4.setChilds(list4);
		ArrayList<Node> list5 = new ArrayList<Node>();
		list5.add(node8);
		node6.setChilds(list5);
		ArrayList<Node> list6 = new ArrayList<Node>();
		list6.add(node9);
		node7.setChilds(list6);
		//calculate the height
		System.out.println("The depth of the tree is(Tree with one node,its depth is 1):" + calculate(node0));
		
	}
	//calculate the height of tree represented by node sent to this function.
	private int calculate(Node node) {
		int max = 1;
		if(node.getChilds() == null || node.getChilds().size() == 0) {
			return 1;
		}else {
			//traverse all child nodes
			List<Node> childs = node.getChilds();
			for (Node childNode : childs) {
				int tempHeight = calculate(childNode);
				max = compare(max,tempHeight);
			}
			return max + 1;
		}
		
	}
	
	private int compare(int a, int b) {
		return a>b?a:b;
	}

}

class Node{
	
	private int value;
	
	List<Node> childs;
	
	public Node(int value) {
		this.value = value;
	}

	public List<Node> getChilds() {
		return childs;
	}

	public void setChilds(List<Node> childs) {
		this.childs = childs;
	}
	
}
