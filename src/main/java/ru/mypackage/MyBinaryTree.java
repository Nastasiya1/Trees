package ru.mypackage;

public class MyBinaryTree {
    private MyTreeNode root;

    public MyTreeNode getRoot() {
        return root;
    }

    public void setRoot(MyTreeNode root) {
        this.root = root;
    }

    public void addNode(int value) {
        this.setRoot(addRecurs(value, this.getRoot()));
    }

    private MyTreeNode addRecurs(int value, MyTreeNode myTreeNode) {
        if (myTreeNode == null) {
            myTreeNode = new MyTreeNode(value);
        } else if (value < myTreeNode.getValue()) {
            myTreeNode.setLeft(addRecurs(value, myTreeNode.getLeft()));
        } else if (value > myTreeNode.getValue()) {
            myTreeNode.setRight(addRecurs(value, myTreeNode.getRight()));
        } else {
            throw new IllegalArgumentException("duplicate value");
        }
        return myTreeNode;
    }

    public void deleteNode(int value) {
        root = deleteRecurs(value, root);
    }

    private MyTreeNode deleteRecurs(int value, MyTreeNode myTreeNode) {
        if (myTreeNode == null) {
            return null;
        }
        //find the needed node in the tree
        if (value < myTreeNode.getValue()) {
            myTreeNode.setLeft(deleteRecurs(value, myTreeNode.getLeft()));
        } else if (value > myTreeNode.getValue()) {
            myTreeNode.setRight(deleteRecurs(value, myTreeNode.getRight()));
        }
        //if no children
        else if (myTreeNode.getLeft() == null && myTreeNode.getRight() == null) {
            myTreeNode = null;
        }
        //if one child
        else if (myTreeNode.getLeft() == null) {
            myTreeNode = myTreeNode.getRight();
        } else if (myTreeNode.getRight() == null) {
            myTreeNode = myTreeNode.getLeft();
        }
        // if two children
        else {
            MyTreeNode min = myTreeNode.getRight(); //find the smallest left leaf of the right child
            while (min.getLeft() != null) {
                min = min.getLeft();
            }
            myTreeNode.setValue(min.getValue()); //the leaf's value replaces the deleting value
            myTreeNode.setRight(deleteRecurs(min.getValue(), myTreeNode.getRight())); //delete the previous min leaf from the tree recursively
        }
        return myTreeNode;
    }

    public MyTreeNode searchNode(int value) {
        MyTreeNode current = root;
        while (current != null) {
            if (value == current.getValue()) {
                break;
            }
            current = current.getValue() < value ? current.getRight() : current.getLeft();
        }
        return current;
    }

    public MyTreeNode rotateLeft(MyTreeNode root) {
        MyTreeNode x = root.getRight();
        if (x == null) return root;
        MyTreeNode y = x.getLeft();
        x.setLeft(root);
        root.setRight(y);
        return x;
    }

    public MyTreeNode rotateRight(MyTreeNode root) {
        MyTreeNode x = root.getLeft();
        if (x == null) return root;
        MyTreeNode y = x.getRight();
        x.setRight(root);
        root.setLeft(y);
        return x;
    }
}