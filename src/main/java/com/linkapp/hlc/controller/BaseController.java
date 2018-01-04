package com.linkapp.hlc.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.linkapp.hlc.entity.Config;
import com.linkapp.hlc.service.BaseService;

@Controller
@RequestMapping("db")
public class BaseController {

	@Resource private BaseService baseService;
	@RequestMapping("/insert")	
	public void insert(HttpServletRequest request,HttpServletResponse response)  throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("1");
		long starTime=System.currentTimeMillis();
		baseService.save();
		long endTime=System.currentTimeMillis();
		long time=endTime-starTime;
		System.out.println();
		response.getWriter().print("执行时间："+time);			
	}
	@RequestMapping("/sel")	
	public void select(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		System.out.println("1");
		long starTime=System.currentTimeMillis();
		int count=baseService.select();
		long endTime=System.currentTimeMillis();
		long time=endTime-starTime;
		System.out.println();
		response.getWriter().print(count+"执行时间："+time);
		//return  String.valueOf(count);
		
		
	}
	@RequestMapping("/getConfig")	
	public void getConfig(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		long con=baseService.getConfig();
		response.getWriter().print("执行时间："+con);		
			
		
		
	}

	@RequestMapping("/saveConfig")	
	public void saveConfig(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Config con=new Config("hello", "world");
		baseService.saveConfig(con);

	}

	@RequestMapping("/getConfigs")	
	public void getConfigs(HttpServletRequest request,HttpServletResponse response) throws IOException{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		long con=baseService.getConfigs();
		response.getWriter().print("执行时间："+con);		
			
		
		
	}
	
	
	
}
