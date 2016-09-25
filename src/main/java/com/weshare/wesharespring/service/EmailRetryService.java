package com.weshare.wesharespring.service;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;
import com.weshare.wesharespring.common.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

@Service
public class EmailRetryService {
    private static Logger logger = LoggerFactory.getLogger(EmailRetryService
                                                                   .class);

    /*@Value("${sendGrid.api.key}")
    private String sendGridAPIKey;*/

    @Async("emailAsyncExecutor")
    public ListenableFuture<Void> sendEmail(final SendGrid.Email email) {
        logger.info("Execute sendEmail with executor - " + Thread
                .currentThread().getName());

        try {
            ListenableFuture<Void> sendEmailFuture = sendEmailWithRetry(email);
            sendEmailFuture.get();
        } catch (SendGridException | InterruptedException |
                ExecutionException e) {
            return AsyncResult.forExecutionException(e);
        }

        return AsyncResult.forValue(null);
    }

    @Retryable(backoff = @Backoff(delay = 1000, multiplier = 2))
    private ListenableFuture<Void> sendEmailWithRetry(final SendGrid.Email email)
            throws SendGridException {
        logger.info("Trying to send email...");
        //logger.info("send email to "+ sendGridAPIKey);
        //SendGrid sendgrid = new SendGrid(sendGridAPIKey);
        SendGrid sendgrid = new SendGrid(Constant.SENDGRID_API_KEY);
        SendGrid.Response response = sendgrid.send(email);
        logger.info(response.getMessage());

        return AsyncResult.forValue(null);
    }
}
