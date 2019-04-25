package com.lk.tools.jedis.springredis;


import com.lk.tools.jedis.JedisException;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luokun
 * @date 2019/4/22 10:53
 */
@Service("RedisSpringUtils")
public class RedisSpringUtils {

    @Resource
    private RedisTemplate<String, String> redisTemplate;


    /**
     * type：Hash：
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * @author eric
     * @date 2017年7月5日下午6:26:07
     * @param indexDB：指定的数据库索引，0到11之间
     * @param key：需要获得的hash表key
     * @param fieldsMap：字段与对应值
     * @return Boolean ： true：成功
     *                  false：失败
     * @throws JedisException 执行失败
     */
    public Boolean hmset(int indexDB, String key,
                         Map<String, String> fieldsMap)throws JedisException{
        Map<byte[], byte[]> map = new HashMap<byte[], byte[]>();
        for (String s : fieldsMap.keySet()) {
            map.put(SafeEncoder.encode(s), SafeEncoder.encode(fieldsMap.get(s)));
        }
        return this.hmset(indexDB, SafeEncoder.encode(key), map);
    }

    /**
     * type：Hash：
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中。
     * @author eric
     * @date 2017年7月5日下午6:24:14
     * @param indexDB：指定的数据库索引，0到11之间
     * @param key：需要获得的hash表key
     * @param fieldsMap：字段与对应值
     * @return Boolean ： true：成功
     *                  false：失败
     * @throws JedisException 执行失败
     */
    public Boolean hmset(int indexDB, byte[] key,
                         Map<byte[], byte[]> fieldsMap)throws JedisException{
        try {
            return redisTemplate.execute(new RedisCallback<Boolean>() {
                @Override
                public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.select(indexDB);
                    connection.hMSet(key, fieldsMap);
                    return true;
                }
            });
        } catch (Exception e) {
            throw new JedisException(e);
        }
    }




    /**
     * type:Hash
     * 获取存储在哈希表中指定字段的值。
     * @param indexDB：指定的数据库索引，0到11之间 。
     * @param key：需要获得的hash表key。
     * @param member：需要获得的hash表字段名。
     * @return 获得的hash字段的值。
     * @throws JedisException 执行失败
     */
    public String hget(int indexDB, String key, String member)throws JedisException{
        return SafeEncoder.encode(this.hget(indexDB,SafeEncoder.encode(key),SafeEncoder.encode(member)));

    }

    /**
     * type:Hash
     * 获取存储在哈希表中指定字段的值。
     * @author eric
     * @date 2017年7月5日下午5:48:33
     * @param indexDB：指定的数据库索引，0到11之间 。
     * @param key：需要获得的hash表key。
     * @param member：需要获得的hash表字段名。
     * @return 获得的hash字段的值。
     * @throws JedisException 执行失败
     */
    public byte[] hget(int indexDB, byte[] key, byte[] member)throws JedisException{
        try {
            return redisTemplate.execute(new RedisCallback<byte[]>() {
                @Override
                public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                    connection.select(indexDB);
                    return connection.hGet(key, member);
                }
            });
        } catch (Exception e) {
            throw new JedisException(e);
        }
    }


}
