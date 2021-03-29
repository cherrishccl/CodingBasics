package com.boot.basics.coding.algorithm.tree;

/**
 * @Author cherrishccl
 * @Date 2021/2/20 13:49
 * @Version 1.0
 * @Description TreeTest
 */
public class TreeTest {

    public int kthSmallest(TreeNode root, int k){
        return kthSmallestHelper(root, k).val;
    }
    private ResultType kthSmallestHelper(TreeNode root, int k){
        if(null == root){
            return new ResultType(false, 0);
        }
        ResultType left = kthSmallestHelper(root.left, k);
        if(left.found){
            return new ResultType(true, left.val);
        }
        if(k - left.val == 1){
            return new ResultType(true, root.val);
        }
        ResultType right = kthSmallestHelper(root.right, k - left.val - 1);
        if(right.found){
            return new ResultType(true, right.val);
        }
        return new ResultType(false, left.val + 1 + right.val);
    }
    private class ResultType {
        boolean found;
        int val;

        public ResultType(boolean found, int val) {
            this.found = found;
            this.val = val;
        }
    }
}
