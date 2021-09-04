package com.cpprac.InterviewQuestions;

/**
 * @author priyesh.mishra
 */


class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class MaxPathSumInBTree {

    /*
    [1,-2,-3,1,3,-2,null,-1]

                 1
            -2       -3
         1     3  -2
     -1

     */
    public int maxPathSum(TreeNode root) {
        int sum = inorder(root);
        return sum;
    }

    int sum = Integer.MIN_VALUE;

    private int inorder(TreeNode root) {
        if ( root == null )
            return -1000;
        int left = inorder(root.left);
        int curr = root.val;
        int right = inorder(root.right);

        root.val = Math.max(curr, Math.max(left + curr, Math.max(curr + right, left + curr + right)));
        sum = Math.max(sum, root.val);

        return Math.max(curr, Math.max(left + curr, curr + right));
    }
}
