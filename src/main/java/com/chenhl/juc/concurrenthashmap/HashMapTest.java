package com.chenhl.juc.concurrenthashmap;

import java.util.HashMap;

public class HashMapTest {

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.put(null, null);
        map.put(null, null);
        System.out.println(map);

    }
}
