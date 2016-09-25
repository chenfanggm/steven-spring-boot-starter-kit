package com.weshare.wesharespring.controller;

import com.weshare.wesharespring.common.annotation.LoggedUser;
import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.exception.BaseServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.config.RouteConfig;
import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.entity.Request.AuthRequest;
import com.weshare.wesharespring.entity.Request.RegisterRequest;
import com.weshare.wesharespring.entity.Response.AuthResponse;
import com.weshare.wesharespring.entity.Response.SuccessResponse;
import com.weshare.wesharespring.entity.Response.UserResponse;
import com.weshare.wesharespring.entity.Token;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.service.AuthService;
import com.weshare.wesharespring.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping(value = RouteConfig.AUTH_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;
    private final ProfileService profileService;

    @Autowired
    public AuthController(final AuthService authService, final ProfileService profileService) {
        this.authService = authService;
        this.profileService = profileService;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public AuthResponse registerUser(@RequestBody final @Valid RegisterRequest registerRequest, final HttpServletResponse response)
        throws IOException, BaseServiceException {

        logger.info("<Start> registerUser(): for User: {} ", registerRequest.getEmail());
        final Authentication authentication = authService.registerUser(registerRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Token token = (Token) authentication.getDetails();
        final User user = (User) authentication.getPrincipal();
        final Profile profile = profileService.getProfileByUserId(user.getUserId());

        //set cookies
        Utils.addCookie(response, Constant.AUTH_JWT_TOKEN_COOKIE, token.getToken(), -1);

        logger.info("<End> registerUser(): for User: {}", registerRequest.getEmail());
        return new AuthResponse(token, new UserResponse(user, profile));
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public AuthResponse loginUser(@RequestBody final @Valid AuthRequest authRequest, final HttpServletResponse response)
        throws IOException, BaseServiceException {

        logger.info("<Start> loginUser(): for User: {} ", authRequest.getEmail());
        final Authentication authentication = authService.authByPassword(authRequest.getEmail(), authRequest.getPassword(), authRequest.getRememberMe());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final Token token = (Token) authentication.getDetails();
        final User user = (User) authentication.getPrincipal();
        final Profile profile = profileService.getProfileByUserId(user.getUserId());

        // set cookies
        if (token.hasRefreshToken()) {
            Utils.addCookie(response, Constant.AUTH_JWT_TOKEN_COOKIE, token.getToken(), Constant.AUTH_JWT_TOKEN_EXPIRE);
            Utils.addCookie(response, Constant.AUTH_JWT_REFRESH_TOKEN_COOKIE, token.getRefreshToken(), Constant.AUTH_JWT_REFRESH_TOKEN_EXPIRE);
        } else {
            Utils.addCookie(response, Constant.AUTH_JWT_TOKEN_COOKIE, token.getToken(), -1);
        }

        logger.info("<End> loginUser(): for User: {}", authRequest.getEmail());
        return new AuthResponse(token, new UserResponse(user, profile));
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public SuccessResponse loginUser(@LoggedUser final User user, final HttpServletResponse response)
        throws IOException, BaseServiceException {

        logger.info("<Start> logout(): for User: {} ", user.getEmail());
        // remove cookie
        Utils.addCookie(response, Constant.AUTH_JWT_TOKEN_COOKIE, "", 0);
        Utils.addCookie(response, Constant.AUTH_JWT_REFRESH_TOKEN_COOKIE, "", 0);
        SecurityContextHolder.clearContext();
        logger.info("<End> logout(): for User: {} ", user.getEmail());

        return new SuccessResponse();
    }
}
