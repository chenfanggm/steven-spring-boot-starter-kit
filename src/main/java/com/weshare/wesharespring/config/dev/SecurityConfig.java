package com.weshare.wesharespring.config.dev;

import com.weshare.wesharespring.common.security.JWTRefreshTokenAuthProvider;
import com.weshare.wesharespring.common.security.JWTTokenAuthProvider;
import com.weshare.wesharespring.config.RouteConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.ManagementServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletResponse;


@Profile("dev")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
@Order(ManagementServerProperties.BASIC_AUTH_ORDER - 1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JWTTokenAuthProvider jwtTokenAuthProvider;
    @Autowired
    private JWTRefreshTokenAuthProvider jwtRefreshTokenAuthProvider;

    /***
     * Config what resources need to be protected
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            // no need CSRF by using JWT
            .csrf().disable()
            // no need session by using JWT
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            // authentication exception handling
            .exceptionHandling().authenticationEntryPoint(unauthorizedEntryPoint()).and()
            // define route access rules
            .authorizeRequests()
            // allow main api access by only authenticated user
            .anyRequest().permitAll();
    }

    /**
     * Register authentication provider
     */
    @Override
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // register auth provider
        authenticationManagerBuilder
            .authenticationProvider(jwtTokenAuthProvider)
            .authenticationProvider(jwtRefreshTokenAuthProvider);
    }

    /**
     * Beans
     */
    @Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    private String[] actuatorEndpoints() {
        return new String[]{
            RouteConfig.AUTOCONFIG_ENDPOINT, RouteConfig.BEANS_ENDPOINT, RouteConfig.CONFIGPROPS_ENDPOINT,
            RouteConfig.ENV_ENDPOINT, RouteConfig.MAPPINGS_ENDPOINT, RouteConfig.METRICS_ENDPOINT,
            RouteConfig.SHUTDOWN_ENDPOINT};
    }
}