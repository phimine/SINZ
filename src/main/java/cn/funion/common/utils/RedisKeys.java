package cn.funion.common.utils;

/**
 * Redis所有Keys
 *
 * @author 4union
 * @email dev@4union.cn
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

    public static String getSysConfigKey(String key){
        return "sys:config:" + key;
    }
}
