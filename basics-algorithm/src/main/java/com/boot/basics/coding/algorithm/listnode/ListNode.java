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
        ListNode<Integer> n6 = new ListNode<>(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        //reverse(n1);
        removeNthEnd(n1, 3);
        System.out.println();

    }

    /**
     * 合并两个有序链表
     * 1->2->3
     * 2->3->4
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2){
        if(null == l1){
            return l2;
        }
        if(null == l2){
            return l1;
        }
        if(l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l2.next, l1);
            return l2;
        }
    }

    public static ListNode<Integer> mergeTwoLists2(ListNode<Integer> l1, ListNode<Integer> l2){
       ListNode<Integer> dummy = new ListNode<>(0);
       ListNode<Integer> cur = dummy;
       while (null != l1 && null != l2){
           if(l1.val < l2.val){
               cur.next = l1;
               l1 = l1.next;
           }else {
               cur.next = l2;
               l2 = l2.next;
           }
           cur = cur.next;
       }
       cur.next = null == l1 ? l2 : l1;
       return dummy.next;
    }

    public static <T> ListNode<T> removeNthEnd(ListNode<T> head, int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
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

    public T val;
    public ListNode<T> next;
}
