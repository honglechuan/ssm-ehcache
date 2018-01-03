/**
 * 
 */
package com.linkapp.hlc.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.linkapp.hlc.test.BizMailTest;

/** 
 * @ClassName:     StringUtil 
 * @Description:   TODO
 * @author:        HongLC 
 * @date:          2017年12月28日 下午2:35:51 
 *  
 */
public class StringUtil {
	
	private static final Logger logger=Logger.getLogger(StringUtil.class);
	
	public static String URLEncoder(String str) {
		String newStr = str;//先等于原值，防止下面出异常时为空情况
		if (null != str && !"".equals(str)) {
			try {
				newStr = URLEncoder.encode(str, "UTF-8");
				if(newStr.indexOf("+")>-1)
				{
					newStr = StringUtils.replace(newStr, "+", "%20");//处理空格编码过后生成的“+”不能再解码回来的情况
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("URLEncoder转码失败,str="+str+"; "+e.getMessage());
			}
		}
		return newStr;
	}
	
	public static String URLDecoder(String str){
		/*
		 1、对于"a-z", "A-Z", "0-9", ".", "-", "*", "_"，encode/decode前后不产生任何变化，所以实际上无需判断；
		2、" "被转换成"+"，如果原字符串本来就含有"+"，上述方法无效;
		3、其他的字符，根据不同的字符集先被转换成一到多个byte，然后每个byte被表示成类似"%xy"的字符串，其中xy是该byte值的16进制表示形式。所以对于原字符串本来含有"%"或者"%xy"，上述方法也无效，对于"%xy"，如果xy为非法字符，则会抛出IllegalArgumentException。
所以如果需要得到精确的结果，需要自己另加额外的控制标志位。
 		即，原字符包含+号不解码, 包含%号可能转码异常
		 */
		String newStr = str;//先等于原值，防止下面出异常时为空情况
		if (null != str && !"".equals(str) && str.indexOf("+")==-1)
		{
			try
			{
				newStr = URLDecoder.decode(str, "UTF-8");
			}
			catch (Exception e)
			{
				logger.error("URLDecoder转码失败,str="+str+"; "+e.getMessage());
			}
		}
		return newStr;
	}
}
