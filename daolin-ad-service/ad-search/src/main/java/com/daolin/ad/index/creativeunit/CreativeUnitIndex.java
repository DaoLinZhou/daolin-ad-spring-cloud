package com.daolin.ad.index.creativeunit;

import com.daolin.ad.index.IndexAware;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Daolin
 * @date 2019/05/11
 */
@Slf4j
@Component
public class CreativeUnitIndex implements IndexAware<String, CreativeUnitObject> {

    //<adId-unitId, CreativeUnitObject>
    private static Map<String, CreativeUnitObject> objectMap;

    //<adId, unitId Set>
    private static Map<Long, Set<Long>> creativeUnitMap;

    //<unitId, adId Set>
    private static Map<Long, Set<Long>> unitCreativeMap;

    static {
        objectMap = new ConcurrentHashMap<>();
        creativeUnitMap = new ConcurrentHashMap<>();
        unitCreativeMap = new ConcurrentHashMap<>();
    }

    @Override
    public CreativeUnitObject get(String key) {
        return objectMap.get(key);
    }

    @Override
    public void add(String key, CreativeUnitObject value) {
        log.info("before add: {}", objectMap);
        // 更新 objectMap
        objectMap.put(key, value);
        // 更新 creativeUnitMap
        Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
        if(CollectionUtils.isEmpty(unitSet)){
            unitSet = new ConcurrentSkipListSet<>();
            creativeUnitMap.put(value.getAdId(), unitSet);
        }
        unitSet.add(value.getUnitId());
        // 更新 unitCreativeMap
        Set<Long> creativeSet = unitCreativeMap.get(value.getUnitId());
        if(CollectionUtils.isEmpty(creativeSet)){
            creativeSet = new ConcurrentSkipListSet<>();
            unitCreativeMap.put(value.getUnitId(), creativeSet);
        }
        creativeSet.add(value.getAdId());

        log.info("after add: {}", objectMap);
    }

    @Override
    public void update(String key, CreativeUnitObject value) {
        log.error("CreativeUnitIndex not support update");
    }

    @Override
    public void delete(String key, CreativeUnitObject value) {
        log.info("before delete: {}", objectMap);
        // 从 objectMap 中删除
        objectMap.remove(key);
        // 从 creativeUnitMap 中删除
        Set<Long> unitSet = creativeUnitMap.get(value.getAdId());
        if(CollectionUtils.isNotEmpty(unitSet)){
            unitSet.remove(value.getUnitId());
        }
        // 从 unitCreativeMap 中删除
        Set<Long> creativeSet = unitCreativeMap.get(value.getUnitId());
        if(CollectionUtils.isNotEmpty(creativeSet)){
            creativeSet.remove(value.getAdId());
        }
        log.info("after delete: {}", objectMap);
    }
}
