package indi.tom.dataStructure.tree;

import org.junit.Test;



/**
 * @Author: Tom
 * @Date: 2021年1月3日 下午8:43:56
 * @Version: 1.0
 * @Description: A demo of BST, including search, add, delete function.
 */
public class BinarySortTreeTest {

	@Test
	public void test01() throws ValueNotFoundException {
		Node node12 = new Node(12);

		Node node8 = new Node(8);
		Node node18 = new Node(18);
		node12.setLeft(node8);
		node12.setRight(node18);
		
		Node node5 = new Node(5);
		Node node10 = new Node(10);
		node8.setLeft(node5);
		node8.setRight(node10);
		
		Node node15 = new Node(15);
		Node node22 = new Node(22);
		node18.setLeft(node15);
		node18.setRight(node22);
		
		Node node4 = new Node(4);
		Node node6 = new Node(6);
		node5.setLeft(node4);
		node5.setRight(node6);
		
		Node node9 = new Node(9);
		Node node11 = new Node(11);
		node10.setLeft(node9);
		node10.setRight(node11);
		Node node13 = new Node(13);
		Node node17 = new Node(17);
		node15.setLeft(node13);
		node15.setRight(node17);
		BinarySortTree binarySortTree = new BinarySortTree(node12);

//		binarySortTree.add(7);
		System.out.println("*******");
//		binarySortTree.inOrder();
//		binarySortTree.delete(8);
//		binarySortTree.inOrder();
		binarySortTree.delete(9);
		binarySortTree.delete(10);
		binarySortTree.inOrder();

	}
	
	@Test
	public void test02() {
//		
//		for (int i = 4; i < 20; i++) {
//			binarySortTree.add(i);
//		}
//		binarySortTree.inOrder();
	}

	class Node {

		private int value;
		private Node left, right;

		public Node(int value) {
			super();
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
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

		// InOrder traverse
		public void inOrder() {
			if (this.left != null) {
				this.left.inOrder();
			}
			System.out.println(value);
			if (this.right != null) {
				this.right.inOrder();
			}
		}

		// search
		public Node search(int value) {
			Node target = null;
			if (this.value == value)
				return this;
			if (this.left != null) {
				target = this.left.search(value);
				if (target != null)
					return target;
			}
			if (this.right != null) {
				target = this.right.search(value);
				if (target != null)
					return target;
			}
			return null;
		}

		// add

		public Node add(int value) {
			Node target = new Node(value);
			if (this.value <= value) {
				if (this.right == null) {
					return this.right = target;
				} else {
					return this.right.add(value);
				}
			} else {
				if (this.left == null) {
					return this.left = target;
				} else {
					return this.left.add(value);
				}
			}
		}

		@Override
		public String toString() {
			return "Node [value=" + value + "]";
		}
		
		

	}

	class BinarySortTree {
		Node root;

		public BinarySortTree(Node root) {
			super();
			this.root = root;
		}

		// In order traverse
		public void inOrder() {
			root.inOrder();
		}

		// BST search
		public Node search(int value) {
			if (root == null)
				return null;
			return root.search(value);
		}

		// BST add
		public Node add(int value) {
			if (root == null)
				return root = new Node(value);
			return root.add(value);
		}

	
		/**
		 * 
		* @Description: Delete the node with given value from the tree ****Difficult*****
		* @param  value The Node value to be deleted
		* @return  no return value
		* @throws ValueNotFoundException when the given value is not found in the tree.
		 */
		public void delete(int value) throws ValueNotFoundException {
			//1. root is null or not found in the tree
			if (root == null)
				throw new ValueNotFoundException("This is a blank tree");
			Node searchResultNode = search(value);
			if (searchResultNode == null)
				throw new ValueNotFoundException("The given value is not found");
			Node parent = searchParent(root, value);
			System.out.println("searchResultNode: " + searchResultNode);
			System.out.println("parentNode:" + parent);
			// 2. root node is the one that has the value
			if (parent == null) {
				if (root.getLeft() == null && root.getRight() == null) {
					root = null;
				} else if (root.getLeft() != null && root.getRight() != null) {
					int minValue = getAndDeleteRightTreeMinValue(root.getRight());
					root.setValue(minValue);
				} else if (root.getLeft() != null && root.getRight() == null) {
					this.root = root.getLeft();
				} else if (root.getLeft() == null && root.getRight() != null) {
					this.root = root.getRight();
				}
			//3. the value exists being held by an non-root node
			} else {
				//3.1 searchResultNode is a leaf node, remove it directly
				if (searchResultNode.getLeft() == null && searchResultNode.getRight() == null) {
					if (parent.getLeft() == searchResultNode) {
						parent.setLeft(null);
					} else {
						parent.setRight(null);
					}
				//3.2 both the left and right child of searchResultNode is not null
				} else if (searchResultNode.getLeft() != null && searchResultNode.getRight() != null) {
					int minValue = getAndDeleteRightTreeMinValue(searchResultNode.getRight());
					searchResultNode.setValue(minValue);
				//3.3 left child is not null
				} else if (searchResultNode.getLeft() != null && searchResultNode.getRight() == null) {
					parent.setLeft(searchResultNode.getLeft());
				//3.4 right child is not null
				} else {
					parent.setRight(searchResultNode.getRight());
				}
			}


		}

		// get the min value in the right subtree and remove the corresponding node
		private int getAndDeleteRightTreeMinValue(Node node) throws ValueNotFoundException {

			while (node.getLeft() != null) {
				node = node.getLeft();
			}

			int minimal = node.getValue();
			// delete the minimal node
			delete(minimal);
			return minimal;

		}

		private Node searchParent(Node node, int value) {

			if (node.getValue() == value)
				return null;
			Node target = null;
			if (node.getLeft() != null) {
				if (node.getLeft().getValue() == value) {
					return node;
				} else {
					target = searchParent(node.getLeft(), value);
					if (target != null)
						return target;
				}

			}
			if (node.getRight() != null) {
				if (node.getRight().getValue() == value) {
					return node;
				} else {
					target = searchParent(node.getRight(), value);
					if (target != null)
						return target;
				}

			}

			return null;
		}
	}
	
	class ValueNotFoundException extends Exception{
		
		public ValueNotFoundException() {
			super();
		}
		
		public ValueNotFoundException(String message) {
			super(message);
		}
		
		public ValueNotFoundException(String message, Throwable cause) {
			super(message, cause);
		}
	}

}
