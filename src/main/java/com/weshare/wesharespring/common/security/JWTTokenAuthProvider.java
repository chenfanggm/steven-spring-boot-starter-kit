package com.weshare.wesharespring.common.security;

import com.weshare.wesharespring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenAuthProvider implements AuthenticationProvider {

    private AuthService authService;

    @Autowired
    public JWTTokenAuthProvider(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication)
        throws AuthenticationException {

        final String token = (String) authentication.getPrincipal();
        return authService.authByToken(token);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(JWTAuthToken.class);
    }
}
