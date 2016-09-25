package com.weshare.wesharespring.service;

import com.weshare.wesharespring.common.constant.Role;
import com.weshare.wesharespring.common.exception.AuthServiceException;
import com.weshare.wesharespring.common.exception.DuplicateItemException;
import com.weshare.wesharespring.common.exception.ItemNotFoundException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.common.security.JWTAuthRefreshToken;
import com.weshare.wesharespring.common.security.JWTAuthToken;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.entity.Request.RegisterRequest;
import com.weshare.wesharespring.entity.Token;
import com.weshare.wesharespring.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class AuthService {

    private final static Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final UserService userService;
    private final TokenService tokenService;
    private final ProfileService profileService;

    @Autowired
    public AuthService(final UserService userService, final TokenService tokenService, final ProfileService profileService) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.profileService = profileService;
    }

    public UsernamePasswordAuthenticationToken registerUser(final RegisterRequest registerRequest)
        throws ItemNotFoundException, DuplicateItemException, StorageServiceException {

        final String hash = Utils.getPasswordHash(registerRequest.getPassword());
        final User user = userService.createUser(registerRequest.getEmail(), hash);

        final Profile profile = new Profile();
        profile.setUserId(user.getUserId());
        profile.setFirstName(registerRequest.getFirstName());
        profile.setLastName(registerRequest.getLastName());
        profileService.upsertProfile(profile);

        return authByUser(user);
    }

    public UsernamePasswordAuthenticationToken authByPassword(final String email, final String password, final Boolean rememberMe)
        throws  AuthServiceException {

        // verify user existence
        final User user;
        try {
            user = userService.getUserByEmail(email);
        } catch (final ItemNotFoundException | StorageServiceException baseServiceException) {
            logger.error("<In> authByRefreshToken(): Caught BaseServiceException: {}", baseServiceException.toString());
            throw new AuthServiceException();
        }

        // verify user password
        if (!Utils.isValidUserPassword(user, password)) {
            logger.warn("<In> authWithPassword(): Invalid password for User: {}", email);
            throw new AuthServiceException();
        }

        // grant user roles
        final UsernamePasswordAuthenticationToken authentication;
        if (user.getAccessLevel().equals(Role.ADMIN.getValue())) {
            authentication = new UsernamePasswordAuthenticationToken(user, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(Role.ADMIN.toString()));
        } else {
            authentication = new UsernamePasswordAuthenticationToken(user, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER.toString()));
        }

        // set token
        final String token = tokenService.genToken(user.getUserId());
        final Token jwtToken = new Token(token);
        if (rememberMe) {
            final String refreshToken = tokenService.genRefreshToken(user.getUserId());
            jwtToken.setRefreshToken(refreshToken);
        }

        authentication.setDetails(jwtToken);

        return authentication;
    }

    public JWTAuthToken authByToken(final String token)
        throws AuthServiceException {

        // verify token
        final Map<String, Object> payload = tokenService.verifyToken(token);

        // get user
        final Long userId = ((Integer) payload.get("userId")).longValue();
        final User user;
        try {
            user = userService.getUserByUserId(userId);
        } catch (final ItemNotFoundException | StorageServiceException baseServiceException) {
            logger.error("<In> authByRefreshToken(): Caught BaseServiceException: {}", baseServiceException.toString());
            throw new AuthServiceException();
        }

        // set authentication
        final JWTAuthToken authentication;
        if (user.getAccessLevel().equals(Role.ADMIN.getValue())) {
            authentication = new JWTAuthToken(user, null, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER_AND_ADMIN.toString()));
        } else {
            authentication = new JWTAuthToken(user, null, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER.toString()));
        }

        authentication.setDetails(token);

        return authentication;
    }

    public JWTAuthRefreshToken authByRefreshToken(final String refreshToken)
        throws AuthServiceException {

        // verify token
        final Map<String, Object> payload = tokenService.verifyToken(refreshToken);
        // get user
        final Long userId = ((Integer) payload.get("userId")).longValue();
        // get stored refresh token
        final String storedRefreshToken = tokenService.getRefreshTokenByUserId(userId);
        // validate refresh token
        if (!refreshToken.equals(storedRefreshToken)) {
            logger.error("<In> authByRefreshToken(): Expired stored RefreshToken : TokenPayload: {}", payload.toString());
            throw new AuthServiceException();
        }
        // generate new token
        final String newToken = tokenService.genToken(userId);
        final User user;
        try {
            user = userService.getUserByUserId(userId);
        } catch (final ItemNotFoundException | StorageServiceException baseServiceException) {
            logger.error("<In> authByRefreshToken(): Caught BaseServiceException: {}", baseServiceException.toString());
            throw new AuthServiceException();
        }

        // set authentication
        final JWTAuthRefreshToken authentication;
        if (user.getAccessLevel().equals(Role.ADMIN.getValue())) {
            authentication = new JWTAuthRefreshToken(user, newToken, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER_AND_ADMIN.toString()));
        } else {
            authentication = new JWTAuthRefreshToken(user, newToken, AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER.toString()));
        }

        authentication.setDetails(newToken);

        return authentication;
    }

    public UsernamePasswordAuthenticationToken authByUser(final User user) {

        // grant user roles
        final UsernamePasswordAuthenticationToken authentication;
        if (user.getAccessLevel().equals(Role.ADMIN.getValue())) {
            authentication = new UsernamePasswordAuthenticationToken(user, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER_AND_ADMIN.toString()));
        } else {
            authentication = new UsernamePasswordAuthenticationToken(user, null,
                AuthorityUtils.commaSeparatedStringToAuthorityList(Role.USER.toString()));
        }

        // set token
        final Token token = new Token(tokenService.genToken(user.getUserId()));

        authentication.setDetails(token);

        return authentication;
    }
}
