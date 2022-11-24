package org.example;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class LFUCache {
    private static final int INITIAL_CAPACITY = 1000_000;
    private Map<CacheEntry, Integer> cacheMap = new LinkedHashMap();

    private boolean isFull() {
        return cacheMap.size() == INITIAL_CAPACITY;
    }

    private boolean isContainsKey(CacheEntry cacheEntry) {
        return cacheMap.containsKey(cacheEntry);
    }

    public void put(CacheEntry data) {
        if (!isFull() && !cacheMap.containsKey(data)) {
            cacheMap.put(data, 0);
        } else if (!isFull() && cacheMap.containsKey(data)) {
            cacheMap.computeIfPresent(data, (k, v) -> v + 1);
        } else {
            removeLFU();
            cacheMap.put(data, 0);
        }
    }

    public Integer get(CacheEntry data) {
        return cacheMap.computeIfPresent(data, (k, v) -> v + 1);
    }


    private void removeLFU() {
        Integer max = cacheMap.values().stream().max(Comparator.naturalOrder()).get();
        CacheEntry cacheEntry = cacheMap.entrySet().stream().filter(e -> Objects.equals(e.getValue(), max))
                .findFirst().get().getKey();
        cacheMap.remove(cacheEntry);
    }
}
