package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Like;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LikeMapper implements ResultSetMapper<Like> {

	@Override
	public Like map(int index, ResultSet r, StatementContext ctx) throws SQLException {

		return Like.builder()
			.userAId(r.getLong("user_a_id"))
			.userBId(r.getLong("user_b_id"))
			.likeType(r.getInt("like_type"))
			.status(r.getInt("status"))
			.timeCreated(r.getLong("time_created"))
			.timeUpdated(r.getLong("time_updated"))
			.build();
	}

}
