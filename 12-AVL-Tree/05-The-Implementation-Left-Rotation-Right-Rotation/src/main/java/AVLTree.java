/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/19 15:06
 */
public class AVLTree<K extends Comparable<K>, V> {

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private int getHeight(Node node) {
        if (null == node) {
            return 0;
        }
        return node.height;
    }

    private int getBalanceFactor(Node node) {
        if (null == node) {
            return 0;
        }
        return getHeight(node.left) - getHeight(node.right);
    }

    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }
        return true;
    }

    private void inOrder(Node node, List<K> keys) {
        if (null == node) {
            return;
        }
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node node) {
        if (null == node) {
            return true;
        }
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
    }

    // 对节点y进行向右旋转操作，返回旋转后新的根节点x
    //        y                              x
    //       / \                           /   \
    //      x   T4     向右旋转 (y)        z     y
    //     / \       - - - - - - - ->    / \   / \
    //    z   T3                       T1  T2 T3 T4
    //   / \
    // T1   T2
    private Node rightRotate(Node node) {
        Node retNode = node.left;
        Node tmpNode = retNode.right;
        // right rotation
        retNode.right = node;
        node.left = tmpNode;
        // update the height of nodes
        node.height = Math.max(getHeight(node.right), getHeight(node.left)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }


    // 对节点y进行向左旋转操作，返回旋转后新的根节点x
    //    y                             x
    //  /  \                          /   \
    // T1   x      向左旋转 (y)       y     z
    //     / \   - - - - - - - ->   / \   / \
    //   T2  z                     T1 T2 T3 T4
    //      / \
    //     T3 T4
    private Node leftRotate(Node node) {
        Node retNode = node.right;
        Node tmpNode = retNode.left;
        // right rotation
        retNode.left = node;
        node.right = tmpNode;
        // update the height of nodes
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }


    public void add(K key, V value){
        root = add(root, key, value);
    }

    private Node add(Node node, K key, V value){
        if (null == node) {
            size++;
            return new Node(key, value);
        }

        if (key.compareTo(node.key) < 0) {
            node.left = add(node.left, key, value);
        } else if (key.compareTo(node.key) > 0) {
            node.right = add(node.right, key, value);
        } else { // key.compareTo(node.key) == 0
            node.value = value;
        }

        // Calculate the height
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        // Calculate the factor of balance for current node
        int balanceFactor = getBalanceFactor(node);
/*        if (Math.abs(balanceFactor) > 1) {
            System.out.println("unbalance：" + balanceFactor);
        }*/

        // Maintain balance
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        return node;
    }

    private Node getNode(Node node, K key){

        if (null == node) {
            return null;
        }

        if (key.equals(node.key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else {  // if(key.compareTo(node.key) > 0)
            return getNode(node.right, key);
        }
    }

    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    public V get(K key){

        Node node = getNode(root, key);
        return null == node ? null : node.value;
    }

    public void set(K key, V newValue) {
        Node node = getNode(root, key);
        if (node == null) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = newValue;
    }

    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    // 删除掉以node为根的二分搜索树中的最小节点
    // 返回删除节点后新的二分搜索树的根
    private Node removeMin(Node node){

        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 从二分搜索树中删除键为key的节点
    public V remove(K key){

        Node node = getNode(root, key);
        if(null != node){
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){

        if( node == null ) {
            return null;
        }

        if( key.compareTo(node.key) < 0 ) {
            node.left = remove(node.left , key);
            return node;
        } else if (key.compareTo(node.key) > 0 ) {
            node.right = remove(node.right, key);
            return node;
        } else { // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }

            // 待删除节点右子树为空的情况
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }

            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }
    }

    private class Node {
        public K key;
        public V value;
        public Node left,right;
        public int height;

        public Node (K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    public static void main(String[] args) {
        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("d:\\todo\\pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            AVLTree<String, Integer> map = new AVLTree<>();
            for (String word : words) {
                if (map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.add(word, 1);
            }

            System.out.println("Total different words: " + map.getSize());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));

            System.out.println("is BST : " + map.isBST());
            System.out.println("is Balanced : " + map.isBalanced());
        }

        System.out.println();
    }
}
