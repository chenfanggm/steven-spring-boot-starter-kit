package com.weshare.wesharespring.service;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.exception.AuthServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.jdbi.dao.TokenDao;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class TokenService {

    private static final Logger logger = LoggerFactory.getLogger(TokenService.class);

    private static final JWTSigner jwtSigner = new JWTSigner(Constant.AUTH_JWT_SECRET);
    private static final JWTVerifier jwtVerifier = new JWTVerifier(Constant.AUTH_JWT_SECRET);
    private static final JWTSigner.Options jwtOptions = new JWTSigner.Options()
        //.setExpirySeconds(Constant.AUTH_JWT_TOKEN_EXPIRE)
        .setNotValidBeforeLeeway(5).setIssuedAt(true)
        .setJwtId(true);
    private static final JWTSigner.Options jwtRefreshOptions = new JWTSigner.Options()
        .setExpirySeconds(Constant.AUTH_JWT_REFRESH_TOKEN_EXPIRE)
        .setNotValidBeforeLeeway(5).setIssuedAt(true)
        .setJwtId(true);

    private final TokenDao tokenDao;

    @Autowired
    public TokenService(TokenDao tokenDao) {
        this.tokenDao = tokenDao;
    }

    public String genToken(final Long userId) {

        final Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);

        return jwtSigner.sign(claims, jwtOptions);
    }

    public String genRefreshToken(final Long userId)
        throws AuthServiceException {

        final Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId);
        final String refreshToken = jwtSigner.sign(claims, jwtRefreshOptions);
        final Long timeNow = Utils.getCurrentTimeStamp();
        // upsert token
        try {
            final Integer changes = tokenDao.update(userId, "", "", refreshToken, timeNow);
            if (changes == 0) {
                try {
                    tokenDao.create(userId, "", "", refreshToken, timeNow);
                } catch (final DBIException dbiException) {
                    logger.error("<In> genRefreshToken(): Caught DBIException: {}", dbiException.toString());
                    throw new AuthServiceException();
                }
            }
        } catch (final DBIException dbiException) {
            logger.error("<In> genRefreshToken(): Caught DBIException: {}", dbiException.toString());
            throw new AuthServiceException();
        }

        return refreshToken;
    }

    public Map<String,Object> verifyToken (final String token)
        throws AuthServiceException {

        final Map<String,Object> payload;
        try {
            payload = jwtVerifier.verify(token);

            if (payload.isEmpty()) {
                throw new AuthServiceException("Empty payload in token");
            }

        } catch (final IllegalStateException | IOException jwtVerifyException) {
            logger.error("<In> verifyToken(): Failed to verify token", jwtVerifyException);
            throw new AuthServiceException("Failed to verify token!");
        } catch (final JWTVerifyException | InvalidKeyException | NoSuchAlgorithmException | SignatureException invalidTokenException) {
            logger.error("<In> verifyToken(): Invalid token", invalidTokenException);
            throw new AuthServiceException("Invalid Token!");
        }

        return payload;
    }

    public String getRefreshTokenByUserId (final Long userId)
        throws AuthServiceException {

        try {
            final String refreshToken = tokenDao.getRefreshTokenByUserId(userId);
            if (refreshToken == null) {
                throw new AuthServiceException();
            }
            return refreshToken;

        } catch (final DBIException dbiException) {
            logger.error("<In> getRefreshTokenByUserId(): Caught DBIException: {}", dbiException.toString());
            throw new AuthServiceException();
        }
    }
}
