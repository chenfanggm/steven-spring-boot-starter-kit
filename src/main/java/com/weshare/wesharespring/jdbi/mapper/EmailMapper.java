package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Email;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 5/24/16.
 */
public class EmailMapper implements ResultSetMapper<Email> {
    @Override
    public Email map(int index, ResultSet r, StatementContext ctx)
            throws SQLException {
        return Email.builder()
                .emailId(r.getLong("email_id"))
                .emailSubject(r.getString("email_subject"))
                .emailContent(r.getString("email_content"))
                .status(r.getInt("status"))
                .timeCreated(r.getLong("time_created"))
                .build();
    }
}
