package com.weiwei.weiqi.service.redis.api;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RedisService {
	   /**
	    * 设定single hash表
	    * @param key
	    * @param map
	    */
	   public void setSingleHash(String key,Object hashKey,Object value);
	   
	   /**
	    * 设定single hash表
	    * @param key
	    * @param map
	    * @param expiredSeconds 超时时间  小于0设置超时时间
	    */
	   public void setSingleHash(String key,Object hashKey,Object value,int expiredSeconds);
	   
	   /**
	    * 设定multi hash表
	    * @param key
	    * @param map
	    */
	   public void setMultiHash(String key,Map<String,String> map);
	   
	   /**
	    * 设定multi hash表
	    * @param key
	    * @param map
	    * @param expiredSeconds 超时时间  小于0是不设置超时时间
	    */
	   public void setMultiHash(String key,Map<String,String> map,int expiredSeconds);	   
	   
	   /**
	    * 
	   * @Description: 设定key超时时间
	   * @param key
	   * @param expiredSeconds 
	   *
	    */
	   public void setExpireSeconds(String key,int expiredSeconds);
	   
	   /**
	    * 获取单个 hash值
	    * @param key
	    * @param hashKey
	    */
	   public Object getSingleHash(String key,Object hashKey);
	   
	   /**
	     * 删除key	
	     * @param key
	     * void
	    */
	   public void delKey(String key);
	   /**
	    * 获取  hash map
	    * @param key hashmap key值
	    */
	   public Map<Object, Object>  getHashMap(String key);
	   	
	   /**
	    * 批量获取 hash
	    * @param key
	    * @param hashKeys
	    */
	   public List<Object> getMultiHash(String key,List<Object> hashKeys);
	   
	   
//	   /**
//	    * hash value 增长操作 
//	    * @param key
//	    * @param delta 增长量
//	    * @return 增长后的数值
//	    */
//	   public long hashValueIncrement(String key,String hashKey,long delta);
	   
	   /**
	    * hash value 增长操作 
	    * @param key
	    * @param delta 增长量
	    * @param expiredSeconds  键值 超时时间  小于0是不设置超时时间
	    * @return 增长后的数值
	    */
	   public long hashValueIncrement(String key,String hashKey,long delta,int expiredSeconds);
	   
	   /**
	    * 设定 key value
	    * @param key
	    * @param value
	    */
	   public void setValue(String key,String value);
	   
	   /**
	    * 设定 key value
	    * @param key
	    * @param value
	    * @param expiredSeconds  键值 超时时间  小于0是不设置超时时间
	    */
	   public void setValue(String key,String value,int expiredSeconds);
	   
	   /**
	    *  获取 String  value
	    * @param key
	    * @return
	    */
	   public String getValue(String key);
	   
	   
//	   /**
//	    * String value 增长操作 
//	    * @param key
//	    * @param delta 增长量
//	    * @return 增长后的数值
//	    */
//	   public long valueIncrement(String key,long delta);
	   
	   /**
	    * String value 增长操作 
	    * @param key
	    * @param delta 增长量
	    * @param expiredSeconds  键值 超时时间  小于0是不设置超时时间
	    * @return 增长后的数值
	    */
	   public long valueIncrement(String key,long delta,int expiredSeconds);
	   
	   
}
