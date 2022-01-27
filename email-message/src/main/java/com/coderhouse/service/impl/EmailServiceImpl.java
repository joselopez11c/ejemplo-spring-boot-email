package com.coderhouse.service.impl;

import com.coderhouse.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

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
        var userName = "Jose Ignacio"; //Viene de la bd
        helper.setTo("joselopez11c@gmail.com"); //Viene de la bd
        helper.setSubject("Testing form Coderhouse");
        helper.setText(
                String.format("<h1> Hola %s Este es un ejemplo desde Spring Boot <h1>", userName), true);
        helper.addAttachment("coderhouse.png",
                new ClassPathResource("static/coderhouse.png"));
        javaMailSender.send(message);
    }
}

