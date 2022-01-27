package com.coderhouse.controller;

import com.coderhouse.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coder-house")
public class MessageController {

    private final EmailService service;

    @PostMapping("/mensajes/email/send")
    public void sendEmail() {
        service.sendEmail();
    }

    @PostMapping("/mensajes/email/send/attachment")
    public void sendEmailWithAttachment() throws MessagingException {
        service.sendEmailWithAttachment();
    }

}
