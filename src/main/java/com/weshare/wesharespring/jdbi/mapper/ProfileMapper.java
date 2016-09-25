package com.weshare.wesharespring.jdbi.mapper;


import com.weshare.wesharespring.entity.Profile;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileMapper implements ResultSetMapper<Profile> {

	@Override
	public Profile map(int index, ResultSet r, StatementContext ctx)
		throws SQLException {

		return Profile.builder()
			.userId(r.getLong("user_id"))
            .firstName(r.getString("first_name"))
			.lastName(r.getString("last_name"))
			.gender(r.getInt("gender"))
			.major(r.getString("major"))
			.summary(r.getString("summary"))
			.contactWechat(r.getString("contact_wechat"))
			.contactEmail(r.getString("contact_email"))
			.contactPhone(r.getString("contact_phone"))
			.workCity(r.getString("work_city"))
			.workState(r.getString("work_state"))
			.workAddress(r.getString("work_address"))
			.workCompany(r.getString("work_company"))
			.workPosition(r.getString("work_position"))
			.workYear(r.getInt("work_year"))
			.workReferStatus(r.getInt("work_refer_status"))
			.workReferPosition(r.getString("work_refer_position"))
			.availableTime(r.getString("available_time"))
			.preferTime(r.getString("prefer_time"))
			.preferAddress(r.getString("prefer_address"))
			.preferPayment(r.getString("prefer_payment"))
			.phoneRate(r.getInt("phone_rate"))
            .meetupRate(r.getInt("meetup_rate"))
            .meetupBonus(r.getString("meetup_bonus"))
			.headShotUrl(r.getString("head_shot_url"))
			.timeZone(r.getInt("time_zone"))
			.status(r.getInt("status"))
			.timeCreated(r.getLong("time_created"))
			.timeUpdated(r.getLong("time_updated"))
			.build();
	}
}
