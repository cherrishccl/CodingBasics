package com.boot.basics.coding.algorithm.listnode.single;

/**
 * @Author cherrishccl
 * @Date 2021/4/21 17:25
 * @Version 1.0
 * @Description Test
 */
public class NodeTest {
    public static void main(String[] args) {
        Node node = NodeUtils.generateRandomNodeList(10, 10);
        NodeUtils.printNode(node);
        node = NodeUtils.sort(node);
        NodeUtils.printNode(node);
        node = NodeUtils.reverse(node);
        NodeUtils.printNode(node);
    }
}
