package com.boot.basics.coding.algorithm;

/**
 * @Author chencl
 * @Date 2020/12/23 9:25
 * @Version 1.0
 * @Description
 */
public class SingleList {

    public static void main(String[] args) {
        SingleList sl = new SingleList();
        sl.addHead(4);
        sl.addHead(3);
        sl.addHead(2);
        sl.addHead(1);

        System.out.println(sl);
        sl.reverse();
        System.out.println(sl);
    }

    static class Node{
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;

    private Node tail;

    private int size;
    public void reverse(){
        Node h = head;
        //Node n = reverse0(head);
        Node n = reverse1(head);
        tail = h;
        head = n;

    }

    private static Node reverse1(Node head){
        if(null == head || null == head.next){
            return head;
        }
        Node pre = head;
        Node cur = head.next;
        while (null != cur.next){
            Node tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        cur.next = pre;
        head.next = null;
        return cur;
    }

    /**
     * 1 2 3 4
     * 2 3 4
     * 3 4
     * 4
     * @param head
     * @return
     */
    private Node reverse0(Node head){
        if(null == head || null == head.next){
            return head;
        }
        Node rv = reverse0(head.next);
        head.next.next = head;
        head.next = null;
        return rv;
    }
    public void addTail(int data){
        Node newNode = new Node(data);
        if(null == tail){
            tail = newNode;
            if(null == head){
                head = newNode;
            }
        }else {
            Node pre = tail;
            pre.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public void addHead(int data){
        Node newNode = new Node(data);
        if(null == head){
            head = newNode;
            tail = newNode;
        }else {
            Node pre = head;
            head = newNode;
            newNode.next = pre;
        }
        size++;
    }
}
