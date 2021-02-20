package com.boot.basics.coding.algorithm.listnode;

import java.util.Stack;

/**
 * @Author cherrishccl
 * @Date 2021/2/20 8:55
 * @Version 1.0
 * @Description ListNode
 */
public class ListNode<T> {

    public static void main(String[] args) {
        ListNode<Integer> n1 = new ListNode<>(1);
        ListNode<Integer> n2 = new ListNode<>(2);
        ListNode<Integer> n3 = new ListNode<>(3);
        ListNode<Integer> n4 = new ListNode<>(4);
        ListNode<Integer> n5 = new ListNode<>(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        reverse(n1);
        System.out.println();
    }

    public static <T> void reverse(ListNode<T> head){
        if(null == head || null == head.next){
            return;
        }
        ListNode<T> currentNode = head;
        Stack<ListNode<T>> stack = new Stack<>();
        while (null != currentNode){
            stack.push(currentNode);
            ListNode<T> tempNode = currentNode.next;
            currentNode.next = null;
            currentNode = tempNode;
        }
        head = stack.pop();
        currentNode = head;
        while (!stack.isEmpty()){
            currentNode.next = stack.pop();
            currentNode = currentNode.next;
        }
    }

    public ListNode(T val) {
        this.val = val;
    }

    T val;
    ListNode<T> next;
}
