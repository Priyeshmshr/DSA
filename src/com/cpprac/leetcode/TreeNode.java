package com.cpprac.leetcode;

import java.util.Objects;

/**
 * @author priyesh.mishra
 */

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public int height;

    @Override
    public boolean equals(Object o) {
        if ( this == o ) return true;
        if ( o == null || getClass() != o.getClass() ) return false;
        TreeNode treeNode = (TreeNode) o;
        return val == treeNode.val &&
                Objects.equals(left, treeNode.left) &&
                Objects.equals(right, treeNode.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, left, right);
    }

    TreeNode(int x) {
        val = x;
    }

    TreeNode(int x, int height) {
        val = x;
        this.height = height;
    }

}
