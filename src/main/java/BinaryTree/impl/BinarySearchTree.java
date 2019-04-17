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

    public V findMinimum() {
        return findMinimum(root).value;
    }

    public V findMaximum() {
        return findMaximum(root).value;
    }

    public V removeMinimum() {
        V tmp = null;
        if (root != null) {
            tmp = findMinimum();
            root = removeMinimum(root);
        }
        return root == null ? null : tmp;
    }

    public V removeMaximum() {
        V tmp = null;
        if (root != null) {
            tmp = findMaximum();
            root = removeMaximum(root);
        }
        return root == null ? null : tmp;
    }

    public void removeKey(K key) {
        root = removeKey(root, key);
    }

    @SuppressWarnings("unchecked")
    private Node removeKey(Node root, K key) {
        if (root == null) {
            root = root;
        } else if (root.key.compareTo(key) < 0) {
            root.right = removeKey(root.right, key);
        } else if (root.key.compareTo(key) > 0) {
            root.left = removeKey(root.left, key);
        } else if (root.key.compareTo(key) == 0) {
            if (root.right != null) {
                Node tmp = findMinimum(root.right);
                root.key = tmp.key;
                root.value = tmp.value;
                root.right = removeMinimum(root.right);
            } else {
                Node tmp = root.left;
                root.left = null;
                root = tmp;
                count--;
            }
        }

        return root;
    }

    private Node removeMinimum(Node root) {
        if (root.left != null) {
            root.left = removeMinimum(root.left);
            return root;
        } else {
            Node tmp = root.right;
            root.right = null;
            count--;
            return tmp;
        }
    }

    private Node removeMaximum(Node root) {
        if (root.right != null) {
            root.right = removeMaximum(root.right);
            return root;
        } else {
            Node tmp = root.left;
            root.left = null;
            count--;
            return tmp;
        }
    }

    private Node findMinimum(Node root) {
        if (root == null) {
            return null;
        }
        if (root.left == null) {
            return root;
        } else {
            return findMinimum(root.left);
        }
    }

    private Node findMaximum(Node root) {
        if (root == null) {
            return null;
        }
        if (root.right == null) {
            return root;
        } else {
            return findMaximum(root.right);
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
