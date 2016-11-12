package com.weiwei.weiqi.security.filter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.google.common.base.Optional;
import com.weiwei.common.Constants;
import com.weiwei.weiqi.security.authentication.RegisterAuthenticationWithToken;
import com.weiwei.weiqi.security.token.RegisterTokenService;

public class RegisterFilter extends GenericFilterBean {
	
	private AuthenticationManager authenticationManager;
	
	public RegisterFilter(AuthenticationManager authenticationManager){
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String username_saw = httpRequest.getHeader(Constants.HEADER_REG_USERNAME);
		String username_str = null;
        if(username_saw != null){
        	username_str = URLDecoder.decode(URLDecoder.decode(username_saw, "UTF-8"), "UTF-8");
        }
        String companyName_saw = httpRequest.getHeader(Constants.HEADER_REG_COMPANYNAME);
        String companyName_str = null;
        if(companyName_saw != null){
        	companyName_str = URLDecoder.decode(URLDecoder.decode(companyName_saw, "UTF-8"), "UTF-8");
        }
        
        String resourcePath = new UrlPathHelper().getPathWithinApplication(httpRequest);
        
        try{
        	if(requestToRegistrationCode(httpRequest, resourcePath)) {
				processGenerateCodeImage(httpResponse);
				return;
			}
        } catch (DisabledException authenticationException) {
            SecurityContextHolder.clearContext();
		} catch (BadCredentialsException ex) {
			SecurityContextHolder.clearContext();
            httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
		} finally {
			
		}
	}
	
	private boolean requestToRegistrationCode(HttpServletRequest httpRequest, String resourcePath){
		return Constants.REGISTER_CODE_URL.equalsIgnoreCase(resourcePath) && httpRequest.getMethod().equalsIgnoreCase("GET");
	}
	
	private void processGenerateCodeImage(HttpServletResponse httpResponse) throws IOException{
		DefaultKaptcha captcha = new DefaultKaptcha();
		Properties properties = new Properties();
		properties.setProperty("kaptcha.image.width", "200");
		properties.setProperty("kaptcha.image.height", "100");
		properties.setProperty("kaptcha.textproducer.char.string", "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.font.size", "70");
		Config config = new Config(properties);
		captcha.setConfig(config);
		Producer captchaProducer = captcha;
		String capText = captchaProducer.createText();
		
		RegisterTokenService tokenService = new RegisterTokenService();
		String newToken = tokenService.generateNewToken();
		
		RegisterAuthenticationWithToken authentication = new RegisterAuthenticationWithToken(null, null, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ON_REGISTERING_USER"));
        authentication.setToken(newToken);
        tokenService.store(newToken+capText.toUpperCase(), authentication);
		
		Cookie cookie = new Cookie("sessionid", newToken);
		httpResponse.addCookie(cookie);
		BufferedImage bi = captchaProducer.createImage(capText);
		httpResponse.setContentType("image/jpeg");
		httpResponse.setStatus(HttpServletResponse.SC_OK);
		ImageIO.write(bi, "jpg", httpResponse.getOutputStream());
	}
	
	private void processTokenAuthentication(Optional<String> token) {
        Authentication resultOfAuthentication = tryToAuthenticateWithToken(token);
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
    }
	
	private Authentication tryToAuthenticateWithToken(Optional<String> token) {
		RegisterAuthenticationWithToken requestAuthentication = new RegisterAuthenticationWithToken(token, null);
        return tryToAuthenticate(requestAuthentication);
    }
	
	private Authentication tryToAuthenticate(Authentication requestAuthentication) {
        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        return responseAuthentication;
    }

}
