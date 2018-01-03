package com.linkapp.hlc.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.log4j.Logger;

import com.linkapp.hlc.util.StringUtil;
 


public class BizMailTest {
	
	private static final Logger log=Logger.getLogger(BizMailTest.class);
	
	public static void main(String[] args) throws Exception {
		String  URL ="http://127.0.0.1/commdisk/bizMailSave.action?apikey=9b11127a9701975c734b8aee81ee3526&usid=c3284d0f94606de1fd2af172aba15bf3-fe1b9a223ba095ff-admin";
		Map<String,Object> json=new HashMap<String,Object>();
		json.put("unid", StringUtil.URLEncoder("2121333asdad"));
		json.put("moduleName", StringUtil.URLEncoder("公文"));
		json.put("bizid", StringUtil.URLEncoder("a0cafa3f-517f-4f0f-a3a3-a486aac9cbde"));
		json.put("annexCount", StringUtil.URLEncoder("4"));
		json.put("summary", StringUtil.URLEncoder("fewfsda66666"));
		json.put("docLinkurl", StringUtil.URLEncoder("http://www.baidu.com"));
		json.put("title", StringUtil.URLEncoder("好发432z#"));
		
		json.put("createTime", StringUtil.URLEncoder(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
		json.put("viewer", StringUtil.URLEncoder("{\"group\":\"124,hlc123\",\"dept\":\"技术部,研发部\",\"user\":\"guest,hlc\"}"));
		json.put("createUser", StringUtil.URLEncoder("zhangsan"));
		json.put("createUserid", StringUtil.URLEncoder("34"));
		json.put("createDepartment", StringUtil.URLEncoder("guoshui"));
		json.put("createDepartmentid", StringUtil.URLEncoder("435"));
		json.put("content",StringUtil.URLEncoder( "asd<img src=\"http://www.baidu.com/img/baidu_sylogo1.gif\"/>sd<img src=\"https://123p1.sogoucdn.com/imgu/2017/12/20171219184713_603.png\"/>ijijisad<img src=\"https://123p2.sogoucdn.com/imgu/2017/12/20171222185023_198.png\"/><font color=\"red\">841236f阿发</font><span style=\"color:red\">assssssssssss</span>"));
	
		
		sendPost(URL,json);
		
	}
	
	public static String sendPost(String url, Map<String,Object> propsMap)  throws Exception{
		

        HttpClient httpClient = new HttpClient();
        
        String responseMsg="";
        PostMethod postMethod = new PostMethod(url);// POST请求
        if (propsMap != null) {
            // 参数设置
            Set<String> keySet = propsMap.keySet();
            NameValuePair[] postData = new NameValuePair[keySet.size()];
            int index = 0;
            for (String key : keySet) {
                postData[index++] = new NameValuePair(key, propsMap.get(key)
                        .toString());
            }
            postMethod.addParameters(postData);
        }
        postMethod.getParams().setParameter(
                HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        try {
            int statusCode = httpClient.executeMethod(postMethod);// 发送请求        
            if (statusCode == HttpStatus.SC_OK) {
                // 读取内容
                byte[] responseBody = postMethod.getResponseBody();
                // 处理返回的内容
                responseMsg = new String(responseBody, "utf-8");
                log.info("返回的数据="+responseMsg);
            }
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            postMethod.releaseConnection();// 关闭连接
        }
       
        return responseMsg;
	}
	
	public static String sendPost2(String url, String param)  throws Exception{
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
	        log.error("HTTP请求路径时错误：" + url, e);
	       
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
