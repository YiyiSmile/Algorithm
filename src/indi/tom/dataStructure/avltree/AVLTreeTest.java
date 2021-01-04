package indi.tom.dataStructure.avltree;

import org.junit.Test;

import indi.tom.dataStructure.avltree.AVLTreeTest.AVLTree;
import indi.tom.dataStructure.avltree.AVLTreeTest.ValueNotFoundException;



/**
 * @Author: Tom
 * @Date: 2021年1月3日 下午8:43:56
 * @Version: 1.0
 * @Description: A demo of AVL tree, built on top of BST tree. The main
 *               difference between BST and AVL tree is that the AVL tree
 *               addNode() method, The addNode() method guarantee that after
 *               adding the new node, the tree is still a self-balancing BST
 *               tree which is a tree that the height difference of the left and
 *               right subtree doesn't exceeds 1. It utilize the leftRotate()
 *               and rightRotate() method to make it.
 */
public class AVLTreeTest {
	public static void main(String[] args) throws ValueNotFoundException {
		AVLTree avlTree = new AVLTree();
		for (int i = 0; i < 10; i++) {
			avlTree.add(i);
		}

       avlTree.inOrder();
       
       avlTree.add(10);
       avlTree.delete(4);
       
       avlTree.inOrder();
       

	}

	@Test
	public void test01() throws ValueNotFoundException {
		AVLTree avlTree = new AVLTree();
		for (int i = 0; i < 10; i++) {
			avlTree.add(i);
		}
		System.out.println(avlTree);

	}

	

	static class AVLTree {
		Node root;
		
		public AVLTree() {}

		public AVLTree(Node root) {
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
		public void add(int value) {
			
		
			if (root == null) {
				root = new Node(value);
			}else {
				root.add(value);
			}		
			
		
		}

		/**
		 * 
		 * @Description: Delete the node with given value from the tree
		 *               ****Difficult*****
		 * @param value The Node value to be deleted
		 * @return no return value
		 * @throws ValueNotFoundException when the given value is not found in the tree.
		 */
		public void delete(int value) throws ValueNotFoundException {
			// 1. root is null or not found in the tree
			if (root == null)
				throw new ValueNotFoundException("This is a blank tree");
			Node searchResultNode = search(value);
			if (searchResultNode == null)
				throw new ValueNotFoundException("The given value is not found");
			Node parent = searchParent(root, value);

			// 2. root node is the one that has the value
			if (parent == null) {
				if (root.getLeft() == null && root.getRight() == null) {
					root = null;
				} else if (root.getLeft() != null && root.getRight() != null) {
					int minValue = getAndDeleteRightTreeMinValue(root.getRight());
					root.setValue(minValue);
					root.balance();

				} else if (root.getLeft() != null && root.getRight() == null) {
					this.root = root.getLeft();
				} else if (root.getLeft() == null && root.getRight() != null) {
					this.root = root.getRight();
				}
				// 3. the value exists being held by an non-root node
			} else {
				// 3.1 searchResultNode is a leaf node, remove it directly
				if (searchResultNode.getLeft() == null && searchResultNode.getRight() == null) {
					if (parent.getLeft() == searchResultNode) {
						parent.setLeft(null);
					} else if(parent.getRight() == searchResultNode){
						parent.setRight(null);
					}
					this.root.balance();
					// 3.2 both the left and right child of searchResultNode is not null
				} else if (searchResultNode.getLeft() != null && searchResultNode.getRight() != null) {
					int minValue = getAndDeleteRightTreeMinValue(searchResultNode.getRight());
					searchResultNode.setValue(minValue);
					this.root.balance();
					// 3.3 left child is not null
				} else if (searchResultNode.getLeft() != null && searchResultNode.getRight() == null) {
					parent.setLeft(searchResultNode.getLeft());
					this.root.balance();
					// 3.4 right child is not null
				} else {
					parent.setRight(searchResultNode.getRight());
					this.root.balance();
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

		@Override
		public String toString() {
			return this.root.toString();
		}
		
		
	}

	static class ValueNotFoundException extends Exception {

		private static final long serialVersionUID = 1L;

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
