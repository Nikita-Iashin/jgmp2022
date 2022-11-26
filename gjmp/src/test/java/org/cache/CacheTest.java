package org.cache;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest {

    @Test
    void removeObjectTestWhenMaxSizeIsNotReached() {
        Cache<String, String> cache = new Cache<>(200, 500, 6);
        cache.put("test1", "test1");
        cache.put("test2", "test2");

        int initialCacheSize = cache.size();
        cache.remove("test1");
        int sizeAfterRemove = cache.size();
        assertTrue(initialCacheSize > sizeAfterRemove,
                String.format("Initial size was %s, after a removal of one item the size become %s",
                        initialCacheSize, sizeAfterRemove));
    }

    @Test
    void checkAutoRemovalWhenReachedMaxSizeTest() {
        Cache<String, String> cache = new Cache<>(200, 500, 6);
        cache.put("test1", "test1");
        cache.put("test2", "test2");
        cache.put("test3", "test3");
        cache.put("test4", "test4");
        cache.put("test5", "test5");
        cache.put("test6", "test6");

        int initialCacheSize = cache.size();
        cache.put("test7", "test7");
        int sizeAfterRemove = cache.size();
        assertEquals(initialCacheSize, sizeAfterRemove,
                String.format("Initial size was %s, after a addition of one item the size stay %s",
                        initialCacheSize, sizeAfterRemove));
    }

    @Test
    @SneakyThrows
    void expiredCacheObjectsTest() {
        int timeToLive = 1;
        Cache<String, String> cache = new Cache<>(timeToLive, 1, 10);
        cache.put("test1", "test1");
        cache.put("test2", "test2");

        Thread.sleep(3000);

        assertEquals(0, cache.size(),
                String.format("Cache expected to be empty after $d, but was %d", timeToLive, cache.size()));
    }

    @Test
    @SneakyThrows
    void objectsCleanupTimeTest() {
        int size = 100_000;

        Cache<String, String> cache = new Cache<>(100, 100, size);
        populateCache(cache);
        Thread.sleep(200);

        long start = System.currentTimeMillis();
        cache.CleanUp();
        double finish = (double) (System.currentTimeMillis() - start) / 1000.0;

        int expectedTimeToCleanInSeconds = 2;
        assertTrue(finish < 2,
                String.format("Clean up time for cache with size %d expected to be %d, but was %f",
                        size, expectedTimeToCleanInSeconds, finish));
    }

    private void populateCache(Cache<String, String> cache) {
        for (int i = 0; i < cache.size(); i++) {
            String value = Integer.toString(i);
            cache.put(value, value);
        }
    }
}
