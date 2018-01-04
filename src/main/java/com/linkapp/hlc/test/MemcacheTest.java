/**
 * 
 */
package com.linkapp.hlc.test;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.linkapp.hlc.util.MemcachedUtils;

/** 
 * @ClassName:     MemcacheTest 
 * @Description:   TODO
 * @author:        HongLC 
 * @date:          2018年1月4日 下午4:42:21 
 *  
 */
public class MemcacheTest {

	 /** 
     * 测试MemcachedUtils类的set方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testSet1() {  
        MemcachedUtils.set("set1Description", "调用MemcachedUtils类的set方法，没有设置键值对的存在时长");  
        System.out.println(MemcachedUtils.get("set1Description").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的set方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testSet2() {  
        MemcachedUtils.set("set2Description", "调用MemcachedUtils类的set方法，设置了键值对的存在时长——存在60秒", new Date(1000*60));  
        System.out.println(MemcachedUtils.get("set2Description").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的add方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testAdd1() {  
        MemcachedUtils.add("add1Description", "调用MemcachedUtils类的add方法，没有设置键值对的存在时长");  
        System.out.println(MemcachedUtils.get("add1Description").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的add方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testAdd2() {  
        MemcachedUtils.add("add2Description", "调用MemcachedUtils类的add方法，设置了键值对的存在时长——存在60秒", new Date(1000*60));  
        System.out.println(MemcachedUtils.get("add2Description").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的replace方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testReplace1() {  
        MemcachedUtils.add("replace1Description", "调用MemcachedUtils类的replace方法，没有设置键值对的存在时长");  
        MemcachedUtils.replace("replace1Description", "值改变了！！！");  
        System.out.println(MemcachedUtils.get("replace1Description").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的replace方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testReplace2() {  
        MemcachedUtils.add("replace2Description", "调用MemcachedUtils类的replace方法，设置了键值对的存在时长——存在60秒", new Date(1000*60));  
        MemcachedUtils.replace("replace2Description", "值改变了！！！", new Date(1000*60));  
        System.out.println(MemcachedUtils.get("replace2Description").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的get方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testGet() {  
        MemcachedUtils.add("getDescription", "调用MemcachedUtils类的get方法，没有设置键值对的存在时长");  
        System.out.println(MemcachedUtils.get("getDescription").toString());  
    }  
  
    /** 
     * 测试MemcachedUtils类的delete方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testDelete1() {  
        MemcachedUtils.add("delete1Description", "调用MemcachedUtils类的delete方法，没有设置键值对的逾期时长");  
        MemcachedUtils.delete("delete1Description");  
        Assert.assertEquals(null, MemcachedUtils.get("delete1Description"));  
    }  
  
    /** 
     * 测试MemcachedUtils类的delete方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testDelete2() {  
        MemcachedUtils.set("delete2Description1", "调用MemcachedUtils类的delete方法，设置键值对的逾期时长", new Date(600*1000));  
        MemcachedUtils.delete("delete2Description1", new Date(1000*600));  
        Assert.assertEquals(null, MemcachedUtils.get("delete2Description1"));  
    }  
  
    /** 
     * 测试MemcachedUtils类的flashAll方法。 
     *  
     * @author HongLC 
     */  
	@Test
    public  void testFlashAll() {  
        MemcachedUtils.add("flashAllDescription", "调用MemcachedUtils类的delete方法，没有设置键值对的预期时长");  
        MemcachedUtils.flashAll();  
        Assert.assertEquals(null, MemcachedUtils.get("flashAllDescription"));  
    }  
}
