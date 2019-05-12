package com.daolin.ad.index;

/**
 * @author Daolin
 * @date 2019/05/06
 */
public interface IndexAware<K, V> {

    V get(K key);

    void add(K key, V value);

    void update(K key, V value);

    void delete(K key, V value);
}
