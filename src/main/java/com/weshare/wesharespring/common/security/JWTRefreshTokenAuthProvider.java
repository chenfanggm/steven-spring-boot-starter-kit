package com.weshare.wesharespring.common.security;

import com.weshare.wesharespring.common.exception.AuthServiceException;
import com.weshare.wesharespring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JWTRefreshTokenAuthProvider implements AuthenticationProvider {

    private AuthService authService;

    @Autowired
    public JWTRefreshTokenAuthProvider(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication)
        throws AuthServiceException {

        final String refreshToken = (String) authentication.getPrincipal();
        return authService.authByRefreshToken(refreshToken);
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(JWTAuthRefreshToken.class);
    }
}
