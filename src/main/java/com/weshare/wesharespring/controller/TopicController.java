package com.weshare.wesharespring.controller;

import com.weshare.wesharespring.common.annotation.LoggedUser;
import com.weshare.wesharespring.common.exception.BaseServiceException;
import com.weshare.wesharespring.config.RouteConfig;
import com.weshare.wesharespring.entity.Response.SuccessResponse;
import com.weshare.wesharespring.entity.Response.TopicResponse;
import com.weshare.wesharespring.entity.Topic;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.service.TopicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = RouteConfig.TOPIC_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class TopicController {

    private static final Logger logger = LoggerFactory.getLogger(TopicController.class);

    private final TopicService topicService;

    @Autowired
    public TopicController(final TopicService topicService) {
        this.topicService = topicService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TopicResponse> getAllTopic()
        throws BaseServiceException {

        logger.info("<Start> getAllTopic()");
        final List<TopicResponse> topic =  topicService.getAll();
        logger.info("<End> getAllTopic");
        return topic;
    }

    @RequestMapping(value = "/{topicId}", method = RequestMethod.GET)
    public Topic getTopicById(@PathVariable("topicId") final Long topicId)
        throws BaseServiceException {

        logger.info("<Start> getTopicById(): TopicId: {}", topicId);
        final Topic topic =  topicService.getTopicById(topicId);
        logger.info("<End> getTopicById(): TopicId: {}", topicId);
        return topic;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public List<TopicResponse> getTopicsByCurrentUser(@LoggedUser final User user )
        throws BaseServiceException {

        logger.info("<Start> getTopicsByCurrentUser(): UserId: {}", user.getUserId());
        final List<TopicResponse> topicList =  topicService.getTopicsByUserId(user.getUserId());
        logger.info("<End> getTopicsByCurrentUser(): UserId: {}", user.getUserId());
        return topicList;
    }

    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    public List<TopicResponse> getTopicsByUserId(@PathVariable("userId") final Long userId)
        throws BaseServiceException {

        logger.info("<Start> getTopicsByUserId(): UserId: {}", userId);
        final List<TopicResponse> topicList =  topicService.getTopicsByUserId(userId);
        logger.info("<End> getTopicsByUserId(): UserId: {}", userId);
        return topicList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Topic createTopic(@RequestBody final Topic topic, @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> createTopic()");
        topic.setUserId(user.getUserId());
        final Topic topicResult = topicService.createTopic(topic);
        logger.info("<End> createTopic()");

        return topicResult;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Topic updateTopic(@RequestBody final Topic topic, @LoggedUser final User user)
        throws BaseServiceException {

        logger.info("<Start> updateTopic()");
        topic.setUserId(user.getUserId());
        final Topic topicResult = topicService.updateTopic(topic);
        logger.info("<End> updateTopic()");
        return topicResult;
    }

    @RequestMapping(value = "/{topicId}", method = RequestMethod.DELETE)
    public ResponseEntity<SuccessResponse> deleteTopic(@PathVariable("topicId") final Long topicId)
        throws BaseServiceException {

        logger.info("<Start> deleteTopic(): topicId: {}", topicId);
        topicService.deleteTopicById(topicId);
        logger.info("<End> deleteTopic(): topicId: {}", topicId);

        return ResponseEntity.ok(new SuccessResponse());
    }
}
