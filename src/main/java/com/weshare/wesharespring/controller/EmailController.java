package com.weshare.wesharespring.controller;

import com.weshare.wesharespring.common.annotation.LoggedUser;
import com.weshare.wesharespring.common.exception.BaseServiceException;
import com.weshare.wesharespring.config.RouteConfig;
import com.weshare.wesharespring.entity.Email;
import com.weshare.wesharespring.entity.Request.EmailRequest;
import com.weshare.wesharespring.entity.Response.EmailResponse;
import com.weshare.wesharespring.entity.User;
import com.weshare.wesharespring.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = RouteConfig.EMAIL_URL, produces = {MediaType.APPLICATION_JSON_VALUE})
public class EmailController {
    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    private final EmailService emailService;

    @Autowired
    public EmailController(final EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<EmailResponse> getEmails(@LoggedUser final User user,
                                         @RequestParam final Integer emailType)
            throws BaseServiceException {

        logger.info("<Start> getEmails(): UserId: {}", user.getUserId());
        final List<EmailResponse> emailList=emailService
        .getEmailsByUserIdAndEmailType(user
                .getUserId(), emailType);
        logger.info("<End> getEmails(): UserId: {}", user.getUserId());
        return emailList;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Email sendEmail(@LoggedUser final User user,
                           @RequestBody final EmailRequest emailRequest)
            throws BaseServiceException {

        logger.info("<Start> sendEmail()");
        // Todo: Add Receiver Validation.
        final Email email=emailService.sendEmail(user.getUserId(), emailRequest
                        .getReceiverId(), emailRequest.getEmailSubject(),
                emailRequest.getEmailContent());
        logger.info("<End> sendEmail()");
        return email;
    }
}
