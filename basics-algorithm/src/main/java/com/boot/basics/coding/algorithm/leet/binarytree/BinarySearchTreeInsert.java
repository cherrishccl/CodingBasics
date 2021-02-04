package com.boot.basics.coding.algorithm.leet.binarytree;

/**
 * @Author cherrishccl
 * @Date 2021/2/4 11:05
 * @Version 1.0
 * @Description BinarySearchTreeInsert
 */
public class BinarySearchTreeInsert {
    public static TreeNode insert(TreeNode root, int val){
        TreeNode node = new TreeNode(val);
        if(null == root){
            return node;
        }
        TreeNode tmp = root;
        if(val >= root.val){
            if(null == root.right){
                root.right = node;
            }else {
                insert(root.right, val);
            }
        }else {
            if(null == root.left){
                root.left = node;
            }else {
                insert(root.left, val);
            }
        }
        return tmp;
    }
}
