package com.weshare.wesharespring.common.security;

import com.weshare.wesharespring.common.exception.AuthServiceException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class PasswordAuthProvider implements AuthenticationProvider {

    private AuthService authService;

    @Autowired
    public PasswordAuthProvider(final AuthService authService) {
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(final Authentication authentication)
        throws AuthenticationException {

        final String username = (String) authentication.getPrincipal();
        final String password = (String) authentication.getCredentials();
        try {
            return authService.authByPassword(username, password, false);
        } catch (final StorageServiceException storageServiceException) {
            throw new AuthServiceException();
        }
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
