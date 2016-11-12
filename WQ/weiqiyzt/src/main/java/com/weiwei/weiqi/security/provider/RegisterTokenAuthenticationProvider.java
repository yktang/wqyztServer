package com.weiwei.weiqi.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.google.common.base.Optional;
import com.weiwei.weiqi.security.authentication.RegisterAuthenticationWithToken;
import com.weiwei.weiqi.security.token.RegisterTokenService;

public class RegisterTokenAuthenticationProvider implements AuthenticationProvider {

	private RegisterTokenService tokenService;
	
	public RegisterTokenAuthenticationProvider(RegisterTokenService tokenService) {
        this.tokenService = tokenService;
    }
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		@SuppressWarnings("unchecked")
		Optional<String> token = (Optional<String>) authentication.getPrincipal();
        if (!token.isPresent() || token.get().isEmpty()) {
            throw new BadCredentialsException("Invalid token");
        }
        if (!tokenService.contains(token.get())) {
            throw new BadCredentialsException("Invalid token or token expired");
        }
        return tokenService.retrieve(token.get());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(RegisterAuthenticationWithToken.class);
	}

}
