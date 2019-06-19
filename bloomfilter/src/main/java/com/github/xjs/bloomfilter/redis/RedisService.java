package com.github.xjs.bloomfilter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.rebloom.client.Client;

@Service
public class RedisService {
	
	@Autowired
	Client client;
	
	public boolean bfCreate(String key, long initCapacity, double errorRate) {
		try {
			client.createFilter(key, initCapacity, errorRate);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean bfAdd(String key, String value) {
		try {
			return client.add(key, value);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean bfExists(String key, String value) {
		try {
			return client.exists(key, value);
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(String key) {
		try {
			client.delete(key);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
