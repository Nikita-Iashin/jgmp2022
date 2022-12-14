package org.cache;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;

import java.util.ArrayList;

public class CacheJava<K, T> {
    private final long timeToLive;
    private final LRUMap cacheMap;

    public CacheJava(long timeToLive, final long timerInterval, int maxItems) {
        this.timeToLive = timeToLive * 1000;
        cacheMap = new LRUMap(maxItems);
        if (this.timeToLive > 0 && timerInterval > 0) {
            Thread t = new Thread(() -> {
                while (true) {
                    try {
                        Thread.sleep(timerInterval * 1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    CleanUp();
                }
            });
            t.setDaemon(true);
            t.start();
        }
    }

    public void put(K key, T value) {
        synchronized (cacheMap) {
            cacheMap.put(key, new CacheObject(value));
        }
    }

    public T get(K key) {
        synchronized (cacheMap) {
            CacheObject c;
            c = (CacheObject) cacheMap.get(key);
            if (c == null)
                return null;
            else {
                c.lastAccessed = System.currentTimeMillis();
                return c.value;
            }
        }
    }

    public void remove(K key) {
        synchronized (cacheMap) {
            cacheMap.remove(key);
        }
    }

    public int size() {
        synchronized (cacheMap) {
            return cacheMap.size();
        }
    }

    public void CleanUp() {
        long now = System.currentTimeMillis();
        ArrayList<K> deleteKey;
        synchronized (cacheMap) {
            MapIterator itr = cacheMap.mapIterator();
            deleteKey = new ArrayList<K>((cacheMap.size() / 2) + 1);
            K key;
            CacheObject c;
            while (itr.hasNext()) {
                key = (K) itr.next();
                c = (CacheObject) itr.getValue();
                if (c != null && (now > (timeToLive + c.lastAccessed))) {
                    deleteKey.add(key);
                }
            }
        }
        for (K key : deleteKey) {
            synchronized (cacheMap) {
                cacheMap.remove(key);
            }
            Thread.yield();
        }
    }

    protected class CacheObject {
        public long lastAccessed = System.currentTimeMillis();
        public T value;

        protected CacheObject(T value) {
            this.value = value;
        }
    }
}
