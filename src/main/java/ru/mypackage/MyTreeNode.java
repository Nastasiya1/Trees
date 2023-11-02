package ru.mypackage;

public class MyTreeNode {
    private MyTreeNode left;
    private MyTreeNode right;
    private int value;
    private int height;

    public MyTreeNode(int value) {
        this.value = value;
    }

    public MyTreeNode getLeft() {
        return left;
    }

    public void setLeft(MyTreeNode left) {
        this.left = left;
    }

    public MyTreeNode getRight() {
        return right;
    }

    public void setRight(MyTreeNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "TreeNode: value - " + value + ", left - (" + left + "), right - (" + right + ")";
    }
}