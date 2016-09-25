package com.weshare.wesharespring.entity.email;

import com.sendgrid.SendGrid;

import java.io.File;
import java.io.IOException;

public class EmailBuilder {
    private SendGrid.Email email;

    public EmailBuilder() {
        this.email = new SendGrid.Email();
    }

    public SendGrid.Email build(){
        return email;
    }

    public EmailBuilder addTo(String emailAddress) {
        email.addTo(emailAddress);
        return this;
    }

    public EmailBuilder addToName(String emailName) {
        email.addToName(emailName);
        return this;
    }

    public EmailBuilder addCc(String emailAddress) {
        email.addCc(emailAddress);
        return this;
    }

    public EmailBuilder addBcc(String emailAddress) {
        email.addBcc(emailAddress);
        return this;
    }

    public EmailBuilder setFrom(String emailAddress) {
        email.setFrom(emailAddress);
        return this;
    }

    public EmailBuilder setFromName(String emailName) {
        email.setFromName(emailName);
        return this;
    }

    public EmailBuilder setReplyTo(String emailAddress) {
        email.setReplyTo(emailAddress);
        return this;
    }

    public EmailBuilder setSubject(String emailSubject) {
        email.setSubject(emailSubject);
        return this;
    }

    public EmailBuilder setTextContent(String emailTextContent) {
        email.setText(emailTextContent);
        return this;
    }

    public EmailBuilder setHtmlContent(String emailHtmlContent) {
        email.setHtml(emailHtmlContent);
        return this;
    }

    /**
     * <code>addAttachment("/var/user/", "image.png", "ID_IN_HTML");
     * setHtmlContent("<html><body>TEXT BEFORE IMAGE<img src=\"cid:ID_IN_HTML\"></img>AFTER IMAGE</body></html>")</code>
     */
    public EmailBuilder addAttachment(String filePath, String
            fileName, String contentId) throws IOException {
        email.addAttachment(fileName, new File(filePath + fileName));
        email.addContentId(fileName, contentId);
        return this;
    }
}
