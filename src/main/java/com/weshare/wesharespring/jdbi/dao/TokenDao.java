package com.weshare.wesharespring.jdbi.dao;

import com.weshare.wesharespring.entity.Token;
import com.weshare.wesharespring.jdbi.mapper.TokenMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TokenMapper.class)
public abstract class TokenDao {

    @SqlQuery("SELECT refresh_token FROM token WHERE user_id = :user_id")
    public abstract Token getByUserId(
        @Bind("user_id") final Long userId);

    @SqlQuery("SELECT refresh_token FROM token WHERE user_id = :user_id")
    public abstract String getRefreshTokenByUserId(
        @Bind("user_id") final Long userId);

    @SqlUpdate(
        "INSERT INTO token(user_id, device, ip, refresh_token, time_created, time_updated) " +
            "VALUES (:user_id, :device, :ip, :refresh_token, :time_now, :time_now)")
    public abstract int create(
        @Bind("user_id") final Long userId,
        @Bind("device") final String device,
        @Bind("ip") final String ip,
        @Bind("refresh_token") final String freshToken,
        @Bind("time_now") final Long timeNow);

    @SqlUpdate(
        "UPDATE token SET device = :device, ip = :ip, refresh_token = :refresh_token, " +
            "time_updated = :time_updated WHERE user_id = :user_id")
    public abstract int update(
        @Bind("user_id") final Long userId,
        @Bind("device") final String device,
        @Bind("ip") final String ip,
        @Bind("refresh_token") final String refreshToken,
        @Bind("time_updated") final Long timeUpdate);

    abstract void close();
}
