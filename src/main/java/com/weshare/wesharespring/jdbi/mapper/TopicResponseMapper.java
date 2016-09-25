package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Response.TopicResponse;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicResponseMapper implements ResultSetMapper<TopicResponse> {

    public TopicResponse map(int index, ResultSet r, StatementContext ctx)
        throws SQLException {

        return TopicResponse.builder()
            .topicId(r.getLong("topic_id"))
            .userId(r.getLong("user_id"))
            .firstName(r.getString("first_name"))
            .lastName(r.getString("last_name"))
            .headShotUrl(r.getString("head_shot_url"))
            .workCompany(r.getString("work_company"))
            .workPosition(r.getString("work_position"))
            .phoneRate(r.getInt("phone_rate"))
            .meetupRate(r.getInt("meetup_rate"))
            .topicTitle(r.getString("topic_title"))
            .topicDetail(r.getString("topic_detail"))
            .topicTarget(r.getString("topic_target"))
            .topicType(r.getString("topic_type"))
            .topicLength(r.getInt("topic_length"))
            .status(r.getInt("status"))
            .timeCreated(r.getLong("time_created"))
            .timeUpdated(r.getLong("time_updated"))
            .build();
    }
}