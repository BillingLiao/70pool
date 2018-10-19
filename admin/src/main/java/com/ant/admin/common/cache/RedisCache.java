package com.ant.admin.common.cache;

import com.ant.admin.common.utils.JedisUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author zhaopinchao
 * @date 2018-7-31 19:52
 */
@Component
public class RedisCache<K, V> implements Cache<K, V> {

    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);

    private final String CACHE_PREFIX = "redis-cache:";

    private JedisUtil jedisUtil = new JedisUtil();

    private byte[] getKey(K k){
        if(k instanceof String){
            return (CACHE_PREFIX + k).getBytes();
        }
        return SerializationUtils.serialize(k);
    }

    @Override
    public V get(K k) throws CacheException {
        logger.info("从redis缓存中获取权限");
        byte[] value = jedisUtil.get(getKey(k));
        if(value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public V put(K k, V v) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = SerializationUtils.serialize(v);
        jedisUtil.set(key,value,43200);
        return v;
    }

    @Override
    public V remove(K k) throws CacheException {
        byte[] key = getKey(k);
        byte[] value = jedisUtil.get(key);
        jedisUtil.del(key);
        if(value != null){
            return (V) SerializationUtils.deserialize(value);
        }
        return null;
    }

    @Override
    public void clear() throws CacheException {
        //不进行重写
    }

    @Override
    public int size() {
        return keys().size();
    }

    @Override
    public Set<K> keys() {
        return (Set<K>) jedisUtil.keys(CACHE_PREFIX);
    }

    @Override
    public Collection<V> values() {
        Set<V> values = new HashSet<V>();
        Set<byte[]> keys = jedisUtil.keys(CACHE_PREFIX);
        if (CollectionUtils.isEmpty(keys)){
            return values;
        }
        for (byte[] key : keys) {
            values.add((V) SerializationUtils.deserialize(jedisUtil.get(key)));
        }
        return values;
    }
}
