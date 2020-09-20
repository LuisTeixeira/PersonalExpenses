package com.lmteixeira.personalfinances.hazelcastrepo;

import com.hazelcast.core.HazelcastInstance;

public class Hazelcast {

    private static final Object LOCK = new Object();
    private static HazelcastInstance HAZELCAST;

    public static HazelcastInstance getInstance() {
        if (HAZELCAST == null) {
            synchronized (LOCK) {
                if (HAZELCAST == null) {
                    HAZELCAST = com.hazelcast.core.Hazelcast.newHazelcastInstance();
                }
            }
        }
        return HAZELCAST;
    }

    private Hazelcast() {

    }

}
