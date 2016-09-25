package com.weshare.wesharespring.jdbi.dao;


import com.weshare.wesharespring.entity.Response.TopicResponse;
import com.weshare.wesharespring.entity.Topic;
import com.weshare.wesharespring.jdbi.mapper.TopicMapper;
import com.weshare.wesharespring.jdbi.mapper.TopicResponseMapper;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(TopicMapper.class)
public abstract class TopicDao {

    @RegisterMapper(TopicResponseMapper.class)
    @SqlQuery("SELECT t.*, p.first_name, p.last_name, p.head_shot_url, p.work_company, p.work_position, p.phone_rate, p.meetup_rate " +
        "FROM (SELECT * FROM topic WHERE status = :status) t " +
        "LEFT JOIN profile p " +
        "ON t.user_id = p.user_id")
    public abstract List<TopicResponse> getAll(
        @Bind("status") final Integer status);

    @SqlQuery("SELECT * FROM topic WHERE topic_id = :topic_id")
    public abstract Topic getById(
        @Bind("topic_id") final Long topicId);

    @RegisterMapper(TopicResponseMapper.class)
    @SqlQuery("SELECT t.*, p.first_name, p.last_name, p.head_shot_url, p.work_company, p.work_position, p.phone_rate, p.meetup_rate " +
        "FROM (SELECT * FROM topic WHERE user_id = :user_id) t " +
        "LEFT JOIN profile p " +
        "ON t.user_id = p.user_id")
    public abstract List<TopicResponse> getTopicsByUserId(
        @Bind("user_id") final Long userId);

    @SqlUpdate(
        "INSERT INTO topic (user_id, topic_title, topic_detail, topic_target, topic_type, topic_length, status, time_created, time_updated) " +
            "VALUES (:user_id, :topic_title, :topic_detail, :topic_target, :topic_type, :topic_length, :status, :time_now, :time_now)")
    @GetGeneratedKeys
    public abstract Long create(
        @Bind("user_id") final Long userId,
        @Bind("topic_title") final String topicTitle,
        @Bind("topic_detail") final String topicDetail,
        @Bind("topic_target") final String topicTarget,
        @Bind("topic_type") final String topicType,
        @Bind("topic_length") final Integer topicLength,
        @Bind("status") final Integer status,
        @Bind("time_now") final Long timeNow);

    @SqlUpdate(
        "UPDATE topic SET topic_title = :topic_title, topic_detail = :topic_detail, topic_target = :topic_target, " +
            "topic_type = :topic_type, topic_length = :topic_length, time_updated = :time_updated " +
            "WHERE topic_id = :topic_id")
    public abstract int update(
        @Bind("topic_id") final Long topicId,
        @Bind("topic_title") final String topicTitle,
        @Bind("topic_detail") final String topicDetail,
        @Bind("topic_target") final String topicTarget,
        @Bind("topic_type") final String topicType,
        @Bind("topic_length") final Integer topicLength,
        @Bind("time_updated") final Long timeUpdated);

    @SqlUpdate("UPDATE topic SET status = 0 WHERE topic_id = :topic_id")
    public abstract int delete(
        @Bind("topic_id") final Long topicId);

    abstract void close();
}
