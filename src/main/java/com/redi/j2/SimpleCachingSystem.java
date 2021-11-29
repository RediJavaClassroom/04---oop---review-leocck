package com.redi.j2;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class SimpleCachingSystem {

    private Map<String, Object> cache;
    private Map<String, LocalDateTime> expirationDate;

    private static SimpleCachingSystem instance;
    public static SimpleCachingSystem getInstance() {
        if (instance == null) {
            instance = new SimpleCachingSystem();
        }
        return instance;
    }

    private SimpleCachingSystem() {

        cache = new HashMap<>();
        expirationDate = new HashMap<>();
    }

    public Object getFromCache(String key) {
        // lazy check of expiration date
        if(expirationDate.containsKey(key) &&
            !expirationDate.get(key).isAfter(LocalDateTime.now())) {
            // invalidate cache
            System.out.println("Key expired: "+key);
            cache.remove(key);
            expirationDate.remove(key);
            return null;
        }
        return cache.get(key);
    }

    public void addToCache(String key, Object value, int ttl) {
        cache.put(key, value);
        expirationDate.put(key, LocalDateTime.now().plusSeconds(ttl));
    }

    public void invalidateKey(String key) {
        cache.remove(key);
        expirationDate.remove(key);
    }
}
