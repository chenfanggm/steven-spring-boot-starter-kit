package com.weshare.wesharespring.common.utils;

import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.entity.User;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Optional;

public class Utils {

    /**
     * HTTP
     */
    public static void addCookie(final HttpServletResponse response, final String cookieName, final String cookieValue, final Integer cookieExpire) {
        final Cookie cookie = new Cookie(cookieName, cookieValue);
        //cookie.setSecure(true);
        //cookie.setDomain("weshare.com");
        cookie.setPath("/");
        cookie.setMaxAge(cookieExpire);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);
    }

    public static Optional<Cookie> getCookie(final HttpServletRequest request, final String cookieName) {
        final Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i].getName().equals(cookieName)) {
                    return Optional.of(new Cookie(cookieName, cookies[i].getValue()));
                }
            }
        }
        return Optional.empty();
    }

    /**
     *  Service
     */
    public static Boolean isDuplicateEntryException(final DBIException exception) {
        if (exception.getCause() instanceof SQLException) {
            final Integer errorCode = ((SQLException) exception.getCause()).getErrorCode();
            if (errorCode.equals(Constant.SQL_EXCEPTION_DUPLICATE_ENTRY)) {
                return true;
            }
        }
        return false;
    }

    public static Long getCurrentTimeStamp() {
        return System.currentTimeMillis();
    }

    public static String getPasswordHash(final String password) {
        final String salt = BCrypt.gensalt(Constant.AUTH_JWT_SALT_LENGTH).concat(Constant.AUTH_JWT_SECRET);
        return BCrypt.hashpw(password, salt);
    }

    public static Boolean isValidUserPassword(final User user, final String password) {
        return BCrypt.checkpw(password, user.getPassword());
    }

}
