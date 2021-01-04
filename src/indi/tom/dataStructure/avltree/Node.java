package indi.tom.dataStructure.avltree;

/**
 * @Author: Tom
 * @Date: 2021年1月4日 下午9:20:45
 * @Version: 1.0
 * @Description:
 */
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
		// 1. add new node to the right
		if (this.value <= value) {
			// 1.1 if right == null, add to right directly
			if (this.right == null) {
				this.right = target;
				// 1.2 if right != null, call right.add() recursively
			} else {
				this.right.add(value);
				// 1.2.1 compare the left and right subtree, if the difference is greater than
				// 1, adjust it

				// a. left rotate the entire tree (this is possible as right subtree add one)
				if (right.height() - (left == null ? 0 : left.height()) > 1) {
					// before left rotate the entire tree, check if the right.left > right.right, if
					// so, right rotate the right subtree
					if ((right.left == null ? 0 : right.left.height()) > (right.right == null ? 0
							: right.right.height())) {
						right.rightRotate();
						// then check if the entire tree, right.height - left.heitht > 1, if so, left
						// rotate the entire tree
						if ((right.height() - (left == null ? 0 : left.height())) > 1) {
							this.leftRotate();
						}
					} else if ((right.right == null ? 0 : right.right.height()) > (right.left == null ? 0
							: right.left.height())) {
						this.leftRotate();
						// The last case is right.right.height()=right.left.height(), but it's not
						// possible because before add to the right,
						// it must be that right.height > left.height, then it's possible right.height()
						// - left.height() > 1.
						// So before add, it must be right.height - left.right = 1, then add 1 node to
						// right, if right.left.height = right.right.height,
						// then the right subtree height must has no change
					} /*
						 * else if(right.right.height() == right.left.height()) {this is not possible
						 * under the condition that (right.height() - left.height()) > 1}
						 */
				} /* else if(left.height() - right.height() > 1) {this is no possible} */

			}

			// 2. add new node to the left
		} else {
			// 2.1 if left == null, add to left directly
			if (this.left == null) {
				this.left = target;
				// 2.2 if left != null, call left.add() recursively
			} else {
				this.left.add(value);
				if ((left.height() - (right == null ? 0 : right.height())) > 1) {
					// if(left.right.height()>left.left.height()), left rotate the left subtree,
					// then check if left.height - right.height still > 1, if so,
					// right rotate the entire tree
					if ((left.right == null ? 0 : left.right.height()) > (left.left == null ? 0 : left.left.height())) {
						left.leftRotate();
						if ((left.height() - right.height()) > 1) {
							this.rightRotate();
						}
					} else if ((left.right == null ? 0 : left.right.height()) < (left.left == null ? 0
							: left.left.height())) {
						this.rightRotate();
					} /*
						 * else if(eft.right.height()<left.left.height()) {this is not possible under
						 * the condition that (left.height() - right.height()) > 1}
						 */

				} /* else if((right.height()-left.height()) > 1) {this is not gonna happen} */

			}
		}
		return target;

	}

	// adjust the entire tree to make sure it's a balancing search tree
	public void balance() {
		if (this.left == null && this.right == null) {
			return;
		} else if (this.left == null && this.right != null) {
			if (right.height() > 1) {
				// left rotate
				this.leftRotate();
			}

		} else if (this.left != null && this.right == null) {
			if (left.height() > 1) {
				this.rightRotate();
			}

		} else if (this.left != null && this.right != null) {
			if ((left.height() - right.height()) > 1) {
				// if(left.right.height()>left.left.height()), left rotate the left subtree,
				// then check if left.height - right.height still > 1, if so,
				// right rotate the entire tree
				if (left.right.height() > left.left.height()) {
					left.leftRotate();
					if ((left.height() - right.height()) > 1) {
						this.rightRotate();
					}
				} else if (left.right.height() < left.left.height()) {
					this.rightRotate();
				}

			} else if ((right.height() - left.height()) > 1) {
				// before left rotate the entire tree, check if the right.left > right.right, if
				// so, right rotate the right subtree
				if (right.left.height() > right.right.height()) {
					right.rightRotate();
					// then check if the entire tree, right.height - left.heitht > 1, if so, left
					// rotate the entire tree
					if ((right.height() - left.height()) > 1) {
						this.leftRotate();
					}
				} else if (right.right.height() >= right.left.height()) {
					this.leftRotate();

				}

			}
		}

	}

	// right rotate
	public void rightRotate() {
		
		// original = root = this
		// create a new Node for the original root node
		Node newNode = new Node(value);
		// new node.right = root.right
		newNode.right = this.right;
		// new node.eft = root.left.right
		newNode.left = this.left.right;
		// root.value = left.value;
		this.value = left.value;
		// root.right = new node
		this.right = newNode;
		// root.left = left.left
		this.left = left.left;
	}

	// left rotate
	public void leftRotate() {
		
		// root = this
		// clone the current root node
		Node newNode = new Node(this.value);
		// newNode.left = root.left
		newNode.left = this.left;
		// newNode.right = root.right.left
		newNode.right = this.right.left;
		// root.value = right.value
		this.value = right.value;
		// root.right = right.right
		this.right = right.right;
		// root.left = newNode
		this.left = newNode;

	}

	// calculate height
	public int height() {
		if (left != null && right == null) {
			
			return left.height() + 1;
		} else if (left == null && right != null) {
			return right.height() + 1;
		} else if (left != null && right != null) {
			return Math.max(left.height(), right.height()) + 1;
		} else {
			return 1;
		}

	}

	@Override
	public String toString() {
		return "Node [value=" + value + ", left=" + left + ", right=" + right + "]";
	}

	

}