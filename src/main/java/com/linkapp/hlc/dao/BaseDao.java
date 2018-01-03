package com.linkapp.hlc.dao;

import java.util.List;

import com.linkapp.hlc.entity.Config;
import com.linkapp.hlc.entity.User;



public interface BaseDao {

	public void save(User user);
	
	public int select();
	
	public List<Config> getConfig();
	
	public void saveConfig(Config con);
}
