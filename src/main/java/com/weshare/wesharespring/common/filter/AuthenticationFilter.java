package com.weshare.wesharespring.common.filter;

import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.security.JWTAuthRefreshToken;
import com.weshare.wesharespring.common.security.JWTAuthToken;
import com.weshare.wesharespring.common.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter extends GenericFilterBean {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationFilter.class);

    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(final AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
        throws IOException, ServletException, AuthenticationException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;
        final Optional<Cookie> jwtCookie = Utils.getCookie(request, Constant.AUTH_JWT_TOKEN_COOKIE);
        final Optional<Cookie> jwtRefreshCookie = Utils.getCookie(request, Constant.AUTH_JWT_REFRESH_TOKEN_COOKIE);
        final String resourcePath = new UrlPathHelper().getPathWithinApplication(request);

        logger.info("<Start> Authenticate for path: {}", resourcePath);

        {
            /**
             * call: JWT authentication
             * when: token is presented in header
             */
            if (jwtCookie.isPresent()) {

                logger.info("<Start> Authenticate user with token");
                final String token = jwtCookie.get().getValue();
                final JWTAuthToken authRequest = new JWTAuthToken(token, null);
                final Authentication authentication = authenticationManager.authenticate(authRequest);
                if (authentication == null || !authentication.isAuthenticated()) {
                    logger.error("<In> Failed to authenticate User with token");
                    throw new AuthenticationServiceException("Unable to authenticate User for provided credentials");
                }
                logger.info("<End> Authenticate user with token");
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } else if (jwtRefreshCookie.isPresent()) {

                logger.info("<Start> Authenticate user with refresh token");
                final String refreshToken = jwtRefreshCookie.get().getValue();
                final JWTAuthRefreshToken authRequest = new JWTAuthRefreshToken(refreshToken, null);
                final Authentication authentication = authenticationManager.authenticate(authRequest);
                if (authentication == null || !authentication.isAuthenticated()) {
                    logger.error("<In> Failed to authenticate User with token");
                    throw new AuthenticationServiceException("Unable to authenticate User for provided credentials");
                }
                logger.info("<End> Authenticate user with refresh token");
                SecurityContextHolder.getContext().setAuthentication(authentication);
                Utils.addCookie(response, Constant.AUTH_JWT_TOKEN_COOKIE, authentication.getCredentials().toString(), Constant.AUTH_JWT_TOKEN_EXPIRE);
            }

            /**********
             / if: neither password and token are presented
             / then: pass request down to the filter chain
             ***********/
            logger.info("<End> Passing request down the filter chain");
            chain.doFilter(req, res);
        }
    }

}
