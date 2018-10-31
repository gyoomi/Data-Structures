/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/10/24 10:50
 */
public class BST<E extends Comparable<E>> {

    private Node root;
    private int size;

    public BST() {
        root = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 向二分搜索树中添加元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }

    /**
     * 向以node为根的二叉树中添加元素
     * 返回添加元素的二叉树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node add(Node node, E e) {
        // The entrance of recursion
        if (node == null) {
            size++;
            return new Node(e);
        }
        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }
        return node;
    }

    /**
     * 判断二分搜索树中是否包含元素e
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right ,e);
        }
    }

    /**
     * 二分搜索树前序遍历
     *
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根节点的二分搜索树：递归算法
     *
     * @param node
     */
    private void preOrder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    /**
     * 二分搜索树的前序遍历的非递归实现
     *
     */
    public void preOrderNR() {
        preOrderNR(root);
    }

    /**
     * 非递归的方式实现以node为根节点的而二分搜索树的前序遍历
     *
     * @param node
     */
    private void preOrderNR(Node node) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.println(curr.e);
            if (curr.right != null) {
                stack.push(curr.right);
            }
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    /**
     * 二分搜索树的层级遍历（广度优先遍历）
     *
     */
    public void levelOrder() {
        Queue<Node> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()) {
            Node curr = list.remove();
            System.out.println(curr.e);
            if (curr.left != null) {
                list.add(curr.left);
            }
            if (curr.right != null) {
                list.add(curr.right);
            }
        }
    }

    /**
     * 返回二叉搜索树中最小值
     *
     * @return
     */
    public E minimum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        Node node = minimum(root);
        return node.e;
    }

    /**
     * 返回以node为根节点的二叉搜索树中的最小值所在的节点
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);
    }

    /**
     * 返回二叉搜索树中的最大值
     *
     * @return
     */
    public E maximum() {
        if (size == 0) {
            throw new IllegalArgumentException("BST is empty");
        }
        Node node = maximum(root);
        return node.e;
    }

    /**
     * 返回以node为根节点的二叉搜索树的最大值所在的节点
     *
     * @param node
     * @return
     */
    private Node maximum(Node node) {
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    /**
     * 删除二分搜索树中最小节点，并且返回最小值
     *
     * @return
     */
    public E removeMin() {
        E min = minimum();
        root = removeMin(root);
        return min;
    }

    /**
     * 删除以node为根的二分搜索树中最小节点
     * 返回删除节点后新的以node为根的二分搜索树
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node == null) {
            return null;
        }
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除二分搜索树中最大节点，并且返回最大值
     *
     * @return
     */
    public E removeMax() {
        E max = maximum();
        root = removeMax(root);
        return max;
    }

    /**
     * 删除以node为根的二分搜索树中最大节点
     * 返回删除节点后新的以node为根的二分搜索树
     *
     *
     * @param node
     * @return
     */
    private Node removeMax(Node node) {
        if (node == null) {
            return null;
        }
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 二分搜索树的中序遍历
     *
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历以node为根节点的二分搜索数：recursion
     *
     * @param node
     */
    private void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    /**
     * 从二分搜索树的删除元素为e的节点
     *
     * @param e
     */
    public void remove(E e) {
        root = remove(root, e);
    }

    /**
     * 删除掉以node为根的二分搜索树中值为e的节点, 递归算法
     * 返回删除节点后新的二分搜索树的根
     *
     * @param node
     * @param e
     * @return
     */
    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }
        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else { //e.compareTo(node.e) == 0
            // 待删除的节点的左子树的为空的情况下
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }
            // 待删除的节点的右子树的为空的情况下
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }
            // 待删除的节点的左右子树均不为空的情况下
            // 思路：找到比待删除节点大的最小节点, 即待删除节点右子树的最小节点
            // 用这个节点顶替待删除节点的位置
            Node successor = new Node(minimum(node.right).e);
            size++;
            successor.left = node.left;
            successor.right = removeMin(node.right);

            node.left = node.right = null;
            size--;
            return successor;
        }

    }

    /**
     * 二分搜索树后序遍历
     *
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 后序遍历以node为节点的二分搜索树：recursion
     * 典型应用：如C++的二分搜索树需要手动进行内存管理，所以在释放元素时，必须保证其子元素先释放
     *
     * @param node
     */
    private void postOrder(Node node) {
        if (node == null) {
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    class Node {
        public E e;
        public Node left, right;
        public Node(E e) {
            this.e = e;
            left = null;
            right = null;
        }
    }

}
