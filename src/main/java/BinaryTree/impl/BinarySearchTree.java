package BinaryTree.impl;

import BinaryTree.inerface.DoSth;

import java.util.ArrayDeque;
import java.util.Queue;

public class BinarySearchTree<K extends Comparable, V> {

    private Node root;
    private int count;

    public void insert(K key, V value) {
        root = insert(root, key, value);
    }

    public V search(K key) {
        return search(root, key);
    }

    public boolean contains(K key) {
        return contains(root, key);
    }

    public void preOrder(DoSth<Node> fun) {
        preOrder(root, fun);
    }

    public void midOrder(DoSth<Node> fun) {
        midOrder(root, fun);
    }

    public void backOrder(DoSth<Node> fun) {
        backOrder(root, fun);
    }

    public void breadthFirstSearch(DoSth<Node> fun) {
        int n = new Double(Math.log(count) / Math.log(2)).intValue();
        Queue<Node> queue = new ArrayDeque<>(count);
        queue.add(root);
        while (queue.peek() != null) {
            Node node = queue.remove();
            fun.doSth(node);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }

    private void preOrder(Node root, DoSth<Node> fun) {
        if (root == null)
            return;
        fun.doSth(root);
        preOrder(root.left, fun);
        preOrder(root.right, fun);
    }

    private void midOrder(Node root, DoSth<Node> fun) {
        if (root == null)
            return;
        midOrder(root.left, fun);
        fun.doSth(root);
        midOrder(root.right, fun);
    }

    private void backOrder(Node root, DoSth<Node> fun) {
        if (root == null)
            return;
        backOrder(root.left, fun);
        backOrder(root.right, fun);
        fun.doSth(root);
    }

    @SuppressWarnings("unchecked")
    public V search(Node root, K key) {
        if (root == null) {
            return null;
        } else if (root.key.compareTo(key) == 0) {
            return root.value;
        } else if (root.key.compareTo(key) < 0) {
            return search(root.right, key);
        } else if (root.key.compareTo(key) > 0) {
            return search(root.left, key);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    private boolean contains(Node root, K key) {
        if (root == null) {
            return false;
        } else if (root.key.compareTo(key) == 0) {
            return true;
        } else if (root.key.compareTo(key) < 0) {
            return contains(root.right, key);
        } else if (root.key.compareTo(key) > 0) {
            return contains(root.left, key);
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    private Node insert(Node root, K key, V value) {
        if (root == null) {
            count++;
            root = new Node(key, value);
        } else if (root.key.compareTo(key) < 0) {
            root.right = insert(root.right, key, value);
        } else if (root.key.compareTo(key) == 0) {
            root.value = value;
        } else if (root.key.compareTo(key) > 0) {
            root.left = insert(root.left, key, value);
        }
        return root;
    }

    public BinarySearchTree() {
        root = null;
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public class Node {
        K key;
        V value;
        Node left;
        Node right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return key.toString() + " ";
        }
    }
}
