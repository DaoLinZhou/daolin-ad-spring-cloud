package com.daolin.ad.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author Daolin
 * @date 2019/05/09
 */
public class CommonUtils {

    public static <K, V> V getOrCreate(K key, Map<K, V> map,
                                       Supplier<V> factory){
        return map.computeIfAbsent(key, k -> factory.get());
    }

}
