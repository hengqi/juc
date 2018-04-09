package com.chenhl.juc.concurrenthashmap;

import java.util.Hashtable;

/**
 * key, value 都不能为空
 */
public class HashtableTest {

    public static void main(String[] args) {
        Hashtable table = new Hashtable();
        table.put(null, "hello");

    }
}
