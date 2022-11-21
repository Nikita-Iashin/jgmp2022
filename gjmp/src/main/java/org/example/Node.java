package org.example;

public class Node {
    private Node previous;
    private Node next;
    private int key;
    private CacheEntry value;
    private int frequency;

    Node(int key, CacheEntry value) {
        this.key = key;
        this.value = value;
        this.frequency = 1;
    }

}
