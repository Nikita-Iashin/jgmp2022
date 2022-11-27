package org;

import com.google.common.cache.Cache;
import lombok.SneakyThrows;
import org.cache.CacheBean;
import org.cache.CacheGuava;
import org.cache.CacheJava;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CacheTest {

    @Test
    @SneakyThrows
    void guavaCacheTest() {
        Cache<String, CacheBean> cache = CacheGuava.cache;

        cache.put("test1", new CacheBean("test1"));
        cache.put("test2", new CacheBean("test2"));

        Long initialCacheSize = cache.size();

        cache.invalidate("test1");

        Long sizeAfterRemove = cache.size();
        assertTrue(initialCacheSize > sizeAfterRemove,
                String.format("Initial size was %s, after a removal of one item the size become %s",
                        initialCacheSize, sizeAfterRemove));
    }

    @Test
    void removeObjectTestWhenMaxSizeIsNotReached() {
        CacheJava<String, String> cache = new CacheJava<>(200, 500, 6);
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
        CacheJava<String, String> cache = new CacheJava<>(200, 500, 6);
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
        CacheJava<String, String> cache = new CacheJava<>(timeToLive, 1, 10);
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

        CacheJava<String, String> cache = new CacheJava<>(100, 100, size);
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

    private void populateCache(CacheJava<String, String> cache) {
        for (int i = 0; i < cache.size(); i++) {
            String value = Integer.toString(i);
            cache.put(value, value);
        }
    }
}
