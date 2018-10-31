/**
 * Copyright © 2018, TaoDing
 * <p>
 * All Rights Reserved.
 */

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2018/10/31 15:29
 */
public interface Map<K, V> {

    void add(K key, V value);
    V get(K key);
    boolean contains(K key);
    void set(K key, V newValue);
    int getSize();
    boolean isEmpty();
    V remove(K key);
}
