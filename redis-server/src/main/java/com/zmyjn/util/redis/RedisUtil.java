package com.zmyjn.util.redis;

import com.alibaba.druid.support.json.JSONUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.*;

public class RedisUtil {

    //Redis服务器IP
    private static String ADDR = "127.0.0.1";

    //Redis的端口号
    private static int PORT = 6379;

    //访问密码
    private static String AUTH;

    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 1024;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 10000;

    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;


    /**
     * 初始化Redis连接池
     */
    static {
        try {
            // 1.设置连接池的配置对象
            JedisPoolConfig config = new JedisPoolConfig();
            // 设置连接池的最大连接数【可选】
            config.setMaxTotal(50);
            config.setMaxIdle(MAX_IDLE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            // 2.设置连接池对象
            jedisPool = new JedisPool(config, ADDR, PORT, TIMEOUT, AUTH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null) {
            jedisPool.returnResource(jedis);
        }
    }


    /**
     * 将redis存放数据的方式做进一步封装，使其更加适合java数据的存放 此方法将javaBean直接存储为一个字符串
     *
     * @param index     redis库
     * @param key        存储的名字
     * @param javaBean   要存储的对象
     * @param activeTime 该对象的有效时间，单位为秒
     */
    public static Boolean setBean(int index,String key, Object javaBean, Integer activeTime) {
        /**
         * 1.获取jedis对象 2.将对象转成json数据存入redis缓存 3.释放资源
         */
        if (javaBean != null && key != null && key != "") {
            Jedis jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(key, JSONObject.fromObject(javaBean).toString());
            jedis.setDataSource(jedisPool);
            if (activeTime != null && activeTime > 0) {
                jedis.expire(key, activeTime);
            }
            jedis.close();
            return true;
        }
        return false;
    }

    /**
     * 此方法将会把存在redis中的数据取出来，并封装成相应的JavaBean
     *
     * @param index     redis库
     * @param key       存储的名字
     * @param beanClass 要封装成为的javaBean
     * @return javaBean
     */
    public static Object getBean(int index,String key, Class beanClass) {
        if (key != null && key != "" && beanClass != null) {
            Jedis jedis = jedisPool.getResource();
            jedis.select(index);
            Object object = JSONObject.toBean(JSONObject.fromObject(jedis.get(key)), beanClass);
            jedis.close();
            return object;
        }
        return null;
    }

    /**
     * 此方法将ArrayList集合直接存储为一个字符串
     *
     * @param index     redis库
     * @param key        存储的名字
     * @param list       要存储的集合对象
     * @param activeTime 该对象的有效时间，单位为秒
     */
    public static Boolean setArrayList(int index,String key, List<Object> list,
                                       Integer activeTime) {
        if (list != null && key != null && key != "") {
            Jedis jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(key, JSONArray.fromObject(list).toString());
            if (activeTime != null && activeTime > 0) {
                jedis.expire(key, activeTime);
            }
            jedis.close();
            return true;
        }
        return false;
    }

    /**
     * 此方法将会把存在redis中的数据取出来，并封装成相应的Arraylist集合
     *
     * @param index     redis库
     * @param key       存储的名字
     * @param beanClass 要封装成为的javaBean
     * @return List
     */
    public static List<Object> getArraylist(int index,String key, Class beanClass) {
        if (key != null && key != "" && beanClass != null) {
            Jedis jedis = jedisPool.getResource();
            jedis.select(index);
            JSONArray jsonArray = JSONArray.fromObject(jedis.get(key));
            List<Object> list = new ArrayList<Object>();
            for (int i = 0; i < jsonArray.size(); i++) {
                Object object = JSONObject.toBean(
                        (JSONObject) jsonArray.get(i), beanClass);
                list.add(object);
            }
            return list;
        }
        return null;
    }


    /**
     * 此方法将Map集合直接存储为一个字符串
     *
     * @param index     redis库
     * @param key        存储的名字
     * @param map        要存储的Map集合对象
     * @param activeTime 该对象的有效时间，单位为秒
     * @return 成功返回true, 失败返回false
     */
    public static boolean setMap(int index,String key, Map<String, Object> map,
                                 Integer activeTime) {
        if (map != null && key != null && key != "") {
            Jedis jedis = jedisPool.getResource();
            jedis.select(index);
            jedis.set(key, JSONObject.fromObject(map).toString());
            if (activeTime != null && activeTime > 0) {
                jedis.expire(key, activeTime);
            }
            jedis.close();
            return true;
        }
        return false;
    }

    /**
     * 此方法将会把存在redis中的数据取出来，并封装成相应的Map集合
     *
     * @param key       存储的名字
     * @param beanClass 要封装成的对象
     * @return 返回封装后的map集合
     */
    public static Map<String, Object> getMap(int index,String key, Class beanClass) {
        if (key != null && key != "" && beanClass != null) {
            Jedis jedis = jedisPool.getResource();
            jedis.select(index);
            Map map1 = (Map) JSONObject.fromObject(jedis.get(key));
            Set set = map1.keySet();
            Iterator ite = set.iterator();
            Map<String, Object> maps = new HashMap<String, Object>();
            while (ite.hasNext()) {
                String key1 = (String) ite.next();
                JSONObject jsonObject = JSONObject.fromObject(map1.get(key1));
                Object object = JSONObject.toBean(jsonObject, beanClass);
                maps.put(key1, object);
            }
            return maps;
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println(RedisUtil.setBean(2,"2","{\"2\":2}",null));
    }

}
