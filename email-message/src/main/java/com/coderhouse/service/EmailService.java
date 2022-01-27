package com.coderhouse.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail();
    void sendEmailWithAttachment() throws MessagingException;

}
