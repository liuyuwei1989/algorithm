package BinaryTree.main;

import BinaryTree.impl.BinarySearchTree;

import java.util.ArrayList;
import java.util.Random;

public class BinaryMain {
    private static Random random = new Random();

    public static void main(String[] args) {
       /*int arr[] = {2, 3, 9, 5, 7, 1, 4, 25, 16, 39, 87, 59, 12, 36, 474, 599, 658, 36};
        BinarySearch search = new BinarySearch(arr);
        System.out.println(search.search(474));*/
        int size = 10;
        int bound = 1000000;
        ArrayList<Integer> keys = new ArrayList<>();
        ArrayList<Long> values = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            keys.add(random.nextInt(bound));
            values.add(random.nextLong());
        }
        BinarySearchTree<Integer, Long> tree = new BinarySearchTree<>();
        for (int i = 0; i < size; i++) {
            tree.insert(keys.get(i), values.get(i));
        }
        Integer key = keys.get(random.nextInt(size));
        Long result = 0l;
        System.out.println(result = tree.search(key));
        System.out.println(keys.indexOf(key));
        System.out.println(values.indexOf(result));
        System.out.println(tree.contains(key));
        System.out.println(tree.search(bound));
        System.out.println(tree.size() == size);
        tree.preOrder(System.out::print);
        System.out.println();
        tree.backOrder(System.out::print);
        System.out.println();
        tree.midOrder(System.out::print);
        System.out.println();
        tree.breadthFirstSearch(System.out::print);
        System.out.println();
    }
}
