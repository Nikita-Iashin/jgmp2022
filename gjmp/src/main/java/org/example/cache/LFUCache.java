package org.example.cache;
import java.util.*;

class LFUCache {
    Map<Integer, ListNode> keyMap = new HashMap<>();
    Map<Integer, DoublyList> freqMap = new HashMap<>();
    int curCapcity = 0;
    int maxCapcity;

    public LFUCache(int capacity) {
        this.maxCapcity = capacity;
    }

    private ListNode getNode(int key) {
        if (!keyMap.containsKey(key))
            return null;
        // Retrive current node
        ListNode curNode = keyMap.get(key);

        // Remove curNode from current freq list
        DoublyList list = freqMap.get(curNode.freq);
        list.deleteNode(key);

        // Update the freq of current node
        curNode.freq++;

        // Add curNode onto a higher freq list
        if (!freqMap.containsKey(curNode.freq)) {
            freqMap.put(curNode.freq, new DoublyList());
        }
        freqMap.get(curNode.freq).addNode(curNode);
        return curNode;
    }

    public int get(int key) {
        if (!keyMap.containsKey(key))
            return -1;
        // Retrive current node from current freq list
        ListNode curNode = getNode(key);
        return curNode.val;
    }

    public void put(int key, int value) {
        if (maxCapcity == 0)
            return;
        // Update value
        if (keyMap.containsKey(key)) {
            // Retrive current node from current freq list
            ListNode curNode = getNode(key);
            curNode.val = value;
        } else {
            // Insert value (maybe adjust the size)
            if (curCapcity == maxCapcity) {
                int lowestFreq = Integer.MAX_VALUE;
                for (Integer freq : freqMap.keySet()) {
                    if (freqMap.get(freq).map.isEmpty())
                        continue;
                    lowestFreq = Math.min(lowestFreq, freq);
                }
                DoublyList list = freqMap.get(lowestFreq);
                ListNode curNode = list.deleteHead();
                keyMap.remove(curNode.key);
                curCapcity--;
            }
            int curFreq = 1;
            ListNode curNode = new ListNode(value, key);
            keyMap.put(key, curNode);
            if (!freqMap.containsKey(curFreq)) {
                freqMap.put(curFreq, new DoublyList());
            }
            freqMap.get(curFreq).addNode(curNode);
            curCapcity++;
        }
    }
}
