package org.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

import java.util.concurrent.TimeUnit;

public class CacheGuava {

    public static Cache<String, CacheBean> cache = CacheBuilder.newBuilder()
            .concurrencyLevel(2)
            .removalListener(getRemovalListener())
            .maximumSize(100_000)
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();


    private static RemovalListener<String, CacheBean> getRemovalListener() {
        return new RemovalListener<String, CacheBean>() {
            @Override
            public void onRemoval(RemovalNotification<String, CacheBean> removalNotification) {

            }
        };
    }


}