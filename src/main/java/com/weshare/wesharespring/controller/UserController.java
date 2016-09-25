package com.weshare.wesharespring.controller;

import com.weshare.wesharespring.common.annotation.LoggedUser;
import com.weshare.wesharespring.common.exception.BaseServiceException;
import com.weshare.wesharespring.config.RouteConfig;
import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.entity.Request.PasswordRequest;
import com.weshare.wesharespring.entity.Response.SuccessResponse;
import com.weshare.wesharespring.entity.Response.UserResponse;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.service.ProfileService;
import com.weshare.wesharespring.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = RouteConfig.USER_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    private final ProfileService profileService;


    @Autowired
    public UserController(final UserService userService, final ProfileService profileService) {
        this.userService = userService;
        this.profileService = profileService;
    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public UserResponse getCurrentUser(@LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> getCurrentUser(): Email: {}", user.getEmail());
        final Profile profile = profileService.getProfileByUserId(user.getUserId());
        final UserResponse userResponse = new UserResponse(user, profile);
        logger.info("<End> getCurrentUser(): Email: {}", user.getEmail());

        return userResponse;
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public UserResponse getUserByUserId(@PathVariable("userId") final Long userId)
        throws BaseServiceException {

        logger.info("<Start> getUserByUserId(): UserId: {}", userId);
        final User user =  userService.getUserByUserId(userId);
        final Profile profile = profileService.getProfileByUserId(user.getUserId());
        final UserResponse userResponse = new UserResponse(user, profile);
        logger.info("<End> getUserByUserId(): UserId: {}", userId);

        return userResponse;
    }

    @RequestMapping(value = "/{email}", method = RequestMethod.GET)
    public UserResponse getUserByEmail(@PathVariable("email") final String email)
        throws BaseServiceException {

        logger.info("<Start> getUserByEmail(): Email: {}", email);
        final User user =  userService.getUserByEmail(email);
        final Profile profile = profileService.getProfileByUserId(user.getUserId());
        final UserResponse userResponse = new UserResponse(user, profile);
        logger.info("<End> getUserByEmail(): Email: {}", email);

        return userResponse;
    }

    @RequestMapping(value = "/password", method = RequestMethod.PUT)
    public SuccessResponse updatePassword(@RequestBody final PasswordRequest passwordRequest,
                                          @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> updatePassword(): UserId: {}", passwordRequest.getUserId());
        passwordRequest.setUserId(user.getUserId());
        userService.updatePassword(passwordRequest, passwordRequest.getUserId());
        logger.info("<End> updatePassword(): UserId: {}", passwordRequest.getUserId());

        return new SuccessResponse();
    }
}
