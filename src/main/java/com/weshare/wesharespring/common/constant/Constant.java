package com.weshare.wesharespring.common.constant;

public class Constant {

    /**
     * System
     */
    public static final String SYSTEM_MONITOR_ADMIN_ROLE = "ADMIN";
    public static final String SYSTEM_SERVER_ENV_DEVELOPMENT = "dev";

    /**
     * CORS
     */
    public static final String  ACCESS_CONTROL_ALLOW_ORIGIN = "*";
    public static final String  ACCESS_CONTROL_ALLOW_METHODS = "POST, GET, PUT, DELETE, OPTIONS";
    public static final String  ACCESS_CONTROL_ALLOW_CREDENTIALS = "true";
    public static final String  ACCESS_CONTROL_MAX_AGE = "3600"; // 60 * 60 = 1 HOUR
    public static final String  ACCESS_CONTROL_ALLOW_HEADERS = "Origin, X-Requested-With, X-Prototype-Version, Content-Type, Cache-Control, Pragma, Accept, withCredentials";

    /**
     * Response
     */
    public static final String  RESPONSE_ACTION_SUCCESS = "ACTION HAS BEEN EXECUTED SUCCESSFULLY";
    public static final String  RESPONSE_ERROR_AUTHENTICATION = "Unable to authenticate User with provided credentials";

    /**
     * SQL Exception State
     */
    public static final String SQL_EXCEPTION_DUPLICATE_ENTRY = "23505";

    /**
     * User
     */
    public static final String   USER_NULL_USERNAME = null;
    public static final Integer  USER_NOT_VERIFIED = 0;
    public static final Integer  USER_VERIFIED = 1;
    public static final Integer  USER_STATUS_INACTIVE = 0;
    public static final Integer  USER_STATUS_ACTIVE = 1;

    /**
     * Auth
     */
    public static final String  AUTH_USERNAME_HEADER            = "X-Auth-Username";
    public static final String  AUTH_PASSWORD_HEADER            = "X-Auth-Password";
    public static final String  AUTH_JWT_SECRET                 = "c3VwZXJfdG9rZW5feA==";
    public static final Integer AUTH_JWT_SALT_LENGTH            = 8;
    public static final String  AUTH_JWT_TOKEN_HEADER           = "Authorization";
    public static final String  AUTH_JWT_REFRESH_TOKEN_HEADER    = "Authorization-Refresh";
    public static final String  AUTH_JWT_TOKEN_COOKIE           = "weshare_token";
    public static final String  AUTH_JWT_REFRESH_TOKEN_COOKIE   = "weshare_refresh";
    public static final Integer AUTH_JWT_TOKEN_EXPIRE           = 60 * 15; // 15 Minutes
    public static final Integer AUTH_JWT_REFRESH_TOKEN_EXPIRE   = 60 * 60 * 24 * 30; // 1 Month

    /**
     * Profile
     */
    public static final Integer PROFILE_STATUS_INCOMPLETE = 0;
    public static final Integer PROFILE_STATUS_PROVIDER_PENDING = 1;
    public static final Integer PROFILE_STATUS_PROVIDER_APPROVED = 2;

    /**
     * Topic
     */
    public static final Integer TOPIC_STATUS_INACTIVE = 0;
    public static final Integer TOPIC_STATUS_ACTIVE = 1;
    public static final Integer TOPIC_TYPE_TEXT = 1;
    public static final Integer TOPIC_TYPE_MUSIC = 2;

    /**
     * Appointment
     */
    public static final Integer APPOINTMENT_STATUS_INACTIVE = 0;
    public static final Integer APPOINTMENT_STATUS_ACTIVE = 1;

    /**
     * S3
     */
    public static final String S3_BUCKET_NAME = "we-share-storage";
    public static final String S3_USERS_FOLDER_NAME = "users";
    public static final String S3_USER_PROFILE_IMAGE_NAME = "profile_image";


    /**
     * sendgrid
     */
    public static final String SENDGRID_API_KEY = "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";
    public static final String SENDGRID_EMAIL_SENDER = "admin@weshare.com";
    public static final String SENDGRID_EMAIL_ADMIN = "admin@weshare.com";

}
