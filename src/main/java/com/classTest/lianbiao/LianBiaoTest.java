package com.classTest.lianbiao;

/**
 * Created by root on 17-2-21.
 */
public class LianBiaoTest {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        node1.setNext(node2);
        node2.setNext(node3);
        node3.setNext(node4);
        node4.setNext(node5);
        node5.setNext(node1);

        //遍历链表
        /*Node node = node1;
        while (node != null) {
            System.out.print(node.getData()+" ");
            node = node.getNext();
        }*/

        System.out.println(isCircle(node1));
    }

    //判断链表是否为循环链表
    public static boolean isCircle(Node node) {
        if(node == null) return false;
        Node head = node;
        Node next = node.getNext();
        while (next != null) {
            if(next == head) {
                return true;
            }
            next = next.getNext();
        }
        return false;
    }

}

class Node {
    private int data;
    private Node next;

    Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
