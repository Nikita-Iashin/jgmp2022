package org.example.cache;

public class ListNode {
    ListNode prev, next;
    int val, key, freq;

    ListNode(int val, int key) {
        this.val = val;
        this.key = key;
        this.freq = 1;
    }

    public ListNode() {

    }
}
