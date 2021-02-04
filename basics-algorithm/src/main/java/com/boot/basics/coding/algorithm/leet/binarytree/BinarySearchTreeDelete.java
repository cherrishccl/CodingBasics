package com.boot.basics.coding.algorithm.leet.binarytree;

/**
 * @Author cherrishccl
 * @Date 2021/2/4 10:50
 * @Version 1.0
 * @Description 删除二叉搜索树
 */
public class BinarySearchTreeDelete {
    public static TreeNode delete(TreeNode root, int key){
        if(null == root){
            return null;
        }
        if(key < root.val){
            root.left = delete(root.left, key);
            return root;
        }

        if(key > root.val){
            root.right = delete(root.right, key);
            return root;
        }
        if(null == root.left){
            return root.right;
        }
        if(null == root.right){
            return root.left;
        }

        TreeNode pre = maximum(root.left);
        TreeNode pre1 = new TreeNode(pre.val);
        pre1.left = removeMax(root.left);
        pre1.right = root.right;
        root.left = null;
        root.right = null;
        return pre1;
    }

    private static TreeNode removeMax(TreeNode node){
        if(null == node.right){
            return node.left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    private static TreeNode maximum(TreeNode node){
        if(null == node.right){
            return node;
        }
        return maximum(node.right);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
//        TreeNode n1 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2);
//        TreeNode n3 = new TreeNode(2);
//        TreeNode n4 = new TreeNode(4);
//        TreeNode n5 = new TreeNode(7);
//
//        root.left = n1;
        root.right = n2;
//        n1.left = n3;
//        n1.right = n4;
//        n2.right = n5;

        delete(root, 1);
    }
}
