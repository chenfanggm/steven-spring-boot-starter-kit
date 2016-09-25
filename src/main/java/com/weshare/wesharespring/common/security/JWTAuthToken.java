package com.weshare.wesharespring.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.Collection;

public class JWTAuthToken extends PreAuthenticatedAuthenticationToken {

    public JWTAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public JWTAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
