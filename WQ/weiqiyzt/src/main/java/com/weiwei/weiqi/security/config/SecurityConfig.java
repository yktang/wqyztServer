package com.weiwei.weiqi.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.weiwei.weiqi.security.filter.RegisterFilter;
import com.weiwei.weiqi.security.provider.RegisterTokenAuthenticationProvider;
import com.weiwei.weiqi.security.token.RegisterTokenService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
			.antMatchers("/weiwei/**").permitAll();
		//http
		//	.addFilterBefore(new RegisterFilter(authenticationManager()), BasicAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.authenticationProvider(registerTokenAuthenticationProvider());
	}
	
	@Bean
	public RegisterTokenService registerTokenService(){
		return new RegisterTokenService();
	}
	
	@Bean
	public AuthenticationProvider registerTokenAuthenticationProvider(){
		return new RegisterTokenAuthenticationProvider(registerTokenService());
	}
}
