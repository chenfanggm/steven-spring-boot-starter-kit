package com.weshare.wesharespring.jdbi.dao;


import com.weshare.wesharespring.entity.Profile;
import com.weshare.wesharespring.jdbi.mapper.ProfileMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(ProfileMapper.class)
public abstract class ProfileDao {

    @SqlQuery("SELECT * FROM profile WHERE profile_id = :profile_id")
    public abstract Profile getById(
        @Bind("profile_id") final Long profileId);


    @SqlQuery("SELECT * FROM profile WHERE user_id = :user_id")
    public abstract Profile getByUserId(
        @Bind("user_id") final Long userId);

    @SqlUpdate(
        "INSERT INTO profile (user_id, first_name, last_name, gender, major, summary, " +
            "contact_wechat, contact_email, contact_phone, work_city, work_state, work_address, " +
            "work_company, work_position, work_year, work_refer_status, work_refer_position, " +
            "available_time, prefer_time, prefer_address, prefer_payment, phone_rate, meetup_rate, meetup_bonus, " +
            "head_shot_url, time_zone, status, time_created, time_updated) " +
            "VALUES (:user_id, :first_name, :last_name, :gender, :major, :summary, " +
            ":contact_wechat, :contact_email, :contact_phone, :work_city, :work_state, :work_address, " +
            ":work_company, :work_position, :work_year, :work_refer_status, :work_refer_position, " +
            ":available_time, :prefer_time, :prefer_address, :prefer_payment, :phone_rate, :meetup_rate, :meetup_bonus, " +
            ":head_shot_url, :time_zone, :status, :time_now, :time_now)")
    public abstract int create(
        @Bind("user_id") final Long userId,
        @Bind("first_name") final String firstName,
        @Bind("last_name") final String lastName,
        @Bind("gender") final Integer gender,
        @Bind("major") final String major,
        @Bind("summary") final String summary,
        @Bind("contact_wechat") final String contactWechat,
        @Bind("contact_email") final String contactEmail,
        @Bind("contact_phone") final String contactPhone,
        @Bind("work_city") final String workCity,
        @Bind("work_state") final String workState,
        @Bind("work_address") final String workAddress,
        @Bind("work_company") final String workCompany,
        @Bind("work_position") final String workPosition,
        @Bind("work_year") final Integer workYear,
        @Bind("work_refer_status") final Integer workReferStatus,
        @Bind("work_refer_position") final String workReferPosition,
        @Bind("available_time") final String availableTime,
        @Bind("prefer_time") final String preferTime,
        @Bind("prefer_address") final String preferAddress,
        @Bind("prefer_payment") final String preferPayment,
        @Bind("phone_rate") final Integer phoneRate,
        @Bind("meetup_rate") final Integer meetupRate,
        @Bind("meetup_bonus") final String meetupBonus,
        @Bind("head_shot_url") final String headShotUrl,
        @Bind("time_zone") final Integer timeZone,
        @Bind("status") final Integer status,
        @Bind("time_now") final Long timeNow);

    @SqlUpdate(
        "UPDATE profile SET first_name = :first_name, last_name = :last_name, gender = :gender, major = :major, " +
            "summary = :summary, contact_wechat = :contact_wechat, contact_email = :contact_email, " +
            "contact_phone = :contact_phone, work_city = :work_city, work_state = :work_state, work_address = :work_address, " +
            "work_company = :work_company, work_position = :work_position, work_year = :work_year, " +
            "work_refer_status = :work_refer_status, work_refer_position = :work_refer_position, " +
            "available_time = :available_time, prefer_time = :prefer_time, prefer_address = :prefer_address, " +
            "prefer_payment = :prefer_payment, phone_rate = :phone_rate, meetup_rate = :meetup_rate, meetup_bonus = :meetup_bonus, " +
            "head_shot_url = :head_shot_url, time_zone = :time_zone, status = :status, time_updated = :time_updated " +
            "WHERE user_id = :user_id")
    public abstract int update(
        @Bind("user_id") final Long userId,
        @Bind("first_name") final String firstName,
        @Bind("last_name") final String lastName,
        @Bind("gender") final Integer gender,
        @Bind("major") final String major,
        @Bind("summary") final String summary,
        @Bind("contact_wechat") final String contactWechat,
        @Bind("contact_email") final String contactEmail,
        @Bind("contact_phone") final String contactPhone,
        @Bind("work_city") final String workCity,
        @Bind("work_state") final String workState,
        @Bind("work_address") final String workAddress,
        @Bind("work_company") final String workCompany,
        @Bind("work_position") final String workPosition,
        @Bind("work_year") final Integer workYear,
        @Bind("work_refer_status") final Integer workReferStatus,
        @Bind("work_refer_position") final String workReferPosition,
        @Bind("available_time") final String availableTime,
        @Bind("prefer_time") final String preferTime,
        @Bind("prefer_address") final String preferAddress,
        @Bind("prefer_payment") final String preferPayment,
        @Bind("phone_rate") final Integer phoneRate,
        @Bind("meetup_rate") final Integer meetupRate,
        @Bind("meetup_bonus") final String meetupBonus,
        @Bind("head_shot_url") final String headShotUrl,
        @Bind("time_zone") final Integer timeZone,
        @Bind("status") final Integer status,
        @Bind("time_updated") final Long timeNow);

    @SqlUpdate(
        "UPDATE profile SET status = :status, time_updated = :time_updated " +
            "WHERE user_id = :user_id")
    public abstract int updateStatus(
        @Bind("user_id") final Long userId,
        @Bind("status") final Integer status,
        @Bind("time_updated") final Long timeNow);

    abstract void close();
}
