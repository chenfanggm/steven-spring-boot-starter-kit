package com.weshare.wesharespring.jdbi.dao;

import com.weshare.wesharespring.entity.Email;
import com.weshare.wesharespring.jdbi.mapper.EmailMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(EmailMapper.class)
public abstract class EmailDao {
    @SqlQuery("SELECT * FROM email WHERE email_id  = :email_id")
    public abstract Email getById(
            @Bind("email_id") final Long emailId);

    @SqlUpdate(
            "INSERT INTO email(email_subject, email_content, " +
                    "status, time_created)" +
                    "VALUES (:email_subject, :email_content, " +
                    ":status, :time_now)")
    @GetGeneratedKeys
    public abstract Long create(
            @Bind("email_subject") final String emailSubject,
            @Bind("email_content") final String emailContent,
            @Bind("status") final Integer status,
            @Bind("time_now") final Long timeNow);

    abstract void close();
}
