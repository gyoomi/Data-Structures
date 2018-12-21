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
        var balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1) {
            return false;
        }
        return isBalanced(node.left) && isBalanced(node.right);
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

        // Maintain balance
        // LL
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node node) {
        Node retNode = node.left;
        Node tmpNode = retNode.right;
        // right rotation
        retNode.right = node;
        node.left = tmpNode;
        // update the height of nodes
        // 按照自下而上的方向进行更新
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }
    private Node leftRotate(Node node) {
        Node retNode = node.right;
        Node tmpNode = retNode.left;
        // right rotation
        retNode.left = node;
        node.right = tmpNode;
        // update the height of nodes
        // 按照自下而上的方向进行更新
        node.height = Math.max(getHeight(node.left), getHeight(node.right)) + 1;
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;

        return retNode;
    }



    private Node getNode(Node node, K key) {

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
        Node retNode;
        if( key.compareTo(node.key) < 0 ) {
            node.left = remove(node.left , key);
            retNode = node;
        } else if (key.compareTo(node.key) > 0 ) {
            node.right = remove(node.right, key);
            retNode = node;
        } else { // key.compareTo(node.key) == 0

            // 待删除节点左子树为空的情况
            if(node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                retNode = rightNode;
            } else if(node.right == null) { // 待删除节点右子树为空的情况
                Node leftNode = node.left;
                node.left = null;
                size--;
                retNode = leftNode;
            } else {

                Node successor = minimum(node.right);
                // 注意这里有个小bug
                // successor.right = removeMin(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;

                node.left = node.right = null;

                retNode = successor;
            }
        }

        if (null == retNode) {
            return null;
        }

        // update height
        retNode.height = Math.max(getHeight(retNode.left), getHeight(retNode.right)) + 1;
        //calculate the factor of balance
        var balanceFactor = getBalanceFactor(retNode);
        // LL
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }
        // RR
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }
        // LR
        if (balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }
        // RL
        if (balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }
        return retNode;
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

            for(String word: words){
                map.remove(word);
                if (!map.isBST() || !map.isBalanced()) {
                    throw new RuntimeException();
                }
            }
            System.out.println();
        }

        System.out.println();
    }
}
