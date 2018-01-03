package com.linkapp.hlc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Redis 工具类
 * 
 * 
 */
public class JedisUtil {

    protected static Logger logger = Logger.getLogger(JedisUtil.class);

    //Redis服务器IP
    private static String ADDR_ARRAY = "192.168.0.101:6379";

    //访问密码
    private static String AUTH =null;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private static int MAX_ACTIVE = 8;

    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 8;

    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private static int MAX_WAIT = 3000;

    //超时时间
    private static int TIMEOUT = 10000;

    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = false;

    private static JedisPool jedisPool = null;

    /**
     * redis过期时间,以秒为单位
     */
    public final static int EXRP_HOUR = 60 * 60;			//一小时
    public final static int EXRP_DAY = 60 * 60 * 24;		//一天
    public final static int EXRP_MONTH = 60 * 60 * 24 * 30;	//一个月

    /**
     * 初始化Redis连接池
     */
    private static void initialPool() {
        try {
    		ClassLoader cl = Thread.currentThread().getContextClassLoader();
    		InputStream infile = cl.getResourceAsStream("redis.properties");
    		Properties props = new Properties();
    		Integer PORT=null;
    		try {
    			props.load(infile);
    			ADDR_ARRAY =props.getProperty("redis.serverlist", "127.0.0.1");
    			String service1=ADDR_ARRAY.split(",")[0];
    			String servicAdd=service1.split(":")[0];
    			PORT=Integer.parseInt(service1.split(":")[1]);
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(MAX_ACTIVE);
                config.setMaxIdle(MAX_IDLE);
                config.setMaxWaitMillis(MAX_WAIT);
                config.setTestOnBorrow(TEST_ON_BORROW);
               jedisPool = new JedisPool(config, servicAdd, PORT, TIMEOUT, AUTH);               

               
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    		finally {
    			if(null != infile)
    			{
    				try {
    					infile.close();
    				} catch (IOException e) {
    					e.printStackTrace();
    				}
    			}
            }

        } catch (Exception e) {
            logger.error("First create JedisPool error : " + e);
           try {
                //如果第一个IP异常，则访问第二个IP
                JedisPoolConfig config = new JedisPoolConfig();
                config.setMaxTotal(MAX_ACTIVE);
                config.setMaxIdle(MAX_IDLE);
                config.setMaxWaitMillis(MAX_WAIT);
                config.setTestOnBorrow(TEST_ON_BORROW);
                
    			String service2=ADDR_ARRAY.split(",")[1];
    			String servicAdd=service2.split(":")[0];
    			Integer PORT=Integer.parseInt(service2.split(":")[1]);
                jedisPool = new JedisPool(config, servicAdd, PORT, TIMEOUT, AUTH);
                
            } catch (Exception e2) {
                logger.error("Second create JedisPool error : " + e2);
            }
        }
        
       
    }

    /**
     * 在多线程环境同步初始化
     */
    private static synchronized void poolInit() {
    	if (jedisPool == null) {  
            initialPool();
        }
    }

    
    /**
     * 同步获取Jedis实例
     * @return Jedis
     */
    public synchronized static Jedis getJedis() {  
        if (jedisPool == null) {  
        	poolInit();
        }
        Jedis jedis = null;
        try {  
            if (jedisPool != null) {  
            	jedis = jedisPool.getResource(); 
            }
        } catch (Exception e) {  
        	logger.error("Get jedis error : "+e);
        }finally{
        	returnResource(jedis);
        }
        return jedis;
    }  

    /**
     * 释放jedis资源
     *
     * @param jedis
     */
    public static void returnResource(final Jedis jedis) {
        if (jedis != null && jedisPool != null) {
            jedisPool.returnResource(jedis);
        }
    }

    /**
     * 设置 String
     *
     * @param key
     * @param value
     */
    public synchronized static void setString(String key, String value) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            getJedis().set(key, value);
        } catch (Exception e) {
            logger.error("Set key error : " + e);
        }
    }

    /**
     * 设置 过期时间
     *
     * @param key
     * @param seconds 以秒为单位
     * @param value
     */
    public synchronized static void setString(String key, int seconds, String value) {
        try {
            value = StringUtils.isEmpty(value) ? "" : value;
            getJedis().setex(key, seconds, value);
        } catch (Exception e) {
            logger.error("Set keyex error : " + e);
        }
    }

    /**
     * 获取String值
     *
     * @param key
     * @return value
     */
    public synchronized static String getString(String key) {
    	boolean falg=false;
		try {
			falg = getJedis().exists(key);
		} catch (Exception e) {
			 logger.error("existes error : " + e);			
		}
        if (getJedis() == null || !falg) {
            return null;
        }
        return getJedis().get(key);
    }
    
    public synchronized static void setObject(String key,Object value)
    {
        try {
            value = value==null? "" : value;          
            getJedis().set(key.getBytes(), SerializeUtil.serialize(value));
        } catch (Exception e) {
            logger.error("Set keyex error : " + e);
        }
    }
    public synchronized static Object getObject(String key)
    {
    	boolean falg=false;
		try {
			falg = getJedis().exists(key.getBytes());
		} catch (Exception e) {
			 logger.error("existes error : " + e);			
		}
        if (getJedis() == null || !falg) {
            return null;
        }
    	return SerializeUtil.unserialize(getJedis().get(key.getBytes()));	
    }
    
   
    
    
}