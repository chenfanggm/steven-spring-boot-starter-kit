package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Appointment;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentMapper implements ResultSetMapper<Appointment> {

	@Override
	public Appointment map(int index, ResultSet r, StatementContext statementContext) throws SQLException {

		return Appointment.builder()
			.appointmentId(r.getLong("appointment_id"))
            .appointmentType(r.getInt("appointment_type"))
			.userId(r.getLong("user_id"))
			.topicId(r.getLong("topic_id"))
			.summary(r.getString("summary"))
			.question(r.getString("question"))
			.meetupTime(r.getLong("meetup_time"))
			.meetupAddress(r.getString("meetup_address"))
			.status(r.getInt("status"))
			.timeCreated(r.getLong("time_created"))
			.timeUpdated(r.getLong("time_updated"))
			.build();
	}

}
