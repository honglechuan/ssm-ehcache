/**
 * 
 */
package com.linkapp.hlc.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

/** 
 * @ClassName:     CacheTest 
 * @Description:   TODO
 * @author:        HongLC 
 * @date:          2018年1月3日 下午1:48:24 
 *  
 */
public class CacheTest {
	public static void main(String[] args) {
		String url="http://127.0.0.1:8080/com.linkapp.test/db/getConfig.do";//ehcache
		String url2="http://127.0.0.1:8080/com.linkapp.test/db/saveConfig.do";//ehcache
		String url3="http://127.0.0.1:8080/com.linkapp.test/db/getConfigs.do";//redis
		try {
			String res=sendPost(url3,"");
			//System.out.println(res);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static String sendPost(String url, String param)  throws Exception{
	    PrintWriter out = null;
	    BufferedReader in = null;
	    String result = "";
	    try {
	        URL realUrl = new URL(url);
	        URLConnection conn = realUrl.openConnection();
	        conn.setConnectTimeout(5000);
	        conn.setReadTimeout(10*1000);
	        conn.setDoOutput(true); // 发送POST请求必须设置如下两行
	        conn.setDoInput(true);
	        out = new PrintWriter(conn.getOutputStream());
	        out.print(param);
	        out.flush();
	        in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	        String line;
	        while ((line = in.readLine()) != null) {
	            result += line;
	        }
	    } catch (Exception e) {
	        //logger.error("HTTP请求路径时错误：" + url, e);
	       
	        throw e; // 异常外抛
	    } finally{
	        try{
	            if(out!=null)out.close();
	            if(in!=null) in.close();
	        }
	        catch(Exception ex){
	        }
	    }
	    return result;
	}
}
