package com.weshare.wesharespring.jdbi.dao;

import com.weshare.wesharespring.entity.Appointment;
import com.weshare.wesharespring.jdbi.mapper.AppointmentMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(AppointmentMapper.class)
public abstract class AppointmentDao {

    @SqlQuery("SELECT a.*, p.contact_wechat " +
        "FROM (SELECT * FROM appointment WHERE appointment_id  = :appointment_id) a " +
        "LEFT JOIN profile p " +
        "ON a.user_id = p.user_id")
    public abstract Appointment getById(
        @Bind("appointment_id") final Long appointmentId);

    @RegisterMapper(AppointmentMapper.class)
    @SqlQuery("SELECT a.*, p.contact_wechat " +
        "FROM (SELECT * FROM appointment WHERE user_id  = :user_id) a " +
        "LEFT JOIN profile p " +
        "ON a.user_id = p.user_id")
    public abstract List<Appointment> getAppointmentByUserId(
        @Bind("user_id") final Long userId);

    @SqlUpdate(
        "INSERT INTO appointment(user_id, appointment_type, topic_id, summary, question, meetup_time, meetup_address, status, time_created, time_updated)" +
            "VALUES (:user_id, :appointment_type, :topic_id, :summary, :question, :meetup_time, :meetup_address, :status, :time_now, :time_now)")
    @GetGeneratedKeys
    public abstract Long create(
        @Bind("user_id") final Long userId,
        @Bind("appointment_type") final Integer appointmentType,
        @Bind("topic_id") final Long topicId,
        @Bind("summary") final String summary,
        @Bind("question") final String question,
        @Bind("meetup_time") final Long meetUpTime,
        @Bind("meetup_address") final String meetUpAddress,
        @Bind("status") final Integer status,
        @Bind("time_now") final Long timeNow);

    abstract void close();
}
