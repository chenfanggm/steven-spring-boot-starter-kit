package com.weshare.wesharespring.jdbi.dao;

import com.weshare.wesharespring.entity.Response.EmailResponse;
import com.weshare.wesharespring.jdbi.mapper.EmailResponseMapper;
import com.weshare.wesharespring.jdbi.mapper.MailerMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(MailerMapper.class)
public abstract class MailerDao {
    @RegisterMapper(EmailResponseMapper.class)
    @SqlQuery("SELECT m.email_id, m.user_id, m.email_tpe, e.email_subject, " +
            "e.email_content, e.status, e.time_created " +
            "FROM (SELECT * FROM mailer WHERE user_id = :user_id " +
            "AND email_type = :email_type) m " +
            "LEFT JOIN email e " +
            "ON m.email_id = e.email_id")
    public abstract List<EmailResponse> geAll(
            @Bind("user_id") final Long userId,
            @Bind("email_type") final Integer emailType);

    @SqlUpdate(
            "INSERT INTO mailer(user_id, email_type, email_id)" +
                    "VALUES (:user_a_id, :email_a_type, :email_a_id), " +
                    "(:user_b_id, :email_b_type, :email_b_id)")
    public abstract void create(
            @Bind("user_a_id") final Long userAId,
            @Bind("email_a_type") final Integer emailAType,
            @Bind("email_a_id") final Long emailAId,
            @Bind("user_b_id") final Long userBId,
            @Bind("email_b_type") final Integer emailBType,
            @Bind("email_b_id") final Long emailBId);

    abstract void close();
}
