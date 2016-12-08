package com.weiwei.weiqi.service.redis.impl;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import com.weiwei.weiqi.service.redis.api.RedisService;

@Component
public class RedisServiceImpl implements RedisService{
	 
	private final Logger logger = LoggerFactory.getLogger(RedisServiceImpl.class);
		
	   @Autowired  
	   private StringRedisTemplate stringRedisTemplate; 
	   
	   
	   /**
	    * 设定single hash表
	    * @param key
	    * @param map
	    */
	   public void setSingleHash(String key,Object hashKey,Object value){
		   setSingleHash(key,hashKey,value,0);
	   }
	   /**
	    * 设定single hash表
	    * @param key
	    * @param map
	    * @param expiredSeconds 超时时间  大于0设置超时时间
	    */
	   public void setSingleHash(String key,Object hashKey,Object value,int expiredSeconds){
		   if(expiredSeconds > 0){
			   //stringRedisTemplate.multi();
			   stringRedisTemplate.opsForHash().put(key, hashKey,value);
			   stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
			   logger.info("--setSingleHash-->key-->"+key+",-->expr"+expiredSeconds);
			   //stringRedisTemplate.exec();
		   }else{
			   stringRedisTemplate.opsForHash().put(key, hashKey,value);
		   }
	   }
	   
	   /**
	    * 设定multi hash表
	    * @param key
	    * @param map
	    */
	   public void setMultiHash(String key,Map<String,String> map){
		   setMultiHash(key,map,0);
	   }
	   
	   /**
	    * 设定multi hash表
	    * @param key
	    * @param map
	    * @param expiredSeconds 超时时间  大于0是不设置超时时间
	    */
	   public void setMultiHash(String key,Map<String,String> map,int expiredSeconds){
		   if(expiredSeconds > 0){
			   //stringRedisTemplate.multi();
			   stringRedisTemplate.opsForHash().putAll(key, map);
			   stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
			   logger.info("--setMultiHash-->key-->"+key+",-->expr"+expiredSeconds);
			   //stringRedisTemplate.exec();
		   }else{
			   stringRedisTemplate.opsForHash().putAll(key, map);
		   }

	   }
	   
	   
	   /**
	    * 
	   * @Description: 设定key超时时间
	   * @param key
	   * @param expiredSeconds 
	   *
	    */
	   public void setExpireSeconds(String key,int expiredSeconds){
		   stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
		   logger.info("--setExpireSeconds-->key-->"+key+",-->expr"+expiredSeconds);
	   }
	   /**
	    * 获取单个 hash值
	    * @param key
	    * @param hashKey
	    */
	   public Object getSingleHash(String key,Object hashKey){
		   return stringRedisTemplate.opsForHash().get(key, hashKey);
	   }
	   
	   /**
	     * 删除key
	     * @param key
	     * void
	    */
	   public void delKey(String key){
		   stringRedisTemplate.delete(key);
	   }
	   /**
	    * 获取  hash map
	    * @param key hashmap key值
	    */
	   public Map<Object, Object>  getHashMap(String key){
		   return stringRedisTemplate.opsForHash().entries(key);
	   }
	   
	   /**
	    * 批量获取 hash
	    * @param key
	    * @param hashKeys
	    */
	   public List<Object> getMultiHash(String key,List<Object> hashKeys){
		  return stringRedisTemplate.opsForHash().multiGet(key, hashKeys);
	   }
	   
	   
//	   /**
//	    * hash value 增长操作 
//	    * @param key
//	    * @param delta 增长量
//	    * @return 增长后的数值
//	    */
//	   public long hashValueIncrement(String key,String hashKey,long delta){
//		   return 0;
//		   //return hashValueIncrement(key,hashKey,delta);
//	   }
	   
	   /**
	    * hash value 增长操作 
	    * @param key
	    * @param delta 增长量
	    * @param expiredSeconds  键值 超时时间  大于0是不设置超时时间
	    * @return 增长后的数值
	    */
	   public long hashValueIncrement(String key,String hashKey,long delta,int expiredSeconds){
		   long result = -1;
		   if(expiredSeconds > 0){
			   //stringRedisTemplate.multi();
			   result =  stringRedisTemplate.opsForHash().increment(key,hashKey,delta);
			   if(result == delta){
				   stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
			   }
			   //stringRedisTemplate.exec();
		   }else{
			   result =  stringRedisTemplate.opsForHash().increment(key,hashKey,delta);
		   }
		   return  result;
	   }
	   
	   /**
	    * 设定 key value
	    * @param key
	    * @param value
	    */
	   public void setValue(String key,String value){
		   setValue(key,value,0);
	   }
	   
	   /**
	    * 设定 key value
	    * @param key
	    * @param value
	    * @param expiredSeconds  键值 超时时间  大于0是不设置超时时间
	    */
	   public void setValue(String key,String value,int expiredSeconds){
		   if(expiredSeconds > 0){
			   //stringRedisTemplate.multi();
			   stringRedisTemplate.opsForValue().set(key, value);
			   stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);
			   //stringRedisTemplate.exec();
		   }else{
			   stringRedisTemplate.opsForValue().set(key, value);
		   }
	   }
	   
	   /**
	    *  获取 String  value
	    * @param key
	    * @return
	    */
	   public String getValue(String key){
		   return stringRedisTemplate.opsForValue().get(key);
	   }
	   
	   
//	   /**
//	    * String value 增长操作 
//	    * @param key
//	    * @param delta 增长量
//	    * @return 增长后的数值
//	    */
//	   public long valueIncrement(String key,long delta){
//		   return 0;
//		   //return valueIncrement(key,delta);
//	   }
//	   
	   /**
	    * String value 增长操作 
	    * @param key
	    * @param delta 增长量
	    * @param expiredSeconds  键值 超时时间  大于0是不设置超时时间
	    * @return 增长后的数值
	    */
	   public long valueIncrement(String key,long delta,int expiredSeconds){
		   long result = -1;
		   if(expiredSeconds > 0){
			   //stringRedisTemplate.multi();
			   result =  stringRedisTemplate.opsForValue().increment(key, delta);
			   if(result == delta){
				   stringRedisTemplate.expire(key, expiredSeconds, TimeUnit.SECONDS);   
			   }
			   //stringRedisTemplate.exec();
		   }else{
			   result =  stringRedisTemplate.opsForValue().increment(key, delta);
		   }
		   return  result;
	   }
	   
	 
}
