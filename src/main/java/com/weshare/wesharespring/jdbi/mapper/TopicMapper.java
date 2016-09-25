package com.weshare.wesharespring.jdbi.mapper;

import com.weshare.wesharespring.entity.Topic;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TopicMapper implements ResultSetMapper<Topic> {

    public Topic map(int index, ResultSet r, StatementContext ctx)
        throws SQLException {

        return Topic.builder()
            .topicId(r.getLong("topic_id"))
            .userId(r.getLong("user_id"))
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