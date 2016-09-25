package com.weshare.wesharespring.jdbi.dao;

import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.jdbi.mapper.UserMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(UserMapper.class)
public abstract class UserDao {

    @SqlQuery("SELECT * FROM user WHERE user_id = :user_id")
    public abstract User getById(
        @Bind("user_id") final Long userId);

    @SqlQuery("SELECT * FROM user WHERE email = :email")
    public abstract User getByEmail
        (@Bind("email") final String email);

    @SqlUpdate(
        "INSERT INTO user(email, password, verified, access_level, status, last_login, time_created, time_updated)" +
            "VALUES (:email, :password, :verified, :access_level, :status, :time_now, :time_now, :time_now)")
    @GetGeneratedKeys
    public abstract Long create(
        @Bind("email") final String email,
        @Bind("password") final String password,
        @Bind("verified") final Integer verified,
        @Bind("access_level") final Integer accessLevel,
        @Bind("status") final Integer status,
        @Bind("time_now") final Long timeNow);


    @SqlUpdate(
        "UPDATE user SET password = :password, time_updated = :time_updated WHERE user_id = :user_id")
    public abstract int updatePasswordById(
        @Bind("user_id") final Long userId,
        @Bind("password") final String password,
        @Bind("time_updated") final Long timeUpdated);

    abstract void close();
}
