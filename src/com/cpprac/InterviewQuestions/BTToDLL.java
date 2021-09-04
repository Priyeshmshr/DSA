package com.cpprac.InterviewQuestions;

/**
 * @author priyesh.mishra
 */
class BTree {
    BTree left, right ;
    int data;
    public BTree(int data){
        this.data = data;
    }
}

public class BTToDLL {

    public static void main(String[] args) {
        BTree btree = new BTree(10);
        btree.left = new BTree(12);
        btree.right = new BTree(15);
        btree.right.left = new BTree(36);
        btree.left.left= new BTree(25);
        btree.left.right = new BTree(30);
        inorder(btree);
        System.out.println();
        BtreeToDLL(btree);
        printDLL(btree);
    }

    private static void printDLL(BTree btree) {
        BTree temp = btree;
        while(temp.left !=null){
            temp=temp.left;
        }
        while(temp!=null) {
            System.out.println(temp.data);
            temp = temp.right;
        }
    }

    public static void inorder(BTree btree){
        if(btree == null) {
            return;
        }
        inorder(btree.left);
        System.out.print(btree.data+" ");
        inorder(btree.right);
    }

    public static BTree BtreeToDLL(BTree btree){
        if(btree == null) {
           return null;
        }

        BTree previous = BtreeToDLL(btree.left);
        System.out.print(btree.data + " ");
        if(!(btree.left == null && btree.right == null)) {
            //if(btree.left != null) {
                if(btree.right != null && btree.left != null) {
                if(btree.right.left != btree.right) {
                btree.left.right = btree;
                }
                //}
            }

            if(btree.right != null) {
                if(btree.left != null) {
                if(btree.left.right != btree.left) {
                btree.right.left = btree;
                }
                }
            }
        }


        BtreeToDLL(btree.right);
        return btree;
    }
}
