package org.cache;

public class Node {
    private int key;
    private int value;
    private Node next;
    private Node prev;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.prev= null;
    }

    public Node(int key, int value, Node next, Node prev) {
        this.key = key;
        this.value = value;
        this.next = next;
        this.prev = prev;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }
}
