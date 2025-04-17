package org.cache.policy;

public class Node<Key> {
    private Key key;
    private Node next;
    private Node prev;

    public Node(Key key) {
        this.key = key;
        this.next = null;
        this.prev= null;
    }

    public Node(Key key, Node next, Node prev) {
        this.key = key;
        this.next = next;
        this.prev = prev;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
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
