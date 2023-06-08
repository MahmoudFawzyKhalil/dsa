package dsa.bst;

import java.util.ArrayDeque;
import java.util.Queue;

public class BST {

    Node root;

    public BST(int val) {
        this.root = new Node(val);
    }

    public static void main(String[] args) {
        BST bst = new BST(10);
        bst.insert(15);
        bst.insert(5);
        bst.insert(2);
        bst.insert(100);
        bst.insert(-1);
        bst.print();

        System.out.println(bst.lowerBound(150));
    }

    private void bfs() {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        System.out.println(root.val);
        Node node;
        while ((node = queue.poll()) != null) {
            if (node.left != null) {
                queue.offer(node.left);
                System.out.println(node.left.val);
            }

            if (node.right != null) {
                queue.offer(node.right);
                System.out.println(node.right.val);
            }
        }
    }

    public void print() {
        int initialPadding = maxDepth(root);
        print(root, initialPadding, initialPadding, new ArrayDeque<>());
    }

    public Node search(int val) {
        return search(root, val);
    }

    private Node search(Node node, int val) {
        if (node == null) return null;
        if (val < node.val) {
            return search(node.left, val);
        } else if (val > node.val) {
            return search(node.right, val);
        } else {
            return node;
        }
    }

    /**
     * If v exists in the BST, then lower_bound(v) is the same as Search(v). But, if v does not exist in the BST, lower_bound(v) will find the smallest value in the BST that is strictly larger than v (unless v > the largest element in the BST). This is the location of this currently non-existent v if it is later inserted into this BST.
     */
    public Node lowerBound(int val) {
        return lowerBound(root, val, null);
    }

    private Node lowerBound(Node node, int val, Node lowerBound) {
        if (node == null) return lowerBound;

        if (lowerBound == null) {
            lowerBound = node.val >= val ? node : null;
        } else {
            lowerBound = (node.val >= val) && (node.val < lowerBound.val) ? node : lowerBound;
        }

        if (val < node.val) {
            return lowerBound(node.left, val, lowerBound);
        } else if (val > node.val) {
            return lowerBound(node.right, val, lowerBound);
        } else {
            return lowerBound;
        }
    }

    /**
     * If v has a right subtree, the minimum integer in the right subtree of v must be the successor of v. Try Successor(23) (should be 50).
     * <p>
     * If v does not have a right subtree, we need to traverse the ancestor(s) of v until we find 'a right turn' to vertex w (or alternatively, until we find the first vertex w that is greater than vertex v). Once we find vertex w, we will see that vertex v is the maximum element in the left subtree of w. Try Successor(7) (should be 15).
     * <p>
     * If v is the maximum integer in the BST, v does not have a successor. Try Successor(71) (should be none).
     */
    public Node successor(int val) {
        return null;
    }

    public void insert(int val) {
        insert(root, val);
    }

    private void insert(Node parent, int val) {
        Node newNode = new Node(val);
        if (val < parent.val) {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
            } else {
                insert(parent.left, val);
            }
        } else if (val > parent.val) {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
            } else {
                insert(parent.right, val);
            }
        }
    }

    public void inorder() {
        inorder(root);
    }

    private void inorder(Node node) {
        if (node == null) return;
        inorder(node.left);
        System.out.println(node.val);
        inorder(node.right);
    }

    public void preorder() {
        preorder(root);
    }

    private void preorder(Node node) {
        if (node == null) return;
        System.out.println(node.val);
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder() {
        postorder(root);
    }

    private void postorder(Node node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        System.out.println(node.val);
    }

    public Node predecessor(int val) {
        return null;
    }

    /**
     * Select(k): Given a rank k, 1 ≤ k ≤ N, determine the key v that has that rank k in the BST. Or in another word, find the k-th smallest element in the BST. That is, Select(1) = FindMin() and Select(N) = FindMax().
     */
    public Node select(int rank) {
        return null;
    }

    public Node remove(int val) {
        return null;
    }

    /**
     * Rank(v): Given a key v, determine what is its rank (1-based index) in the sorted order of the BST elements. That is, Rank(FindMin()) = 1 and Rank(FindMax()) = N. If v does not exist, we can report -1.
     */
    public int rank(int val) {
        return -1;
    }

    private void print(Node node, int paddingLeft, int paddingRight, Queue<Node> queue) {
        if (node == null) return;

        String paddingChar = "\t";
        if (node.parent == null) {
            System.out.println(paddingChar.repeat(paddingLeft) + node.val);
        }

        String left = "";
        int newLeftPadding = paddingLeft - 1;
        if (node.left != null) {
            queue.offer(node.left);
            left = paddingChar.repeat(newLeftPadding) + node.left.val;
        }

        String right = "";
        int newRightPadding = paddingRight + 1;
        if (node.right != null) {
            queue.offer(node.right);
            right = paddingChar.repeat(newRightPadding - newLeftPadding) + node.right.val;
        }

        String output = left + right;
        System.out.println(output);
        print(queue.poll(), newLeftPadding, newRightPadding, queue);
    }

    private int maxDepth(Node node) {
        if (node == null) return 0;
        int leftDepth = 1 + maxDepth(node.left);
        int rightDepth = 1 + maxDepth(node.right);
        return Math.max(leftDepth, rightDepth);
    }

    static class Node {
        int val;
        Node parent;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }

        public String toString() {
            return "nodeVal: " + val;
        }

    }
}
