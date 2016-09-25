package com.weshare.wesharespring.entity.Request;

import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created on 5/24/16.
 */
public class EmailRequest {
    @NotNull
    private Long receiverId;
    private String emailSubject;
    private String emailContent;

    public EmailRequest(){

    }

    public EmailRequest(Long receiverId, String emailSubject,
            String emailContent) {
        this.receiverId = receiverId;
        this.emailSubject = emailSubject;
        this.emailContent = emailContent;
    }

    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        EmailRequest that = (EmailRequest) o;
        return Objects.equals(receiverId, that.receiverId) &&
                Objects.equals(emailSubject, that.emailSubject) &&
                Objects.equals(emailContent, that.emailContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiverId, emailSubject, emailContent);
    }
}
