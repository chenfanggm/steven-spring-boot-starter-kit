package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Token;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TokenMapper implements ResultSetMapper<Token> {

    public Token map(int index, ResultSet r, StatementContext ctx)
        throws SQLException {

        return Token.builder()
            .userId(r.getLong("user_id"))
            .device(r.getString("device"))
            .ip(r.getString("ip"))
            .refreshToken(r.getString("refresh_token"))
            .timeCreated(r.getLong("time_created"))
            .timeUpdated(r.getLong("time_updated"))
            .build();
    }
}
