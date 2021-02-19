package com.boot.basics.coding.algorithm.leet.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author cherrishccl
 * @Date 2021/2/2 9:35
 * @Version 1.0
 * @Description 验证二叉搜索树
 */
public class BinarySearchTreeVerify {
    public static void main(String[] args) {
        TreeNode root = TreeNode.initTree();
        System.out.println(solution1(root));
        System.out.println(solution2(root));
    }

    /**
     * 查找上下边界
     * @param root
     * @return
     */
    private static boolean solution2(TreeNode root){
        return valid(root, null, null);
    }
    private static boolean valid(TreeNode root, Integer min, Integer max){
        if(null == root){
            return true;
        }
        int val = root.val;
        if(null != min && val <= min){
            return false;
        }
        if(null != max && val >= max){
            return false;
        }
        if(!valid(root.left, min, max)){
            return false;
        }
        if(!valid(root.right, min, max)){
            return false;
        }
        return true;
    }
    /**
     * 二叉搜索树中序遍历是有序的
     * @param root
     * @return
     */
    private static boolean solution1(TreeNode root){
        List<Integer> rs = new ArrayList<>();
        midTraverse(root, rs);
        if (rs.size() < 2) {
            return true;
        }
        int a = rs.get(0);
        for (int i = 1; i < rs.size(); i++) {
            if (rs.get(i) <= a) {
                return false;
            }
            a = rs.get(i);
        }
        return true;
    }

    static void midTraverse(TreeNode treeNode, List<Integer> rs){
        if(null != treeNode.left){
            midTraverse(treeNode.left, rs);
        }
        rs.add(treeNode.val);
        if(null != treeNode.right){
            midTraverse(treeNode.right, rs);
        }
    }
}
