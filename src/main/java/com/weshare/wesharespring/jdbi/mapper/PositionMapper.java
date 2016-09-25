package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Position;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PositionMapper implements ResultSetMapper<Position> {

	@Override
	public Position map(int index, ResultSet r, StatementContext statementContext) throws SQLException {

		return Position.builder()
			.positionId(r.getLong("position_id"))
			.userId(r.getLong("user_id"))
			.positionCompany(r.getString("position_company"))
			.positionTitle(r.getString("position_title"))
			.positionDetail(r.getString("position_detail"))
			.positionDeadline(r.getLong("position_deadline"))
			.status(r.getInt("status"))
			.timeCreated(r.getLong("time_created"))
			.timeUpdated(r.getLong("time_updated"))
			.build();
	}
}
