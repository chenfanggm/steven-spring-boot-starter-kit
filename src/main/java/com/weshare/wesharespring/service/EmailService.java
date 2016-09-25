package com.weshare.wesharespring.service;

import com.sendgrid.SendGrid;
import com.weshare.wesharespring.common.constant.Constant;
import com.weshare.wesharespring.common.exception.DuplicateItemException;
import com.weshare.wesharespring.common.exception.ItemNotFoundException;
import com.weshare.wesharespring.common.exception.StorageServiceException;
import com.weshare.wesharespring.common.utils.Utils;
import com.weshare.wesharespring.entity.Email;
import com.weshare.wesharespring.entity.Response.EmailResponse;
import com.weshare.wesharespring.entity.email.EmailBuilder;
import com.weshare.wesharespring.jdbi.dao.EmailDao;
import com.weshare.wesharespring.jdbi.dao.MailerDao;
import org.skife.jdbi.v2.exceptions.DBIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.List;

@Service
public class EmailService {
    private static Logger logger = LoggerFactory.getLogger(EmailService.class);

    /*@Value("${sendgrid.email.sender}")
    private String emailSender;

    @Value("${sendgrid.email.admin}")
    private String emailAdmin;*/

    private final EmailDao emailDao;
    private final MailerDao mailerDao;
    private final EmailRetryService emailRetryService;

    @Autowired
    public EmailService(final EmailDao emailDao, final MailerDao mailerDao,
                        final EmailRetryService emailRetryService) {
        this.emailDao = emailDao;
        this.mailerDao = mailerDao;
        this.emailRetryService = emailRetryService;
    }

    public Email getEmailById(final Long emailId)
            throws ItemNotFoundException, StorageServiceException {

        try {
            final Email email = emailDao.getById(emailId);
            if (email == null) {
                throw new ItemNotFoundException();
            }
            return email;

        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public List<EmailResponse> getEmailsByUserIdAndEmailType(Long userId,
                                                             Integer emailType)
            throws ItemNotFoundException, StorageServiceException {
        try {
            final List<EmailResponse> emailList = mailerDao.geAll(userId,
                                                                  emailType);
            if (emailList.isEmpty()) {
                throw new ItemNotFoundException();
            }
            return emailList;
        } catch (final DBIException dbiException) {
            throw new StorageServiceException(dbiException);
        }
    }

    public Email sendEmail(Long senderId, Long receiverId, String
            emailSubject, String emailContent)
            throws DuplicateItemException, ItemNotFoundException, StorageServiceException {

        try {
            final Long timeNow = Utils.getCurrentTimeStamp();
            final Long emailId = emailDao.create(emailSubject, emailContent, 0,
                                                 timeNow);
            mailerDao.create(senderId, 0, emailId, receiverId, 1, emailId);

            sendExternalEmailToAdmin(emailSubject, emailContent);
            return getEmailById(emailId);
        } catch (final DBIException dbiException) {
            if (Utils.isDuplicateEntryException(dbiException)) {
                throw new DuplicateItemException(dbiException);
            }
            throw new StorageServiceException(dbiException);
        }
    }

    public void sendExternalEmailToAdmin(String emailSubject,
                                         String emailContent) {
        EmailBuilder emailBuilder = new EmailBuilder();
        /*SendGrid.Email email = emailBuilder.addTo(emailAdmin).setFrom
                (emailSender).setSubject(emailSubject)
                .setTextContent(emailContent).build();*/
        SendGrid.Email email = emailBuilder.addTo(Constant.SENDGRID_EMAIL_ADMIN).setFrom
                (Constant.SENDGRID_EMAIL_SENDER).setSubject(emailSubject)
                .setTextContent(emailContent).build();

        ListenableFuture<Void> sendEmailFuture = emailRetryService.sendEmail
                (email);
        /*sendEmailFuture.addCallback(
                result -> logger.info(
                        "The email to " + emailAdmin + " send successfully."),
                ex -> logger.warn(
                        "The email to " + emailAdmin + " send failed."));*/
        sendEmailFuture.addCallback(
                result -> logger.info(
                        "The email to " + Constant.SENDGRID_EMAIL_ADMIN + " send successfully."),
                ex -> logger.warn(
                        "The email to " + Constant.SENDGRID_EMAIL_ADMIN + " send failed.", ex));
    }
}
