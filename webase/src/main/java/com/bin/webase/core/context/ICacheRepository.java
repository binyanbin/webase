package com.bin.webase.core.context;


/**
 * @author yanbin
 * @date 2017/7/1
 */
public interface ICacheRepository {

    /**
     * 获得缓存值
     *
     * @param key 关键字
     * @return 值
     */
    String get(String key);

    /**
     * 设置缓存值
     *
     * @param key     关键字
     * @param value   值
     * @param expires 过期时间
     */
    void set(String key, String value, long expires);

    /**
     * 设置缓存值 不过期
     *
     * @param key   关键字
     * @param value 值
     */
    void set(String key, String value);


    /**
     * 删除缓存
     *
     * @param key 关键字
     */
    void delete(String key);


    /**
     * 判断是否有缓存
     *
     * @param key 关键字
     * @return 是否存在
     */
    boolean hasKey(String key);

    /**
     * 自增
     *
     * @param key 关键字
     * @param expires 过期时间
     * @return
     */
    long increment(String key, long expires);

}
