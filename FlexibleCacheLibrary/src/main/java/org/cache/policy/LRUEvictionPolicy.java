package org.cache.policy;

import java.util.HashMap;
import java.util.Map;

public class LRUEvictionPolicy<Key> implements EvictionPolicy<Key>{

    Node head;
    Node tail;

    Map<Key, Node> map;

    public LRUEvictionPolicy() {
        this.head = new Node(null);
        this.tail = new Node(null);
        head.setNext(tail);
        tail.setPrev(head);
        map = new HashMap<>();
    }

    @Override
    public void keyAccessed(Key key) {
        if(map.containsKey(key)) {
            Node node = map.get(key);
            deleteNode(node);
        }
        insertNode(key);
    }

    @Override
    public Key evictKey() {
        Node<Key> lastNode = tail.getPrev();
        deleteNode(lastNode);
        return lastNode.getKey();
    }

    private void insertNode(Key key) {
        Node newNode = new Node<>(key);
        Node nextNode = head.getNext();
        newNode.setNext(nextNode);
        nextNode.setPrev(newNode);
        newNode.setPrev(head);
        head.setNext(newNode);
        map.put(key, newNode);
    }

    private void deleteNode(Node node) {
        Node prev = node.getPrev();
        Node next = node.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        map.remove(node.getKey());
    }

    public void display() {
        Node curr = head;
        while(curr != tail) {
            curr = curr.getNext();
            System.out.print(curr.getKey() + " ");
        }
    }
}
