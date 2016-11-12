package com.weiwei.weiqi.security.token;

import java.util.UUID;

import org.springframework.security.core.Authentication;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class RegisterTokenService {

	private static final Cache registerTokenCache = CacheManager.getInstance().getCache("registerTokenCache");
	
	public String generateNewToken() {
        return UUID.randomUUID().toString();
    }
	
	public void store(String token, Authentication authentication) {
    	registerTokenCache.put(new Element(token, authentication));
    }
	
	public boolean contains(String token) {
        return registerTokenCache.get(token) != null;
    }
	
	public Authentication retrieve(String token) {
        return (Authentication) registerTokenCache.get(token).getObjectValue();
    }
	
}
