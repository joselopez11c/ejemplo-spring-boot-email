package com.coderhouse.service.impl;

import com.coderhouse.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public void sendEmail() {
        var message = new SimpleMailMessage();
        message.setTo("joselopez11c@gmail.com");

        message.setSubject("Testing form Coderhouse");
        message.setText("Este es un ejemplo desde Spring Boot");

        javaMailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment() throws MessagingException {
        var message = javaMailSender.createMimeMessage();

        var helper = new MimeMessageHelper(message, true);
        helper.setTo("joselopez11c@gmail.com");

        helper.setSubject("Testing form Coderhouse");
        helper.setText("<h1> Este es un ejemplo desde Spring Boot <h1>", true);
        helper.addAttachment("coderhouse.png", new ClassPathResource("static/coderhouse.png"));
        javaMailSender.send(message);
    }
}
