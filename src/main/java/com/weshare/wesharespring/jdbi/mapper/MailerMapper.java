package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Mailer;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created on 5/24/16.
 */
public class MailerMapper implements ResultSetMapper<Mailer> {
    @Override
    public Mailer map(int index, ResultSet r, StatementContext ctx)
            throws SQLException {
        return Mailer.builder()
                .userId(r.getLong("user_id"))
                .emailType(r.getInt("email_type"))
                .emailId(r.getLong("email_id"))
                .build();
    }
}
