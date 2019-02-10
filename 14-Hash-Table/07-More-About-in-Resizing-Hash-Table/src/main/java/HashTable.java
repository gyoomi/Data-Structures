/**
 * Copyright © 2018, LeonKeh
 * <p>
 * All Rights Reserved.
 */

import java.util.Map;
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

    private static int upperTol = 10; // 上界
    private static int lowerTol = 2;  // 下界
    private static int initCapacity = 7;

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(initCapacity);
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
            if (size >= upperTol * M) {
                resize(2 * M);
            }
        }
    }


    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashtable[hash(key)];
        if (map.containsKey(key)) {
            map.remove(key);
            size--;
            if (size < M * lowerTol && M / 2 > initCapacity) {
                resize(M / 2);
            }
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


    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = this.M;
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashtable[i];
            for (Map.Entry<K, V> kvEntry : map.entrySet()) {
                newHashTable[hash(kvEntry.getKey())].put(kvEntry.getKey(), kvEntry.getValue());
            }
        }
        this.hashtable = newHashTable;
    }

}
