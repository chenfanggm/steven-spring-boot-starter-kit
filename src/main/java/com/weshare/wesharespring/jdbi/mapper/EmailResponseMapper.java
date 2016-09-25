package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Response.EmailResponse;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import javax.validation.constraints.NotNull;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmailResponseMapper implements ResultSetMapper<EmailResponse> {

        public EmailResponse map(int index, ResultSet r, StatementContext ctx)
                throws SQLException {

            return EmailResponse.builder()
                    .emailId(r.getLong("email_id"))
                    .userId(r.getLong("user_id"))
                    .emailType(r.getInt("email_type"))
                    .emailSubject(r.getString("email_subject"))
                    .emailContent(r.getString("email_content"))
                    .status(r.getInt("status"))
                    .timeCreated(r.getLong("time_created"))
                    .build();
        }
}
