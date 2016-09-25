package com.weshare.wesharespring.controller;

import com.weshare.wesharespring.common.annotation.LoggedUser;
import com.weshare.wesharespring.common.exception.BaseServiceException;
import com.weshare.wesharespring.config.RouteConfig;
import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.service.FileService;
import com.weshare.wesharespring.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(value = RouteConfig.PROFILE_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProfileController {

    private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);

    private final ProfileService profileService;
    private final FileService fileService;

    @Autowired
    public ProfileController(final ProfileService profileService, final FileService fileService) {
        this.profileService = profileService;
        this.fileService = fileService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Profile getProfile(@LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> getProfile(): UserId: {}", user.getUserId());
        final Profile profile =  profileService.getProfileByUserId(user.getUserId());
        logger.info("<End> getProfile(): UserId: {}", user.getUserId());
        return profile;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public Profile getProfileByUserId(@PathVariable("userId") final Long userId)
        throws BaseServiceException {

        logger.info("<Start> getProfileByUserId(): UserId: {}", userId);
        final Profile profile =  profileService.getProfileByUserId(userId);
        logger.info("<End> getProfileByUserId(): UserId: {}", userId);
        return profile;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Profile upsertProfile(@RequestBody final Profile profile, @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> upsertProfile()");
        profile.setUserId(user.getUserId());
        final Profile profileResult = profileService.upsertProfile(profile);
        logger.info("<End> upsertProfile()");
        return profileResult;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Profile updateProfile(@RequestBody final Profile profile, @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> updateProfile()");
        profile.setUserId(user.getUserId());
        final Profile profileResult = profileService.updateProfile(profile);
        logger.info("<End> updateProfile()");
        return profileResult;
    }

    @RequestMapping(value = "/status/{status}", method = RequestMethod.PUT)
    public Profile updateProfileStatus(@PathVariable("status") final Integer status, @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> updateProfileStatus()");
        final Profile profileResult = profileService.updateProfileStatus(user.getUserId(), status);
        logger.info("<End> updateProfileStatus()");

        return profileResult;
    }

    @RequestMapping(value = "/image", method = RequestMethod.GET)
    public String getProfileImageUrl(@LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> getProfileImageUrl(): UserId: {}", user.getUserId());
        final String profileImageUrl = fileService.getProfileImageUrl(user.getUserId());
        logger.info("<End> getProfileImageUrl(): UserId: {}", user.getUserId());

        return profileImageUrl;
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public Profile uploadProfileImage(@LoggedUser final User user,
                                      @RequestParam("file") MultipartFile file)
        throws IOException, BaseServiceException {

        final Long userId = user.getUserId();
        logger.info("<Start> uploadProfileImage(): UserId: {}", userId);

        fileService.uploadProfileImage(userId, file);
        final String profileImageUrl = fileService.getProfileImageUrl(userId);
        final Profile profile = new Profile();
        profile.setUserId(userId);
        profile.setHeadShotUrl(profileImageUrl);
        final Profile updatedProfile = profileService.updateProfile(profile);
        logger.info("<End> uploadProfileImage(): UserId: {}", userId);
        return updatedProfile;
    }
}
