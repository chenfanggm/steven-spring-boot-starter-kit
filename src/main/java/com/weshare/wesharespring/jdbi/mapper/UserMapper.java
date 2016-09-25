package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.User;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ResultSetMapper<User> {

    public User map(int index, ResultSet r, StatementContext ctx)
        throws SQLException {

        return User.builder()
            .userId(r.getLong("user_id"))
            .email(r.getString("email"))
            .username(r.getString("username"))
            .password(r.getString("password"))
            .verified(r.getInt("verified"))
            .accessLevel(r.getInt("access_level"))
            .status(r.getInt("status"))
            .lastLogin(r.getLong("last_login"))
            .timeCreated(r.getLong("time_created"))
            .timeUpdated(r.getLong("time_updated"))
            .build();
    }
}
