package com.boot.basics.coding.algorithm.listnode.single;

import java.util.*;

/**
 * @Author cherrishccl
 * @Date 2021/4/21 17:15
 * @Version 1.0
 * @Description NodeTest
 */
public class NodeUtils {

    public static Node reverse(Node head){
        if(null == head || null == head.next){
            return head;
        }
        Node res = new Node(-1);
        Node cur = head;
        while (null != cur){
            Node temp = cur.next;
            cur.next = res.next;
            res.next = cur;
            cur = temp;
        }
        return res.next;
        /*List<Node> list = new ArrayList<>();
        while (null != head){
            list.add(head);
            head = head.next;
        }
        head = list.get(list.size() - 1);
        list.get(0).next = null;
        Node cur = head;
        for(int i = list.size() - 2; i >=0; i--){
            cur.next = list.get(i);
            cur = cur.next;
        }
        return head;*/
    }

    public static Node sort(Node head){
        if(null == head || null == head.next){
            return head;
        }
        List<Node> list = new ArrayList<>();
        while (null != head){
            list.add(head);
            head = head.next;
        }
        Node[] arr = new Node[list.size()];
        list.toArray(arr);
        quickSort(arr, 0, arr.length - 1);
        arr[arr.length - 1].next =  null;
        head = arr[0];
        Node cur = head;
        for(int i = 1; i < arr.length; i++){
            cur.next = arr[i];
            cur = cur.next;
        }
        return head;
    }

    private static void quickSort(Node[] arr, int left, int right){
        if(left >= right){
            return;
        }
        Node radix = arr[left];
        int i = left, j = right;

        while (i != j){
            while (arr[j].val >= radix.val && i < j){
                j--;
            }

            while (arr[i].val <= radix.val && i < j){
                i++;
            }

            if(i < j){
                Node temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[left] = arr[i];
        arr[i] = radix;

        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    public static void printNode(Node head){
        if(null == head){
            System.out.println("NULL");
        }
        while (null != head){
            System.out.print(head.val);
            System.out.print("\t");
            head = head.next;
        }
        System.out.println();
        System.out.println("--------");
    }

    /**
     * 生成随机数
     * @return
     */
    public static Node generateRandomNodeList(int len, int seed){
        Node head = null;
        if(len > 0){
            if(seed < len){
                seed = len;
            }
            Set<Integer> set = new HashSet<>(len);
            Random random = new Random();
            int val = random.nextInt(seed);
            set.add(val);
            head = new Node(val);
            Node cur = head;
            while (--len > 0){
                while (set.contains(val)){
                    val = random.nextInt(seed);
                }
                set.add(val);
                cur.next = new Node(val);
                cur = cur.next;
            }
        }
        return head;
    }
}
