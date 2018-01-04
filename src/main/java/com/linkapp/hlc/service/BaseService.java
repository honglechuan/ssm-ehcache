package com.linkapp.hlc.service;

import java.util.List;

import com.linkapp.hlc.entity.Config;

public interface BaseService {
	public void save();
	public int select();
	public long getConfig();
	public void saveConfig(Config con);
	
	public long getConfigs();
}
