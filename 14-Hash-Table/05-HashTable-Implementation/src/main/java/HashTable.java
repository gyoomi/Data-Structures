/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

import java.util.TreeMap;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/12/26 14:42
 */
public class HashTable<K, V> {

    private TreeMap<K, V>[] hashtable;
    private int size;
    private int M;

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(97);
    }

    public int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void add(K key, V value) {
        TreeMap<K, V> treeMap = hashtable[hash(key)];
        if (treeMap.containsKey(key)) {
            treeMap.put(key, value);
        } else {
            treeMap.put(key,value);
            size++;
        }
    }


    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.remove(key);
            size--;
        }
        return ret;
    }

    public void set(K key,V value) {
        TreeMap<K, V> map = hashtable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        map.put(key,value);
    }

    public boolean contains(K key) {
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashtable[hash(key)].get(key);
    }




}
