package ru.mypackage;

public class Main {
    public static void main(String[] args) {
        MyBinaryTree testTree = new MyBinaryTree();
        testTree.addNode(2);
        testTree.addNode(3);
        testTree.addNode(4);
        System.out.println(testTree.getRoot().getValue());
        MyTreeNode updatedRoot = testTree.rotateLeft(testTree.getRoot());
        testTree.setRoot(updatedRoot);
        System.out.println(testTree.searchNode(3));
        testTree.addNode(5);
        testTree.deleteNode(4);
        System.out.println(testTree.searchNode(3));

        MyBinaryTree testAVLTree = new MyAvlTree();
        testAVLTree.addNode(1);
        testAVLTree.addNode(2);
        testAVLTree.addNode(3);
        System.out.println(testAVLTree.getRoot().getValue());
        testAVLTree.addNode(4);
        testAVLTree.addNode(6);
        testAVLTree.addNode(7);
        testAVLTree.addNode(9);
        System.out.println(testAVLTree.getRoot().getValue());
        testAVLTree.deleteNode(4);
        System.out.println(testAVLTree.getRoot().getValue());
        testAVLTree.addNode(8);
        System.out.println(testAVLTree.searchNode(6));
    }
}