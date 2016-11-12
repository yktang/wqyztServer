package com.weiwei.weiqi.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class VersionBean {
	private String version;
	private String androidVersion;
	@Autowired
    public VersionBean(@Value("${spring.weiqi.service.version}") String version, @Value("${spring.weiqi.android.version}") String androidversion){
		this.version = version;
		androidVersion = androidversion;
	}
	public String getVersion() {
		return version;
	}
	public String getAndroidVersion() {
		return androidVersion;
	}
}
