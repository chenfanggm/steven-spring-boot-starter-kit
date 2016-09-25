package com.weshare.wesharespring.service;

import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.exception.DuplicateItemException;
import com.weshare.wesharespring.common.exception.ItemNotFoundException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.entity.Response.TopicResponse;
import com.weshare.wesharespring.entity.Topic;
import com.weshare.wesharespring.jdbi.dao.TopicDao;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TopicService {

    private final TopicDao topicDao;

    @Autowired
    public TopicService(final TopicDao topicDao) {
        this.topicDao = topicDao;
    }

    public List<TopicResponse> getAll()
        throws ItemNotFoundException, StorageServiceException {

        try {
            final List<TopicResponse> topicList = topicDao.getAll(Constant.TOPIC_STATUS_ACTIVE);
            if (topicList.isEmpty()) {
                throw new ItemNotFoundException();
            }
            return topicList;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public Topic getTopicById(final Long topicId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final Topic topic = topicDao.getById(topicId);
            if (topic == null) {
                throw new ItemNotFoundException();
            }
            return topic;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public List<TopicResponse> getTopicsByUserId(final Long userId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final List<TopicResponse> topicList = topicDao.getTopicsByUserId(userId);
            if (topicList == null || topicList.isEmpty()) {
                throw new ItemNotFoundException();
            }
            return topicList;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public Topic createTopic(final Topic topic)
        throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            final Long topicId = topicDao.create(topic.getUserId(), topic.getTopicTitle(),
                topic.getTopicDetail(), topic.getTopicTarget(), topic.getTopicType(),
                topic.getTopicLength(), Constant.TOPIC_STATUS_ACTIVE, timeNow);

            return getTopicById(topicId);

        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }

    public Topic updateTopic(final Topic topic)
        throws ItemNotFoundException, StorageServiceException{

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            final int changes = topicDao.update(topic.getTopicId(), topic.getTopicTitle(), topic.getTopicDetail(),
                topic.getTopicTarget(), topic.getTopicType(), topic.getTopicLength(), timeNow);
            if (changes == 0) {
                throw new ItemNotFoundException();
            }
            return getTopicById(topic.getTopicId());
        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public void deleteTopicById(final Long topicId)
        throws ItemNotFoundException, StorageServiceException {

        try {
            final Integer changes = topicDao.delete(topicId);
            if (changes == 0) {
                throw new ItemNotFoundException();
            }
        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

}
