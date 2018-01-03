/**
 * 
 */
package com.linkapp.hlc.test;

import com.linkapp.hlc.util.JedisUtil;

import redis.clients.jedis.Jedis;

/** 
 * @ClassName:     JedisTest 
 * @Description:   TODO
 * @author:        HongLC 
 * @date:          2017年12月28日 下午3:43:44 
 *  
 */
public class JedisTest {
	
	public static void main(String[] args) {
		

	    	Jedis j=JedisUtil.getJedis();
			
			//JedisUtil.setObject("namee", "hlc反对法");
			
			String res=JedisUtil.getObject("name2").toString();
			System.out.println(res);
		
	}
}
