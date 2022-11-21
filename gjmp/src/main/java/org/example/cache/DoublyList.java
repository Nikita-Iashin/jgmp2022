package org.example.cache;

import java.util.HashMap;
import java.util.Map;


class DoublyList {
    Map<Integer, ListNode> map = new HashMap<>();
    ListNode head, tail;

    public DoublyList() {
        head = new ListNode();
        tail = new ListNode();
        tail.prev = head;
        head.next = tail;
    }

    public void addNode(ListNode curNode) {
        ListNode tailPrev = tail.prev;
        tailPrev.next = curNode;
        curNode.prev = tailPrev;
        tail.prev = curNode;
        curNode.next = tail;
        map.put(curNode.key, curNode);
    }

    public ListNode deleteNode(int key) {
        if (!map.containsKey(key))
            return null;
        ListNode curNode = map.get(key);
        ListNode prevNode = curNode.prev;
        ListNode nextNode = curNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        map.remove(key);
        return curNode;
    }

    public ListNode deleteHead() {
        if (head.next == tail)
            return null;
        ListNode firstNode = head.next;
        return deleteNode(firstNode.key);
    }
}