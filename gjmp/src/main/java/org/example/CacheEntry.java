package org.example;

/**
 * Cache entries (objects) – simple custom class with one String field. Your cache service should have 2 methods:
 * get and put.
 */
public class CacheEntry {
    private String entry;

    public String get() {
        return entry;
    }

    public void put(String entry) {
        this.entry = entry;
    }
}
