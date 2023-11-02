package ru.mypackage;

public class MyAvlTree extends MyBinaryTree {
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
        updateHeight(myTreeNode);
        return rebalanced(myTreeNode);
    }

    private void updateHeight(MyTreeNode myTreeNode) {
        myTreeNode.setHeight(1 + Math.max(height(myTreeNode.getLeft()), height(myTreeNode.getRight())));
    }

    private int height(MyTreeNode myTreeNode) {
        return myTreeNode == null ? -1 : myTreeNode.getHeight();
    }

    private int getBalance(MyTreeNode myTreeNode) {
        return (myTreeNode == null) ? 0 : height(myTreeNode.getRight()) - height(myTreeNode.getLeft());
    }

    private MyTreeNode rebalanced(MyTreeNode root) {
        int balance = getBalance(root);
        if (balance > 1) {
            if (height(root.getRight().getRight()) > height(root.getRight().getLeft())) {
                root = rotateLeft(root);
            } else {
                root.setRight(rotateRight(root.getRight()));
                root = rotateLeft(root);
            }
        } else if (balance < -1) {
            if (height(root.getLeft().getLeft()) > height(root.getLeft().getRight())) {
                root = rotateRight(root);
            } else {
                root.setLeft(rotateLeft(root.getLeft()));
                root = rotateRight(root);
            }
        }
        return root;
    }

    public void deleteNode(int value) {
        setRoot(deleteRecurs(value, getRoot()));
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
        if (myTreeNode == null) {
            return null;
        }
        updateHeight(myTreeNode);
        return rebalanced(myTreeNode);
    }

    public MyTreeNode rotateLeft(MyTreeNode root) {
        MyTreeNode x = root.getRight();
        if (x == null) return root;
        MyTreeNode y = x.getLeft();
        x.setLeft(root);
        root.setRight(y);
        updateHeight(root);
        updateHeight(x);
        return x;
    }

    public MyTreeNode rotateRight(MyTreeNode root) {
        MyTreeNode x = root.getLeft();
        if (x == null) return root;
        MyTreeNode y = x.getRight();
        x.setRight(root);
        root.setLeft(y);
        updateHeight(root);
        updateHeight(x);
        return x;
    }
}