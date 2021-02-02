package com.boot.basics.coding.algorithm.leet.binarytree;

/**
 * @Author cherrishccl
 * @Date 2021/2/1 17:27
 * @Version 1.0
 * @Description TreeNode
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    static void preTraverse(TreeNode treeNode){
        System.out.print(treeNode.val + "\t");
        if(null != treeNode.left){
            preTraverse(treeNode.left);
        }
        if(null != treeNode.right){
            preTraverse(treeNode.right);
        }
    }

    static void midTraverse(TreeNode treeNode){
        if(null != treeNode.left){
            midTraverse(treeNode.left);
        }
        System.out.print(treeNode.val + "\t");
        if(null != treeNode.right){
            midTraverse(treeNode.right);
        }
    }

    static void postTraverse(TreeNode treeNode){
        if(null != treeNode.left){
            postTraverse(treeNode.left);
        }
        if(null != treeNode.right){
            postTraverse(treeNode.right);
        }
        System.out.print(treeNode.val + "\t");
    }

    public static TreeNode initTree(){
        TreeNode root = new TreeNode(1);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(3);
        TreeNode c = new TreeNode(4);
        TreeNode d = new TreeNode(5);
        TreeNode e = new TreeNode(6);
        TreeNode f = new TreeNode(7);
        TreeNode g = new TreeNode(8);
        root.left = a;
        root.right = b;
        a.left = c;
        c.right = f;
        b.left = d;
        b.right = e;
        e.left = g;
        return root;
    }
    public static void main(String[] args) {
        TreeNode root = initTree();
        preTraverse(root);
        System.out.println();
        midTraverse(root);
        System.out.println();
        postTraverse(root);
    }
}
