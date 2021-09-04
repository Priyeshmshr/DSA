package com.cpprac.leetcode;

/**
 * @author priyesh.mishra
 */
public class LC687 {

    int result = Integer.MIN_VALUE;

    public int longestUnivaluePath(TreeNode root) {
        solve(root);
        return result - 1;
    }

    private int solve(TreeNode root) {

        if ( root == null )
            return 0;

        int leftCount = solve(root.left), rightCount = solve(root.right),
                currCount = 1, depth = 0;

        if ( root.left != null && root.left.val == root.val ) {
            currCount += leftCount;
            depth = leftCount;
        }
        if ( root.right != null && root.right.val == root.val ) {
            currCount += rightCount;
            depth = Math.max(depth,rightCount);
        }

        result = Math.max(result, currCount);
        return depth+1;
    }
}
